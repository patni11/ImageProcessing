package modifications.Filters;

import model.ImageStorage;
import model.KernalModels.BlurKernel;

public class BlurFilter extends Filter {

  public BlurFilter(ImageStorage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new BlurKernel(), destName);
  }
}
