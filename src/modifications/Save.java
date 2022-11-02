package modifications;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import Model.Image;

public class Save implements PPMModification{

  @Override
  public Image modifyImage(String name, Image image) {
    String imageString = image.toString();
    StringReader sc = new StringReader(imageString);

    FileWriter writer = null;

    try {
      writer = new FileWriter(name);
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
      System.out.println("Done Writing");

    }
    return image;
  }
}
