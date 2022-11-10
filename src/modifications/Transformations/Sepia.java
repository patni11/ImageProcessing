package modifications.Transformations;

import model.ImageStorage;
import model.TransformModels.GreyscaleTransformModel;
import model.TransformModels.SepiaTransformModel;

/**
 * Function object to transform an image by applying the sepia
 * transformation to it.
 */
public class Sepia extends Transformations {

  /**
   * Constructor for Sepia.
   *
   * @param imageStorage - Image storage where images are currently being stored
   *                     *                     and retrieved.
   * @param imageName    - Name of the original image to apply the sepia filter to.
   * @param destName     - Resulting image name after the sepia filter has been applied
   *                     to the original image.
   */
  public Sepia(ImageStorage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new SepiaTransformModel(), destName);
  }
}

