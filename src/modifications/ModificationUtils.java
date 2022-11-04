package modifications;

import Model.Image;
import Model.ImageStorage;

/**
 * Class to help with modifications.
 */
public class ModificationUtils {
  /**
   * gets an image from the storage.
   *
   * @param imgStorage takes in a storage.
   * @param imgName    name of the image you want.
   * @return Image in the storage
   * @throws Exception if the image does not exist in storage.
   */
  public static Image getImage(ImageStorage imgStorage, String imgName) throws Exception {
    Image image;

    try {
      image = imgStorage.getImage(imgName);
    } catch (Exception e) {
      throw new Exception(String.valueOf(e));
    }
    return new Image(image);
  }
}
