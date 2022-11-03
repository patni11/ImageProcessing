package modifications;

import Model.Image;
import Model.ImageStorage;
import Model.Pixel;

public class Flip implements PPMModification {
  private String flipType;
  private String imgName;
  private String destName;

  private ImageStorage imgStorage;
  public Flip(ImageStorage imgStorage, String flipType, String imgName, String destName) {
    this.imgStorage = imgStorage;
    this.flipType = flipType;
    this.imgName = imgName;
    this.destName = destName;
  }

  /**
   * flip the image.
   *
   * @return a new image that is the same as the original but flipped
   */
  public void go() throws Exception {

    Image image = ModificationUtils.getImage(imgStorage,imgName);

    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] originalPixels = image.getPixels();
    //create a new 2d list of Pixels
    Pixel[][] newPixels = new Pixel[height][width];
    //go through all pixels in the original image
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        if (flipType.equals("horizontal")) {
          newPixels[r][width - c - 1] = originalPixels[r][c].clone();
        }
        else if (flipType.equals("vertical")) {
          newPixels[height - r - 1][c] = originalPixels[r][c].clone();
        }
      }
    }

    this.imgStorage.addImage(destName,new Image(height, width, newPixels));

  }
}

