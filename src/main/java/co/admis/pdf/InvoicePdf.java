/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.pdf;

import static co.admis.config.ServerConfiguration.AWS_KEY;
import static co.admis.config.ServerConfiguration.AWS_VALUE;
import static co.admis.config.ServerConfiguration.MAILJET_FROM;
import static co.admis.config.ServerConfiguration.MAILJET_HOST;
import static co.admis.config.ServerConfiguration.MAILJET_PASSWORD;
import static co.admis.config.ServerConfiguration.MAILJET_PORT;
import static co.admis.config.ServerConfiguration.MAILJET_USERNAME;
import static co.admis.config.ServerConfiguration.S3_BUCKET;
import co.admis.controller.HibernateUtil;
import co.admis.model.BillFormat;
import co.admis.model.Customer;
import co.admis.model.Facility;
import co.admis.model.Organization;
import co.admis.model.SellRecord;
import co.admis.model.Invoice;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.PutObjectRequest;
import co.admis.pattern.NumberToWord;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class InvoicePdf {
    
    private final String EMAIL = "JAY@gmail.com";
    
    private List<SellRecord> rows;
    private int maxRowSize = 23;
    private int maxPageWithSummation = 16;
    private int breakPoint = 17;
    private int selectMonth = 0;
    private int selectYear = 0;
    private String date;
    private String[] month = {"January",      
                                    "February",
                                    "March",        
                                    "April",        
                                    "May",          
                                    "June",         
                                    "July",         
                                    "August",       
                                    "September",    
                                    "October",      
                                    "November",     
                                    "December"};
    
    private Facility FACILITY;
    private Organization ORGANIZATION;
    private Customer CUSTOMER;
    private BillFormat BILL_FORMAT;
    private Invoice INVOICE;
    
    public void makeInvoice(String adminUsername, int facilityId, int invoiceId, int customerId) {
        try {
        //Today's date
        ZonedDateTime tDate = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        //Yesturday Date
        ZonedDateTime yDate = tDate.minusDays(1);
        selectMonth = yDate.getMonthValue();
        selectYear = yDate.getYear();
        date = extractDate(yDate);
        rows = getSellRecordsByInvoiceId(invoiceId);
        // Data
        CUSTOMER = getCustomerById(customerId);
        ORGANIZATION = getOrganizationByAdminUsername(adminUsername);
        FACILITY = getFacilityById(facilityId);
        BILL_FORMAT = getBillFormatByAdminUsername(adminUsername);
        INVOICE = getInvoiceByIdInvoiceId(invoiceId);
            PDDocument pdfDocument = new PDDocument();
            PDPage pdfPage = new PDPage();
            pdfDocument.addPage(pdfPage);
            PDPageContentStream contents = new PDPageContentStream(pdfDocument, pdfPage);
            String fileName = getTimeInMilis();
            // Generate Invoice PDF
            printPDF(pdfDocument, contents, CUSTOMER);

            contents.close();
//                Saving the document
            final File file = File.createTempFile(fileName, ".pdf");
            pdfDocument.save(file);
//            pdfDocument.save("F:\\other\\single-invoice3.pdf");
            pdfDocument.close();
            
            uploadS3Object(ORGANIZATION.getS3FolderName()+"/invoice/"+invoiceId+"/"+fileName+".pdf", file);
            DataSource source = new FileDataSource(file);
            if(CUSTOMER.getCustomerEmail()!=null && !CUSTOMER.getCustomerEmail().equalsIgnoreCase("")){
                String EMAIL_CONTENT = "<br>Dear "+CUSTOMER.getCustomerName()+"<br><br>Thank You for visiting "+FACILITY.getFacilityName()+". Please download your invoice below.<br><br><br><br>Regards,<br>"+FACILITY.getFacilityName()+"<br><br>Please contact us for any query/concern.<br><br><a href='tel:+91 "+FACILITY.getNumber()+"'>+91 "+FACILITY.getNumber()+"</a><br><br><img src='"+ORGANIZATION.getLogo()+"' height='120px'><br><br><a href='mailto:"+FACILITY.getEmail()+"'>"+FACILITY.getEmail()+"</a><br><br><a href='"+FACILITY.getFacilityWebsite()+"'>"+FACILITY.getFacilityWebsite()+"</a>";
                sendEmail(CUSTOMER.getCustomerEmail(), "Invoice", EMAIL_CONTENT, source, fileName+".pdf");
            }
            file.delete();
        } catch (IOException ex) {
            Logger.getLogger(InvoicePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void header(PDDocument pdfDocument, PDPageContentStream contents) throws IOException{
        //        Company Logo
        URL url = new URL(ORGANIZATION.getLogo());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
          
        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(pdfDocument, output.toByteArray(), "logo");
        final float width = 60f;
        final float scale = width / pdImage.getWidth();
        contents.drawImage(pdImage, 50, 720, width, pdImage.getHeight()*scale);
        
//        Company GST Number
        PDFont gstFont = PDType1Font.HELVETICA_BOLD;
        PDFPrinter gstPrinter = new PDFPrinter(contents, gstFont, 12);
        gstPrinter.putText(50, 710, "CIN: "+BILL_FORMAT.getCin());
        gstPrinter.putText(50, 695, "PAN: "+BILL_FORMAT.getPanNumber());
        
//        Company Name
        PDFont headerFont = PDType1Font.HELVETICA_BOLD;
        PDFPrinter headerPrinter = new PDFPrinter(contents, headerFont, 14);
        headerPrinter.putText(120, 762, FACILITY.getFacilityName());

//        Company Address
        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 10);
        textPrinter.putText(120, 750, FACILITY.getFacilityAddress());
        textPrinter.putText(120, 738, FACILITY.getFacilityCity()+", "+FACILITY.getFacilityState()+", "+FACILITY.getFacilityPin());
        textPrinter.putText(120, 726, "+91 "+FACILITY.getNumber()+", "+FACILITY.getFacilityWebsite());

//        Invoice Details
        Color color = new Color(200, 200, 200);
        PDFPrinter invoiceHeaderPrinter = new PDFPrinter(contents, font, 24, color);
        invoiceHeaderPrinter.putText(420, 740, "TAX INVOICE");     

        textPrinter.putText(400, 710, "Invoice date:");
        textPrinter.putText(400, 698, "Invoice number:");
        textPrinter.putText(480, 710, INVOICE.getRegisterDate().split("\\s+")[0]);
        textPrinter.putText(480, 698, INVOICE.getInvoiceIdPrefix());
//        String formatted = String.format("%05d", pdfInvoiceData.getInvoiceNumber());
//        textPrinter.putText(430, 698, "WAY"+ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).getYear()+formatted);
//        textPrinter.putText(430, 686, pdfInvoiceData.getTransactionId());
        
    }
    
    private void spplierDetails(PDPageContentStream contents, Customer customer) throws IOException{
        
        PDFont headerFont = PDType1Font.HELVETICA_BOLD;
        PDFPrinter headerPrinter = new PDFPrinter(contents, headerFont, 10);
        PDFont font = PDType1Font.HELVETICA;
        
        //        Detail of Supplier
        int x = 50;

        int y = 670;

        PDFPrinter detailsOfSupplierPrinter = new PDFPrinter(contents, headerFont, 10);
        detailsOfSupplierPrinter.putText(x, y, "Details of Supplier:");

        y -= 14;
        PDFPrinter supplierAddressPrinter = new PDFPrinter(contents, font, 10);
        supplierAddressPrinter.putText(x, y, FACILITY.getFacilityName());
        y -= 12;
        supplierAddressPrinter.putText(x, y, FACILITY.getFacilityAddress());
        y -= 12;
        supplierAddressPrinter.putText(x, y, FACILITY.getFacilityPin()+", "+FACILITY.getFacilityCity());
        y -= 12;
        supplierAddressPrinter.putText(x, y, FACILITY.getFacilityState());
        y -= 14;
        headerPrinter.putText(x, y, "GSTIN: "+BILL_FORMAT.getGstNumber());
        
//        Details of Receiver
        int a = 400;
        int b = 670;
        
//        Change values
        String address = customer.getCustomerAddress();
        String city = customer.getCustomerCity();
        String state = customer.getCustomerState();
        String pin = customer.getCustomerPin();
        String name = customer.getCustomerName();
        String country = customer.getCustomerCountry();
        String gst = customer.getGst();
        if(gst==null || gst.equalsIgnoreCase("") || gst.length()!=15){
            gst = "Not Registered";
        }
        PDFPrinter detailsOfReceiverPrinter = new PDFPrinter(contents, headerFont, 10);
        detailsOfReceiverPrinter.putText(a, b, "Details of Receiver (Billed to):");
        
        PDFPrinter receiverrAddressPrinter = new PDFPrinter(contents, font, 10);
        b -= 14;
        if(name.length()<=35){
            receiverrAddressPrinter.putText(a, b, name);
        }else if(name.length()>35 && name.length()<=70){
            receiverrAddressPrinter.putText(a, b, name.substring(0, 34)+"-");
            b -= 12;
            receiverrAddressPrinter.putText(a, b, name.substring(34));
        }else{
            receiverrAddressPrinter.putText(a, b, name.substring(0, 34)+"-");
            b -= 12;
            receiverrAddressPrinter.putText(a, b, name.substring(34, 68)+"-");
            b -= 12;
            receiverrAddressPrinter.putText(a, b, name.substring(68));
        }
        b -= 12;
        if(address.length()<=35){
            receiverrAddressPrinter.putText(a, b, address);
        }else if(address.length()>35 && address.length()<=70){
            receiverrAddressPrinter.putText(a, b, address.substring(0, 34)+"-");
            b -= 12;
            receiverrAddressPrinter.putText(a, b, address.substring(34));
        }else{
            receiverrAddressPrinter.putText(a, b, address.substring(0, 34)+"-");
            b -= 12;
            receiverrAddressPrinter.putText(a, b, address.substring(34, 68)+"-");
            b -= 12;
            receiverrAddressPrinter.putText(a, b, address.substring(68));
        }
        
        b -= 12;
        receiverrAddressPrinter.putText(a, b, pin+" "+city);
        b -= 12;
        receiverrAddressPrinter.putText(a, b, state+", "+country);
        b -= 12;
        headerPrinter.putText(a, b, "GSTIN: "+gst);
    }
    private PDPageContentStream newPage(PDDocument pdfDocument, PDPageContentStream contents, int rowY, int numRows) throws IOException {
		contents.close();
		PDPage pdfPage = new PDPage();
		pdfDocument.addPage(pdfPage);		
		contents = new PDPageContentStream(pdfDocument, pdfPage);
		header(pdfDocument, contents);
		printRowHeader(contents, rowY);
		printRowBackGround(contents, rowY-21, numRows);		
		return contents;
	}
    
    private boolean newPageRequired(int numPrintedRows, int rowsLeft) {
            if(numPrintedRows >= this.maxRowSize) return true;
            if(this.maxPageWithSummation < rowsLeft && rowsLeft < this.maxRowSize) {
                    if(numPrintedRows >= this.breakPoint) return true;
            }
            return false;
    }
    
    public boolean printPDF(PDDocument pdfDocument, PDPageContentStream contents, Customer customer) throws IOException{
        header(pdfDocument, contents);
        spplierDetails(contents, customer);
        int rowY = 520;
        int numPrintedRows = 0;

        int rowsLeft = rows.size();

        printRowHeader(contents, rowY);
        printRowBackGround(contents, rowY-21, 
                rowsLeft < this.maxPageWithSummation ? this.maxPageWithSummation : this.maxRowSize
                );
        int i = 1;
        Double total = 0.0;
        Double totalTax = 0.0;
        
        for (SellRecord t : rows) {	
            total = (t.getFinalPrice()*t.getQuantity())+total;
            totalTax = (((t.getPrice()-(t.getDiscount()*t.getPrice()*0.01))*t.getTax()*0.01))*t.getQuantity()+totalTax;
                numPrintedRows++;
                rowY -= 20;
                printPDF(contents, rowY, t, i);
                i++;
                if(newPageRequired(numPrintedRows, rowsLeft)) {
                        rowsLeft -= numPrintedRows;
                        numPrintedRows = 0;
                        maxRowSize = 27;
                        maxPageWithSummation = 16;
                        breakPoint = 17;
                        rowY = 600;
                        contents = newPage(pdfDocument, contents, rowY,
                                rowsLeft < this.maxPageWithSummation ? this.maxPageWithSummation : this.maxRowSize
                                );

                }
        }
        printSummery(contents, total, totalTax);
        printSignature(pdfDocument, contents);
//        printFooter(contents);
        printInvoiceTotal(contents,rowY-15,(int)Math.round(total));
        contents.close();
    return true;            
    }
    
    public void printRowBackGround(PDPageContentStream contents, int rowY, int numRows) throws IOException {
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        Color fillColor = new Color(240, 240, 240);
        contents.setNonStrokingColor(fillColor);

		boolean odd = true;
        for(int i=0; i<numRows; i++) {
	        if(odd) {
		        contents.addRect(51, rowY, 518, 20);
		        contents.fill();
	        }

        	contents.moveTo(50, rowY);
        	contents.lineTo(50, rowY+20);
        	contents.moveTo(570, rowY);
        	contents.lineTo(570, rowY+20);
        	contents.stroke();
			rowY -= 20;
			odd = !odd;
        }

    	contents.moveTo(50, rowY+20);
    	contents.lineTo(570, rowY+20);
    	contents.stroke();
	}
    
    public void printRowHeader(PDPageContentStream contents, int headerY) throws IOException {
        Color fillColor = new Color(230, 230, 230);
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        contents.setNonStrokingColor(fillColor);
        contents.addRect(50, headerY, 520, 40);
        contents.fillAndStroke();

        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter headerPrinter = new PDFPrinter(contents, font, 10);
        if(FACILITY.getFacilityState().equalsIgnoreCase(CUSTOMER.getCustomerState())){
            headerPrinter.putText(60, headerY+22, "No.");
            headerPrinter.putText(90, headerY+22, "Description of Goods");
            headerPrinter.putText(195, headerY+22, "HSN");
            headerPrinter.putText(230, headerY+22, "Price");
            headerPrinter.putText(275, headerY+22, "Unit");
            headerPrinter.putText(305, headerY+22, "Total");
            headerPrinter.putText(365, headerY+22, "CGST");
            headerPrinter.putText(350, headerY+10, "Rate");
            headerPrinter.putText(390, headerY+10, "Amt");
            headerPrinter.putText(445, headerY+22, "SGST");
            headerPrinter.putText(430, headerY+10, "Rate");
            headerPrinter.putText(470, headerY+10, "Amt");
            headerPrinter.putText(510, headerY+22, "Sub");
            headerPrinter.putText(510, headerY+10, "Total");
        }else{
            headerPrinter.putText(60, headerY+22, "No.");
            headerPrinter.putText(90, headerY+22, "Description of Goods");
            headerPrinter.putText(245, headerY+22, "HSN");
            headerPrinter.putText(280, headerY+22, "Price");
            headerPrinter.putText(330, headerY+22, "Unit");
            headerPrinter.putText(365, headerY+22, "Total");
            headerPrinter.putText(430, headerY+22, "IGST");
            headerPrinter.putText(410, headerY+10, "Rate");
            headerPrinter.putText(450, headerY+10, "Amt");
            headerPrinter.putText(510, headerY+22, "Sub");
            headerPrinter.putText(510, headerY+10, "Total");
        }
    }
    
    public void printFooter(PDPageContentStream contents) throws IOException {
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        contents.addRect(50, 35, 300, 135);
        contents.stroke();

        PDFPrinter footerLabelPrinter = new PDFPrinter(contents, PDType1Font.HELVETICA_BOLD, 12);
        PDFPrinter footerValuePrinter = new PDFPrinter(contents, PDType1Font.HELVETICA, 12);
        footerLabelPrinter.putText(50, 172, "Bank Detials");
        int rowY = 150;
        
    	footerValuePrinter.putText(55, rowY, "Bank Account Number: ");      
        footerValuePrinter.putText(185, rowY, "0812115428");  
        footerValuePrinter.putText(55, rowY-20, "Bank Branch: ");    
        footerValuePrinter.putText(185, rowY-20, "Unit No.5 & 6, Friends Centre,");    
        footerValuePrinter.putText(185, rowY-40, "Block 38/4B, Sanjay Place, ");    
        footerValuePrinter.putText(185, rowY-60, "Agra, UP, 282002");    
        footerValuePrinter.putText(55, rowY-80, "IFSC: ");  
        footerValuePrinter.putText(185, rowY-80, "KKBK0005006"); 
    }
    
    public void printSummery(PDPageContentStream contents, double amount, double tax) throws IOException {
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        Color fillColor = new Color(240, 240, 240);
        contents.setNonStrokingColor(fillColor);        

        PDFPrinter summeryLabelPrinter = new PDFPrinter(contents, PDType1Font.HELVETICA_BOLD, 8);
        PDFPrinter summeryValuePrinter = new PDFPrinter(contents, PDType1Font.HELVETICA, 12);
    	int summeryStartY = 60;
        Double actualAmount = amount-tax;
	summeryLabelPrinter.putText(50, summeryStartY+3, "Total Amount Before Tax");
        contents.addRect(160, summeryStartY, 110, 16);
        contents.stroke();
        summeryValuePrinter.putTextToTheRight(265, summeryStartY + 3, String.format("%.2f", actualAmount) + " Rs.");

	summeryLabelPrinter.putText(50, summeryStartY - 16 + 3, "Tax Amount : GST");
        contents.addRect(160, summeryStartY - 16, 110, 16);
        contents.stroke();
        summeryValuePrinter.putTextToTheRight(265, summeryStartY - 16 + 3, String.format("%.2f", tax)+" Rs.");

	summeryLabelPrinter.putText(50, summeryStartY - 32 + 3, "Total Amount After Tax");
        contents.addRect(160, summeryStartY - 32, 110, 16);
        contents.stroke();
        summeryValuePrinter.putTextToTheRight(265, summeryStartY - 32 + 3, String.format("%.2f", amount)+" Rs.");      
        
	}
    
    private void printInvoiceTotal(PDPageContentStream contents, int rowY, int total) throws IOException{
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        
        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 10);
        textPrinter.putText(240, rowY, "Total");
        textPrinter.putText(280, rowY, total+"");
        textPrinter.putText(60, rowY-20, "Total Invoice Amount in Words:");
        textPrinter.putText(220, rowY-20, NumberToWord.convertNumberToWords(total)+" Rupees only");
    }
    
    private void printSignature(PDDocument pdfDocument, PDPageContentStream contents) throws IOException{
        int summeryStartY = 170;
        PDFPrinter summeryLabelPrinter = new PDFPrinter(contents, PDType1Font.HELVETICA_BOLD, 8);
        contents.addRect(370, summeryStartY - 150, 200, 50);
        contents.stroke();
        summeryLabelPrinter.putText(380, summeryStartY - 95, "For "+FACILITY.getFacilityName());  
        summeryLabelPrinter.putText(380, summeryStartY - 110, "this is an electronically generated document");  
        summeryLabelPrinter.putText(380, summeryStartY - 120, "and does not require signature.");  
        
//        String img = "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";
//        byte[] imageByte;
//
//        BASE64Decoder decoder = new BASE64Decoder();
//        imageByte = decoder.decodeBuffer(img);
//        PDImageXObject pdImage = PDImageXObject.createFromByteArray(pdfDocument, imageByte, "logo");
//        final float width = 100f;
//        final float scale = width / pdImage.getWidth();
//        contents.drawImage(pdImage, 435, summeryStartY - 150, width, pdImage.getHeight()*scale);
    }
    
    public void printPDF(PDPageContentStream contents, int rowY, SellRecord record, int i) throws IOException {        
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        
        Double actualAmount = record.getPrice()-(record.getPrice()*record.getDiscount()*0.01);
        Double sgst = record.getTax()/2;
        Double cgst = record.getTax()/2;
        Double igst = record.getTax();
        
        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 8);
        if(FACILITY.getFacilityState().equalsIgnoreCase(CUSTOMER.getCustomerState())){
            textPrinter.putText(60, rowY+7, i+"");
            if(record.getBrand()!=null && !record.getBrand().equalsIgnoreCase("")){
                textPrinter.putText(90, rowY+11, stringLengthCut(record.getProductName(),25));
                textPrinter.putText(90, rowY+2,  stringLengthCut("("+record.getBrand()+")",25));
            }else{
                textPrinter.putText(90, rowY+7,  stringLengthCut(record.getProductName(),25));
            }
            textPrinter.putText(195, rowY+7, record.getHsn());
            textPrinter.putText(230, rowY+7, String.format("%.2f", actualAmount));
            textPrinter.putText(280, rowY+7, record.getQuantity()+"");
            textPrinter.putText(305, rowY+7, String.format("%.2f", record.getQuantity()*actualAmount));
            textPrinter.putText(350, rowY+7, sgst+"%");
            textPrinter.putText(390, rowY+7, String.format("%.2f", cgst*actualAmount*0.01*record.getQuantity()));
            textPrinter.putText(430, rowY+7, cgst+"%");
            textPrinter.putText(470, rowY+7, String.format("%.2f", sgst*actualAmount*0.01*record.getQuantity()));
            textPrinter.putText(510, rowY+7, String.format("%.2f", record.getFinalPrice()*record.getQuantity()));
        }else{
            textPrinter.putText(60, rowY+7, i+"");
            if(record.getBrand()!=null && !record.getBrand().equalsIgnoreCase("")){
                textPrinter.putText(90, rowY+11, stringLengthCut(record.getProductName(),35));
                textPrinter.putText(90, rowY+2,  stringLengthCut("("+record.getBrand()+")",35));
            }else{
                textPrinter.putText(90, rowY+7,  stringLengthCut(record.getProductName(),35));
            }
            textPrinter.putText(245, rowY+7, record.getHsn());
            textPrinter.putText(280, rowY+7, String.format("%.2f", actualAmount));
            textPrinter.putText(330, rowY+7, record.getQuantity()+"");
            textPrinter.putText(365, rowY+7, String.format("%.2f", record.getQuantity()*actualAmount));
            textPrinter.putText(410, rowY+7, igst+"%");
            textPrinter.putText(450, rowY+7, String.format("%.2f", cgst*actualAmount*0.01*record.getQuantity()));
            textPrinter.putText(510, rowY+7, String.format("%.2f", record.getFinalPrice()*record.getQuantity()));
        }
        
    }
    
    public double getTotal(List<SellRecord> trans){
        double total = 0.0;
        for(SellRecord t: trans){
            total = total + t.getFinalPrice();
        }
        return total;
    } 
    
    public String getCurrentDate() {
        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String[] date = d.toString().split("T");
        return date[0];
    }

    public String getCurrentTime() {
        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String[] date = d.toString().split("T");
        String[] time = date[1].split(("\\."));
        return time[0];
    }
    
    public String getTimeInMilis() {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }
    
    public String extractDate(ZonedDateTime d){
        return d.toString().split("T")[0];
    }
    
    public int getLastInvoiceNumber(String adminUsername) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.adminUsername = ?0 order by invoice.invoiceId DESC");
            query.setString(0, adminUsername);
            query.setMaxResults(1);
            Invoice last = (Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            if(last!=null){
                return last.getInvoiceId();
            }
            return 1;
        }catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
     private List<SellRecord> getSellRecordsByInvoiceId(int invoiceId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.invoiceId = ?0");
            q.setInteger(0, invoiceId);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private Invoice getInvoiceByIdInvoiceId(int invoiceId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.id = ?0");
            query.setInteger(0, invoiceId);
            query.setMaxResults(1);
            co.admis.model.Invoice invoice = (co.admis.model.Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private Customer getCustomerById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private Facility getFacilityById(int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Facility f WHERE f.id = ?0");
            q.setInteger(0, facilityId);
            q.setMaxResults(1);
            Facility data = (Facility)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private BillFormat getBillFormatByAdminUsername(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from BillFormat billFormat where billFormat.adminUsername = ?0");
            query.setString(0, adminUsername);
            query.setMaxResults(1);
            BillFormat billFormat = (BillFormat) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return billFormat;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private Organization getOrganizationByAdminUsername(String adminUsername) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q1 = session.createQuery("select admin.organizationId FROM Admin admin WHERE admin.username = ?0");
            Query q2 = session.createQuery("FROM Organization organization WHERE organization.id = ?0");
            q1.setString(0, adminUsername);
            q1.setMaxResults(1);
            int id = (int)q1.uniqueResult();
            q2.setInteger(0, id);
            q2.setMaxResults(1);
            Organization data = (Organization)q2.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
     
     public String getInvoiceNumbreFormat(){
         int year = 0;
         if(selectMonth<4){
             year = selectYear-1;
         }else{
             year = selectYear;
         }
         String s2 = year+"";
         String s3 = String.format("%03d", INVOICE.getInvoiceId());
         
         return BILL_FORMAT.getInvoiceNumberPrefix()+s2+s3;
     }
     
     private String uploadS3Object(String key, File uploadObject) {
        AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(
				AWS_KEY, 
				AWS_VALUE));
        s3.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).disableChunkedEncoding().build());
        s3.putObject(new PutObjectRequest(S3_BUCKET, key, uploadObject));
        return "Uploaded Successful";
    }
     
     private boolean sendEmail(String emailId, String subject, String msg, DataSource s, String fileName) {
        // Recipient's email ID needs to be mentioned.
      String to = emailId;

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", MAILJET_HOST);
      props.put("mail.smtp.port", MAILJET_PORT);

      // Get the Session object.
      javax.mail.Session session = javax.mail.Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(MAILJET_USERNAME, MAILJET_PASSWORD);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(MAILJET_FROM));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Create the message part
         MimeBodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         String content = msg;
         
         messageBodyPart.setContent(content,"text/html");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         
        messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(s));
        messageBodyPart.setFileName(fileName);

         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
         return true;
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    }
     
    private String stringLengthCut(String str, int length){
        if(str.length()<=length){
            return str;
        }else{
            return str.substring(0, length);
        }
    } 
    
    public Invoice updateInvoice(Invoice invoice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(invoice);
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
