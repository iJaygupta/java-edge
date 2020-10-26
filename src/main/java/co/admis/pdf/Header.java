package co.admis.pdf;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.json.simple.JSONObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.awt.Color;
import java.io.IOException;
import sun.misc.BASE64Decoder;

public class Header {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Date invoiceDate;
	private String invoiceNumber;

    public Header(JSONObject jsonHeader) {
        if(jsonHeader.containsKey("invoiceDate")) {
            this.setInvoiceDate((String)jsonHeader.get("invoiceDate"));
        }
        if(jsonHeader.containsKey("invoiceNumber")) {
            this.setInvoiceNumber((String)jsonHeader.get("invoiceNumber"));
        }
    }

    public void setInvoiceDate(String invoiceDate) {
    	try {
    		this.invoiceDate = sdf.parse(invoiceDate);
    	} catch(ParseException pe) {
    		pe.printStackTrace();
    		this.invoiceDate = null;
    	}        
    }
    public String getInvoiceDateString() {
        return sdf.format(this.invoiceDate);
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public Date getInvoiceDate() {
        return this.invoiceDate;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void printPDF(PDDocument pdfDocument, PDPageContentStream contents) throws IOException {        
        String img = "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAMAAABrrFhUAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAYBQTFRF5eXr/f39p6W73t3l/vfR4lWG3kJ1/vng67nE/OqH8ezs+fn66ai5/ORoKClf3CtnOTlr5HeYvLvM+tYZ09Ld0bAkhoWkkpOuyMnW7crS8OTl55WstZk1RkZ1/fCt5oqk+9s13DNs79rc9PT0ZmaMLC1h4EZ74WGJWVmD+Njf7MLL3Tlw9vb27OvvxcXSMEJf2dfi6J2xbGuRsrHEmpmz//3xVFR/7tbZ2x9izMzY8fH17tDV5YOeVUtX/PHyfHucSlp0VWF6aVtTg4qdgn+edHSX5cAaa3aLrK3A+95K9vb59M0SPzpc42uQvb/Kwb/Onoc75H2bXWqB6rHB309++uXnwcHQ+tQQz8/alZurHjVU++rt/vn5xsfTjo2q9PT2eXeZr7PAMDBkb2+TtrXHX1+HP09qTk56dneZdniaoaG5+Pf58ejo+/v7mZ+uubjJ9/f3P0Bw//75h4+h1tbe8e/wdGRQjnlB8N/hop+3+tMK2hlg8vLy////JCZd////hTKUgwAAAIB0Uk5T/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wA4BUtnAAAOAUlEQVR42uyd/1/axh/HExFKQSNYigq1VBThA9KCtoJCZ7dVa21Z6/Sjzq3dClI61E9lM59ZgfCvL5AAScjFfLmAIff6zT5ky+vJ3b3f9753TqxhcmEIAAKAACAACAACgAAgAAiA8VTHcRMDqOPOpfmlukkB0O7n52u12jxuRgA4676pc9MBoM3XOFoyFwD8lOceyhzAjDPte9xDmQPGAICfX4i5p3VhAgBg9zDmAHbLx/25c6kmpWEGQAd7ia++rdMhBdBK9GoypHUI3EoA3FTHfABwOt7Jdd+Uc6gAKHXfTAbrQwMAx0GL3tnMHqnTHMBuzaIHXPGDSWu6mtVrDtwKABKpTiawX20qKTEHDA4AHO/ImexilZVfIg4YGQAOTvTs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";
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(img);
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(pdfDocument, imageByte, "logo");
        final float width = 60f;
        final float scale = width / pdImage.getWidth();
        contents.drawImage(pdImage, 50, 720, width, pdImage.getHeight()*scale);
        
        PDFont gstFont = PDType1Font.HELVETICA_BOLD;
        PDFPrinter gstPrinter = new PDFPrinter(contents, gstFont, 12);
        gstPrinter.putText(50, 710, "GSTIN: 09AABCW9266E1ZZ");
        
        PDFont headerFont = PDType1Font.HELVETICA_BOLD;
        PDFPrinter headerPrinter = new PDFPrinter(contents, headerFont, 14);
        headerPrinter.putText(120, 762, "Waytm Tele Services Private Limited");

        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 10);
        textPrinter.putText(120, 750, "9/6, Shivaji Market, Bijlighar");
        textPrinter.putText(120, 738, "Agra, UP, 282005");
        textPrinter.putText(120, 726, "+91 9528618433, www.waytm.info");

        Color color = new Color(200, 200, 200);
        PDFPrinter invoiceHeaderPrinter = new PDFPrinter(contents, font, 24, color);
        invoiceHeaderPrinter.putText(450, 740, "INVOICE");     

        textPrinter.putText(400, 710, "Invoice date:");
        textPrinter.putText(400, 698, "Invoice number:");
        textPrinter.putText(500, 710, this.getInvoiceDateString());
        textPrinter.putText(500, 698, this.getInvoiceNumber());
    }
}