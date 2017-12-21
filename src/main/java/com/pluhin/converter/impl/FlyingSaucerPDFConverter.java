package com.pluhin.converter.impl;

import com.lowagie.text.DocumentException;
import com.pluhin.converter.PDFConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class FlyingSaucerPDFConverter implements PDFConverter {

  @Override
  public void convert(File htmlFile, String destFilePath)
      throws IOException, DocumentException {
    try (OutputStream outputStream = new FileOutputStream(destFilePath)) {
      ITextRenderer renderer = new ITextRenderer();

      String content = getContentFromHtmlFile(htmlFile);
      renderer.setDocumentFromString(content);
      renderer.layout();
      renderer.createPDF(outputStream);
    }
  }
}
