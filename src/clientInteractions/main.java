package clientInteractions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

import Model.Image;
import Model.ImageStorage;
import modifications.Brighten;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.Save;

/**
 * The main class to run the program from. Collects user input and delegates functionality.
 */
public class main {

  public static void main(String[] args) throws IOException {
    ImageStorage imgStorage = new ImageStorage();
    Scanner sc = new Scanner(InputStream.nullInputStream());
    Map<String, BiFunction<String,Image, PPMModification>> knownCommands = new HashMap<>();

    knownCommands.put("load",(String name, Image img) -> {return new Load(imgStorage);});
    knownCommands.put("brighten",(String name, Image img) -> {return new Brighten();});
    knownCommands.put("vertical-flip",(String name, Image img) -> {return new Flip();});
    knownCommands.put("horizontal-flip",(String name, Image img) -> {return new Flip();});
    knownCommands.put("value-component",(String name, Image img) -> {return new Greyscale();});
    knownCommands.put("red-component",(String name, Image img) -> {return new Greyscale();});
    knownCommands.put("blue-component",(String name, Image img) -> {return new Greyscale();});
    knownCommands.put("green-component",(String name, Image img) -> {return new Greyscale();});
    knownCommands.put("luma-component",(String name, Image img) -> {return new Greyscale();});
    knownCommands.put("intensity-component",(String name, Image img) -> {return new Greyscale();});
    knownCommands.put("save",(String name, Image img) -> {return new Save();});

    Image image = null;
    String name = null;

    while (sc.hasNext()) {
      String in = sc.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit"))
        return;

      BiFunction<String,Image, PPMModification> cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null){
        throw new IllegalArgumentException("Invalid Command");
      }else{
        if (in.equals("brighten")){
          try {
            int intensity = sc.nextInt();
            String imgName = sc.next();
            String destName = sc.next();
            PPMModification c = cmd.apply(Integer.toString(intensity), imgStorage.getImage(imgName));


          }catch (Error e){
            System.out.println("Try Again");
          }
        }

      }
    }

  }




  public void saveToStorage(ImageStorage storage, Image image, String name){
    storage.addImage(name,image);
  }
}