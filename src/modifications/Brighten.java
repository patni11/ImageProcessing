package modifications;

import model.Image;
import model.ImageStorage;
import model.Pixel;

/**
 * a function object to brighten a saved image.
 */
public class Brighten implements PPMModification {

  private int increment;
  private String imgName;
  private String destName;
  private ImageStorage imgStorage;

  /**
   * Constructor to create a birghten class that can brighten an image given the params.
   *
   * @param imgStorage to get and save an image
   * @param increment  amount of brightness
   * @param imgName    name of the image to modify
   * @param destName   saving as destname after modifying
   */
  public Brighten(ImageStorage imgStorage, int increment, String imgName, String destName) {
    this.increment = increment;
    this.imgStorage = imgStorage;
    this.imgName = imgName;
    this.destName = destName;
  }


  /**
   * This is specifically for testing.
   */
  public Brighten() {
    //this is to test the functionality of brighten method.
  }


  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(imgStorage, this.imgName);
    this.imgStorage.addImage(destName, brightenImage(image));
  }

  private Image brightenImage(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    Pixel[][] pixels = image.getPixels();

    Pixel[][] newPixels = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int newR = getVal(pixels[r][c].getR());
        int newG = getVal(pixels[r][c].getG());
        int newB = getVal(pixels[r][c].getB());

        newPixels[r][c] = new Pixel(newR, newG, newB);
      }
    }
    return new Image(height, width, newPixels);
  }

  private int getVal(int val) {
    if ((val + this.increment) >= 255) {
      return 255;
    }

    if ((val + this.increment) <= 0) {
      return 0;
    }
    return (val + this.increment);
  }

  /**
   * This applies the brigten function. Method is for testing.
   *
   * @param arg   takes in increment as string
   * @param image takes in an image to brighten
   * @return
   */
  public Image modifyImage(String arg, Image image) {

    this.increment = Integer.parseInt(arg);
    return brightenImage(image);
  }


}
