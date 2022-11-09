package modifications;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageStorage;
import model.Pixel;

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

    if (this.fileName.substring(fileName.length() - 3).equals("ppm")) {

      savePPM(image);
    } else {

      savePopular(image);
    }
  }

  /**
   * Saves an image to one of the various popular image formats
   * (e.g., jpeg, png, etc.)
   *
   * @throws Exception - If it fails to write to the desired file name.
   */
  private void savePopular(Image image) throws Exception {

    int height = image.getHeight();
    int width = image.getWidth();

    BufferedImage bi = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    WritableRaster raster = bi.getRaster();
    int[] pixels = flattenPixels(image.getPixels(), (width * height));

    raster.setPixels(0, 0, width, height, pixels);
    bi.setData(raster);

    File file = new File(this.fileName);
    int indexOfLastDot = this.fileName.lastIndexOf('.');


    try {
      ImageIO.write(bi, this.fileName.substring(indexOfLastDot + 1), file);
    } catch (IOException e) {
      throw new Exception(String.valueOf(e));
    }

  }

  /**
   * Flattens a 2D array of pixels into a 1D array of RGB values of each pixel
   * in order in row-column order.
   *
   * @param pixels - 2D array of pixels from an image.
   * @param size   - Number of pixels in the image (width * height).
   * @return
   */
  private int[] flattenPixels(Pixel[][] pixels, int size) {

    int index = 0;
    int[] res = new int[size * 3];

    for (Pixel[] row : pixels) {

      for (Pixel pixel : row) {

        for (int color : pixel.toList()) {

          res[index] = color;
          index++;
        }
      }
    }

    return res;
  }

  /**
   * Saves an image to the PPM file format.
   *
   * @throws Exception - If it fails to write to the file.
   */
  private void savePPM(Image image) throws Exception {

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
