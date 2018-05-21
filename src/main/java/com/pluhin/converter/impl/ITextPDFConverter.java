package com.pluhin.converter.impl;

import static com.itextpdf.tool.xml.XMLWorkerHelper.parseToElementList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pluhin.converter.PDFConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * {@link PDFConverter} implementation using IText pdf converter.
 */
public class ITextPDFConverter implements PDFConverter {

  private static final Integer PDF_COL_NUM = 1;

  /**
   * {@inheritDoc}
   * @param htmlFile input html file to be converted to PDF
   * @param destFilePath path to locate destination PDF
   * @throws IOException in case with saving file error
   * @throws DocumentException in case with writing data to document error
   */
  @Override
  public void convert(File htmlFile, String destFilePath)
      throws IOException, DocumentException {
    Document document = new Document();

    try (OutputStream outputStream = new FileOutputStream(destFilePath)) {
      PdfWriter.getInstance(document, outputStream);
      document.open();

      PdfPTable pdfPTable = new PdfPTable(PDF_COL_NUM);
      PdfPCell cell = new PdfPCell();

      String content = getContentFromHtmlFile(htmlFile);

      parseToElementList(content, null)
          .stream()
          .filter(ColumnText::isAllowedElement)
          .forEach(cell::addElement);

      pdfPTable.addCell(cell);
      document.add(pdfPTable);
      document.close();
    }
  }
}
