package com.pluhin.converter;

import static java.nio.file.Files.lines;

import java.io.File;
import java.io.IOException;

public interface PDFConverter {

  /**
   * Reads input html file content and returns it as string.
   *
   * @param htmlFile input html file to read content
   * @return content of given html file
   * @throws IOException in case of any error with reading file
   */
  default String getContentFromHtmlFile(File htmlFile) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();

    lines(htmlFile.toPath()).forEach(stringBuilder::append);

    return stringBuilder.toString();
  }

  /**
   * Converts given html file to PDF and outputs in destination file path.
   *
   * @param htmlFile input html file to be converted to PDF
   * @param destFilePath path to locate destination PDF
   * @throws Exception in any errors during converting
   */
  void convert(File htmlFile, String destFilePath) throws Exception;
}
