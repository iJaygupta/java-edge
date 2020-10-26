/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.pdf.service;

import co.admis.pdf.model.InvoiceRowData;
import java.awt.Color;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author dell
 */
public interface PdfBoxService {
//    for top left xPosition = 50 and yPosition = 720
    public boolean setImage(PDDocument pdfDocument, PDPageContentStream contents, String base64image, int xPosition, int yPosition);
    
    public boolean setText(PDPageContentStream contents, PDFont gstFont, Color color, int fontSize, int xPosition, int yPosition, String text);
    
    public boolean setLongString(PDPageContentStream contents, PDFont gstFont, Color color, int fontSize, int xPosition, int yPosition, int ySpace, int characterLimitPerLine, String text);
    
    public boolean addRectangle(PDPageContentStream contents, Color nonStrokingColor, Color strokingColor, int xPosition, int yPosition, int xLength, int yLength);
    
    public boolean addRectangleWithText(PDPageContentStream contents, Color nonStrokingColor, Color strokingColor, int xPosition, int yPosition, int xLength, int yLength, PDFont font, int fontSize, Color fontColor, String text, int xSpacing, int ySpacing);
    
    public boolean addTableRow(PDPageContentStream contents, Color nonStrokingColor, Color strokingColor, int xPosition, int yPosition, int xLength, int yLength, PDFont font, int fontSize, Color fontColor, List<InvoiceRowData> list, int xSpacing, int ySpacing);
}
