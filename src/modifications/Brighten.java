package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to brighten a .ppm image.
 */
public class Brighten implements PPMModification {

  public Image modifyImage(Image image) {
    return new Image(4,5, new Pixel[][]{});
  }
}
