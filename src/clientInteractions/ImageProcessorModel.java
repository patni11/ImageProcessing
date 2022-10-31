package clientInteractions;

import java.io.File;

public class ImageProcessorModel {

  private File filePath;
  private String fileName;

  public ImageProcessorModel(File path, String name) {
    this.filePath = path;
    this.fileName = name;
  }

}
