package modifications.Filters;

import model.ImageStorage;
import model.KernalModels.BlurKernel;

/**
 * Represents a Blur Filter to apply onto images.
 */
public class BlurFilter extends Filter {

  /**
   * Constructor for a blur filter to be executed on an image.
   *
   * @param imageStorage - Image storage where images are currently being stored
   *                     and retrieved.
   * @param imageName    - The original image name before the blur filter is applied.
   * @param destName     - The target or end result name of the image after the filter is applied.
   */
  public BlurFilter(ImageStorage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new BlurKernel(), destName);
  }
}
