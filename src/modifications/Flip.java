package modifications;

import Model.Image;
import Model.Pixel;

public class Flip implements PPMModification {
  /**
   * flip the image.
   *
   * @param image the original image to be flipped
   * @return a new image that is the same as the original but flipped
   */
  public Image modifyImage(String arg, Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] originalPixels = image.getPixels();
    //create a new 2d list of Pixels
    Pixel[][] newPixels = new Pixel[height][width];
    //go through all pixels in the original image
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        if (arg.equals("horizontal")) {
          newPixels[r][width - c - 1] = originalPixels[r][c].clone();
        }
        else if (arg.equals("vertical")) {
          newPixels[height - r - 1][c] = originalPixels[r][c].clone();
        }


      }
    }
    return new Image(height, width, newPixels);
  }
}

