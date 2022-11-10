package modifications;

import model.Image;
import model.ImageStorage;
import model.KernalModels.Kernel;

public abstract class AbstractTransform implements PPMModification{
  protected ImageStorage imgStore;

  protected String destName;
  protected String imgName;
  protected Kernel kernel;

  public AbstractTransform(ImageStorage imageStorage, String imageName,
                Kernel kernel, String destName) {
    this.imgStore = imageStorage;
    this.imgName = imageName;
    this.kernel = kernel;
    this.destName = destName;
  }

  public AbstractTransform() {

  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, applyFunction(image));
  }

  protected Image applyFunction(Image image){
    return image;
  }


}
