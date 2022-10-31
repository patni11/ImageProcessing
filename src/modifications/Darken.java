package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to darken a .ppm image.
 */
public class Darken implements PPMModification {

  public Image modifyImage(Image image) {
    return new Image(4,5, new Pixel[][]{});
  }
}
