package modifications;

import Model.Image;
import Model.ImageStorage;

public class Load implements PPMModification{

  private ImageStorage imageStorage;
  public void Load(ImageStorage imageStorage){
    this.imageStorage = imageStorage;
  }

  @Override
  public Image modifyImage(String arg, Image image) {
    this.imageStorage.addImage(arg,image);
    return image;
  }
}

