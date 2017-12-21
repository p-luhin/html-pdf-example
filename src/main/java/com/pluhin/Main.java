package com.pluhin;

import com.pluhin.converter.PDFConverter;
import com.pluhin.converter.impl.FlyingSaucerPDFConverter;
import java.io.File;
import java.io.IOException;

public class Main {

  private static PDFConverter CONVERTER = new FlyingSaucerPDFConverter();

  private static final String SOURCE_FILE_PATH = "src/main/resources/html/input.html";
  private static final String DEST_FILE_PATH = "src/main/resources/pdf/output.pdf";

  public static void main(String[] args) {
    prepareOutput(DEST_FILE_PATH);
    File file = readFile(SOURCE_FILE_PATH);

    try {
      CONVERTER.convert(file, DEST_FILE_PATH);
    } catch (IOException e) {
      System.out.println("ERROR: Reading input or writing to output");
      throw new RuntimeException(e);
    } catch (Exception e) {
      System.out.println("ERROR: Creating pdf");
      throw new RuntimeException(e);
    }
  }

  private static File readFile(String filePath) {
    File file = new File(filePath);
    assert file.exists();
    assert file.canRead();

    return file;
  }

  private static void prepareOutput(String filePath) {
    File file = new File(filePath);
    file.getParentFile().mkdirs();
  }

}
