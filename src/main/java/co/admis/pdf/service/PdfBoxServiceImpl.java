/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.pdf.service;

import co.admis.pdf.dao.PdfBoxDao;
import co.admis.pdf.model.InvoiceRowData;
import java.awt.Color;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class PdfBoxServiceImpl implements PdfBoxService{
    @Autowired
    PdfBoxDao pdfBoxDao;
    
    @Override
    public boolean setImage(PDDocument pdfDocument, PDPageContentStream contents, String base64image, int xPosition, int yPosition) {
        return pdfBoxDao.setImage(pdfDocument, contents, base64image, xPosition, yPosition);
    }

    @Override
    public boolean setText(PDPageContentStream contents, PDFont gstFont, Color color, int fontSize, int xPosition, int yPosition, String text) {
        return pdfBoxDao.setText(contents, gstFont, color, fontSize, xPosition, yPosition, text);
    }

    @Override
    public boolean setLongString(PDPageContentStream contents, PDFont gstFont, Color color, int fontSize, int xPosition, int yPosition, int ySpace, int characterLimitPerLine, String text) {
        return pdfBoxDao.setLongString(contents, gstFont, color, fontSize, xPosition, yPosition, ySpace, characterLimitPerLine, text);
    }

    @Override
    public boolean addRectangle(PDPageContentStream contents, Color nonStrokingColor, Color strokingColor, int xPosition, int yPosition, int xLength, int yLength) {
        return pdfBoxDao.addRectangle(contents, nonStrokingColor, strokingColor, xPosition, yPosition, xLength, yLength);
    }

    @Override
    public boolean addRectangleWithText(PDPageContentStream contents, Color nonStrokingColor, Color strokingColor, int xPosition, int yPosition, int xLength, int yLength, PDFont font, int fontSize, Color fontColor, String text, int xSpacing, int ySpacing) {
        return pdfBoxDao.addRectangleWithText(contents, nonStrokingColor, strokingColor, xPosition, yPosition, xLength, yLength, font, fontSize, fontColor, text, xSpacing, ySpacing);
    }

    @Override
    public boolean addTableRow(PDPageContentStream contents, Color nonStrokingColor, Color strokingColor, int xPosition, int yPosition, int xLength, int yLength, PDFont font, int fontSize, Color fontColor, List<InvoiceRowData> list, int xSpacing, int ySpacing) {
        return pdfBoxDao.addTableRow(contents, nonStrokingColor, strokingColor, xPosition, yPosition, xLength, yLength, font, fontSize, fontColor, list, xSpacing, ySpacing);
    }
    
}
