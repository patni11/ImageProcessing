package modifications;

import Model.Image;
import Model.ImageStorage;
import Model.Pixel;

/**
 * Flips class to flip a given image vertically or horizontally.
 */
public class Flip implements PPMModification {
  private String flipType;
  private String imgName;
  private String destName;

  private ImageStorage imgStorage;

  /**
   * Flips a given image based on flip type which can be vertical or horizontal.
   *
   * @param imgStorage takes in a place to store and access image.
   * @param flipType   type of flip
   * @param imgName    name of image to modify
   * @param destName   saving modify image as dest name
   */
  public Flip(ImageStorage imgStorage, String flipType, String imgName, String destName) {
    this.imgStorage = imgStorage;
    this.flipType = flipType;
    this.imgName = imgName;
    this.destName = destName;
  }


  /**
   * this is for testing.
   */
  public Flip() {
    // this is for testing.
  }

  /**
   * flips a image provided in the construcotr based on flip type.
   *
   * @throws Exception if the image does not exist.
   */
  public void runFunction() throws Exception {

    Image image = ModificationUtils.getImage(imgStorage, imgName);
    this.imgStorage.addImage(destName, flipImage(image));

  }

  private Image flipImage(Image image) {
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
        } else if (flipType.equals("vertical")) {
          newPixels[height - r - 1][c] = originalPixels[r][c].clone();
        }
      }
    }
    return new Image(height, width, newPixels);
  }

  /**
   * this is for testing.
   *
   * @param args  takes flipType
   * @param image takes an image to flip.
   * @return
   */
  public Image modifyImage(String args, Image image) {
    this.flipType = args;
    return flipImage(image);
  }
}

