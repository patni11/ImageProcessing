package modifications;

import model.Image;
import model.ImageStorage;
import model.Kernel;

public class Filter implements PPMModification {

  private ImageStorage imgStore;

  private String destName;
  private String imgName;
  private Kernel kernel;

  public Filter(ImageStorage imageStorage, String imageName,
                Kernel kernel, String destName) {
    this.imgStore = imageStorage;
    this.imgName = imageName;
    this.kernel = kernel;
    this.destName = destName;
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, blurImage(image));
  }

  private Image blurImage(Image img) {


  }
}
