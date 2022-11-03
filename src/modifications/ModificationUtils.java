package modifications;

import Model.Image;
import Model.ImageStorage;

public class ModificationUtils {
  public static Image getImage(ImageStorage imgStorage, String imgName) throws Exception {
    Image image;

    try {
      image = imgStorage.getImage(imgName);
    }catch (Exception e){
      throw new Exception(String.valueOf(e));
    }
    return new Image(image);
  }
}
