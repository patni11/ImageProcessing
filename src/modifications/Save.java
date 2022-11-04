package modifications;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import Model.Image;
import Model.ImageStorage;

/**
 * Saves an image to a ppm file.
 */
public class Save implements PPMModification {

  private final ImageStorage imgStorage;
  private final String fileName;
  private final String imgName;

  /**
   * constructor to create save class that can save images to ppm files.
   *
   * @param imgStorage takes in storage to get the image from.
   * @param fileName   saves as this file name.
   * @param imgName    name of the image you want to save.
   */
  public Save(ImageStorage imgStorage, String fileName, String imgName) {
    this.imgStorage = imgStorage;
    this.fileName = fileName;
    this.imgName = imgName;
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(imgStorage, imgName);

    String imageString = image.toString();
    StringReader sc = new StringReader(imageString);

    FileWriter writer = null;

    try {
      writer = new FileWriter(fileName);
    } catch (IOException e) {
      System.out.print(e);
    }

    int k = 0;
    if (writer != null) {

      try {
        while ((k = sc.read()) != -1) {
          writer.write((char) k);
        }
        writer.close();
      } catch (IOException e) {
        System.out.println(e);
      }

    }
  }
}
