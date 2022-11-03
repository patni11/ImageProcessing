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

  public static void main(String[] args) {
    ImageStorage imgStorage = new ImageStorage();
    Scanner sc = new Scanner(InputStream.nullInputStream());
    Map<String, Function<Scanner, PPMModification>> knownCommands = new HashMap<>();

    knownCommands.put("load",(Scanner s) -> {return new Load(imgStorage, s.next(), s.next());});

    knownCommands.put("brighten",(Scanner s) ->
    {return new Brighten(imgStorage,s.nextInt(),s.next(),s.next());});

    knownCommands.put("vertical-flip",(Scanner s) ->
    {return new Flip(imgStorage,"vertical",sc.next(), sc.next());});

    knownCommands.put("horizontal-flip",(Scanner s) ->
    {return new Flip(imgStorage,"horizontal", sc.next(), sc.next());});

    knownCommands.put("value-component",(Scanner s) ->
    {return new Greyscale(imgStorage,"value-component",sc.next(),sc.next());});

    knownCommands.put("red-component",(Scanner s) ->
    {return new Greyscale(imgStorage,"red-component",sc.next(),sc.next());});

    knownCommands.put("green-component",(Scanner s) ->
    {return new Greyscale(imgStorage,"green-component",sc.next(),sc.next());});

    knownCommands.put("blue-component",(Scanner s) ->
    {return new Greyscale(imgStorage,"blue-component",sc.next(),sc.next());});

    knownCommands.put("luma-component",(Scanner s) ->
    {return new Greyscale(imgStorage,"luma-component",sc.next(),sc.next());});

    knownCommands.put("intensity-component",(Scanner s) ->
    {return new Greyscale(imgStorage,"intensity-component",sc.next(),sc.next());});

    knownCommands.put("save",(Scanner s) ->
    {return new Save(imgStorage,sc.next(),sc.next());});

    Image image = null;
    String name = null;

    while (sc.hasNext()) {
      String in = sc.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit"))
        return;

      Function<Scanner, PPMModification> cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null){
        throw new IllegalArgumentException("Invalid Command");
      }else{
        if (in.equals("brighten")){
          try {
            int intensity = sc.nextInt();
            String imgName = sc.next();
            String destName = sc.next();



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