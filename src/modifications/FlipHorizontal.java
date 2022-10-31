package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to flip a .ppm image horizontally.
 */
public class FlipHorizontal implements PPMModification{

  public Image modifyImage(Image image) {
    return new Image(4,5, new Pixel[][]{});
  }
}
