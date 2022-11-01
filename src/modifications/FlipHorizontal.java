package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to flip a .ppm image horizontally.
 */
public class FlipHorizontal implements PPMModification{

  /**
   * flip the image horizontally.
   * @param image the original image to be flipped
   * @return a new image that is the same as the original but flipped horizontally
   */
  public Image modifyImage(String args, Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] originalPixels = image.getPixels();
    //create a new 2d list of Pixels
    Pixel[][] newPixels = new Pixel[height][width];
    //go through all pixels in the original image
    for (int i = 0; i < height; i ++) {
      for (int j = 0; j < width; j ++) {
        newPixels[i][width - j] = originalPixels[i][j].clone();
      }
    }
    return new Image(height, width, newPixels);
  }
}
