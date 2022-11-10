package modifications.Transformations;

import model.ImageStorage;
import model.TransformModels.GreyscaleTransformModel;

public class GreyscaleTransform extends Transformations{
  public GreyscaleTransform(ImageStorage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new GreyscaleTransformModel(), destName);
  }
}
