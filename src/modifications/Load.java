package modifications;

import java.io.FileNotFoundException;

import Model.Image;
import Model.ImageStorage;
import clientInteractions.ImageUtil;

public class Load implements PPMModification{

  private ImageStorage imgStorage;
  String fileName;
  String destName;

  public Load(ImageStorage storage) {
    this.imgStorage = storage;
  }

  public Load(ImageStorage storage, String fileName, String destName) {
    this.imgStorage = storage;
    this.fileName = fileName;
    this.destName = destName;
  }

  @Override
  public void go() throws Exception{
    Image img;

    try{
      img = ImageUtil.readPPM(this.fileName);
    }catch (FileNotFoundException e){
      throw new Exception(String.valueOf(e));
    }

    this.imgStorage.addImage(this.destName,img);
  }

  public Image modifyImage(String arg, Image image) {
    this.imgStorage.addImage(arg,image);
    return image;
  }
}

