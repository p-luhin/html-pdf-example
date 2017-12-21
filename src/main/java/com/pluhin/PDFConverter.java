package com.pluhin;

import static com.itextpdf.tool.xml.XMLWorkerHelper.parseToElementList;
import static java.nio.file.Files.lines;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PDFConverter {

  private static final Integer PDF_COL_NUM = 1;

  public void process(File htmlFile, String destFilePath)
      throws IOException, DocumentException {
    Document document = new Document();

    try (FileOutputStream outputStream = new FileOutputStream(destFilePath)) {
      PdfWriter.getInstance(document, outputStream);
      document.open();

      PdfPTable pdfPTable = new PdfPTable(PDF_COL_NUM);
      PdfPCell cell = new PdfPCell();

      StringBuilder htmlContent = new StringBuilder();

      lines(htmlFile.toPath()).forEach(htmlContent::append);

      parseToElementList(htmlContent.toString(), null)
          .stream()
          .filter(ColumnText::isAllowedElement)
          .forEach(cell::addElement);

      pdfPTable.addCell(cell);
      document.add(pdfPTable);
      document.close();
    }
  }

}
