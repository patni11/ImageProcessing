package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to convert a .ppm image to greyscale (set rgb to average of rgb values?).
 */
public class Greyscale implements PPMModification {
  public Image modifyImage(Image image) {
    return new Image(4,5, new Pixel[][]{});
  }
}
