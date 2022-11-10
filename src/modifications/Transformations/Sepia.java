package modifications.Transformations;

import model.ImageStorage;
import model.TransformModels.GreyscaleTransformModel;
import model.TransformModels.SepiaTransformModel;

public class Sepia extends Transformations {
  public Sepia(ImageStorage imageStorage, String imageName, String destName) {
    super(imageStorage, imageName, new SepiaTransformModel(), destName);
  }
}

