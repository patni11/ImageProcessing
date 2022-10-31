package modifications;

import Model.Image;
import Model.Pixel;

/**
 * an interface to be implemented by the image modification functions
 */
public interface PPMModification {

  Image modifyImage(Image image);
}
