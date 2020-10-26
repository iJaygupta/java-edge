/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

public class BarcodePlacement {
//    public static final String DEST = "results/barcodes/barcode_placement.pdf";
// 
//    public static void main(String[] args) throws IOException, DocumentException {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//        new BarcodePlacement().createPdf(DEST);
//    }
// 
//    public void createPdf(String dest) throws IOException, DocumentException {
//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
//        document.open();
//        PdfContentByte cb = writer.getDirectContent();
//        Image img = createBarcode(cb, "This is a 2D barcode", 1, 1);
//        document.add(new Paragraph(String.format("This barcode measures %s by %s user units", img.getScaledWidth(), img.getScaledHeight())));
//        document.add(img);
//        img = createBarcode(cb, "This is NOT a raster image", 3, 3);
//        document.add(new Paragraph(String.format("This barcode measures %s by %s user units", img.getScaledWidth(), img.getScaledHeight())));
//        document.add(img);
//        img = createBarcode(cb, "This is vector data drawn on a PDF page", 1, 3);
//        document.add(new Paragraph(String.format("This barcode measures %s by %s user units", img.getScaledWidth(), img.getScaledHeight())));
//        document.add(img);
//        document.close();
//    }
// 
//    public Image createBarcode(PdfContentByte cb, String text, float mh, float mw) throws BadElementException {
//        BarcodePDF417 pf = new BarcodePDF417();
//        pf.setText("BarcodePDF417 barcode");
//        Rectangle size = pf.getBarcodeSize();
//        PdfTemplate template = cb.createTemplate(mw * size.getWidth(), mh * size.getHeight());
//        pf.placeBarcode(template, BaseColor.BLACK, mh, mw);
//        return Image.getInstance(template);
//    }
}