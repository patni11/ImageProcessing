package modifications;

import java.io.FileNotFoundException;

import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;

/**
 * Loads an image into storage provided a locaiton to image file in directory.
 */
public class Load implements PPMModification {
  private final ImageStorage imgStorage;
  String fileName;
  String destName;

  /**
   * constructor to create Load object that can store images. This is for testing.
   *
   * @param storage stores images
   */
  public Load(ImageStorage storage) {
    this.imgStorage = storage;
  }

  /**
   * constructor to create Load object that can store images.
   *
   * @param storage  stores images
   * @param fileName takes in a file to load
   * @param destName saves the image with destName
   */
  public Load(ImageStorage storage, String fileName, String destName) {
    this.imgStorage = storage;
    this.fileName = fileName;
    this.destName = destName;
  }

  @Override
  public void runFunction() throws Exception {
    Image img;

    try {
      img = ImageUtil.readPPM(this.fileName);
    } catch (FileNotFoundException e) {
      throw new Exception(String.valueOf(e));
    }

    this.imgStorage.addImage(this.destName, img);
  }

  /**
   * this is for testing.
   *
   * @param arg   takes in name for the image
   * @param image stores the image with the given name.
   * @return
   */
  public Image modifyImage(String arg, Image image) {
    this.imgStorage.addImage(arg, image);
    return image;
  }
}

