package modifications;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import Model.Image;
import Model.ImageStorage;

public class Save implements PPMModification{

  private ImageStorage imgStorage;
  private String fileName;
  private String imgName;

  public Save(ImageStorage imgStorage, String fileName, String imgName) {
    this.imgStorage = imgStorage;
    this.fileName = fileName;
    this.imgName = imgName;
  }

  @Override
  public void go() throws Exception {
    Image image = ModificationUtils.getImage(imgStorage,imgName);

    String imageString = image.toString();
    StringReader sc = new StringReader(imageString);

    FileWriter writer = null;

    try {
      writer = new FileWriter(fileName);
    } catch (IOException e) {
      System.out.print(e);
    }

    int k=0;
    if (writer != null){

      try{
        while((k=sc.read())!=-1){
          writer.write((char)k);
        }
        writer.close();
      }catch (IOException e){
        System.out.println(e.toString());
      }

    }
  }
}
