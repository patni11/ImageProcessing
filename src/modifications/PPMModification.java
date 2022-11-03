package modifications;

import java.io.IOException;

import Model.Image;
import Model.Pixel;

/**
 * an interface to be implemented by the image modification functions
 */
public interface PPMModification {
  void go() throws Exception;
}
