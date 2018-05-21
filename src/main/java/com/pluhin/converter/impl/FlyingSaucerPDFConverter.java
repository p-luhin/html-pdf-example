package com.pluhin.converter.impl;

import com.lowagie.text.DocumentException;
import com.pluhin.converter.PDFConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * {@link PDFConverter} implementation uses FlyingSaucer pdf converter.
 */
public class FlyingSaucerPDFConverter implements PDFConverter {

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
    try (OutputStream outputStream = new FileOutputStream(destFilePath)) {
      ITextRenderer renderer = new ITextRenderer();

      String content = getContentFromHtmlFile(htmlFile);
      renderer.setDocumentFromString(content);
      renderer.layout();
      renderer.createPDF(outputStream);
    }
  }
}
