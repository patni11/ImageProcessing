package modifications.Filters;

import model.ImageStorage;
import model.KernalModels.SharpenKernel;

public class SharpenFilter extends Filter {
  public SharpenFilter(ImageStorage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new SharpenKernel(), destName);
  }
}
