package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to flip a .ppm image vertically.
 */
public class FlipVertical implements PPMModification {

  /**
   * flip the image vertically.
   * @param image the original image to be flipped
   * @return a new image that is the same as the original but flipped vertically
   */
  public Image modifyImage(Image image) {
    int width = image.getMetaData()[0];
    int height = image.getMetaData()[1];
    Pixel[][] originalPixels = image.getPixels();
    //create a new 2d list of Pixels
    Pixel[][] newPixels = new Pixel[height][width];
    //go through all pixels in the original image
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newPixels[height - i][j] = originalPixels[i][j].clone();
      }
    }
    return new Image(height, width, newPixels);
  }
}
