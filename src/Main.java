import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
public class Main {

  /**
   * Main function to run the image processing program with user input and output.
   *
   * @param args takes in default args
   */
  public static void main(String[] args) {
    ImageStorage imgStorage = new ImageStorage();
    Scanner sc = new Scanner(System.in);
    Map<String, Function<Scanner, PPMModification>> knownCommands = new HashMap<>();

    knownCommands.put("load", (Scanner s) -> {
      return new Load(imgStorage, s.next(), s.next());
    });

    knownCommands.put("brighten", (Scanner s) -> {
      return new Brighten(imgStorage, s.nextInt(), s.next(), s.next());
    });

    knownCommands.put("vertical-flip", (Scanner s) -> {
      return new Flip(imgStorage, "vertical", sc.next(), sc.next());
    });

    knownCommands.put("horizontal-flip", (Scanner s) -> {
      return new Flip(imgStorage, "horizontal", sc.next(), sc.next());
    });

    knownCommands.put("value-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "value-component", sc.next(), sc.next());
    });

    knownCommands.put("red-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "red-component", sc.next(), sc.next());
    });

    knownCommands.put("green-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "green-component", sc.next(), sc.next());
    });

    knownCommands.put("blue-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "blue-component", sc.next(), sc.next());
    });

    knownCommands.put("luma-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "luma-component", sc.next(), sc.next());
    });

    knownCommands.put("intensity-component", (Scanner s) -> {
      return new Greyscale(imgStorage, "intensity-component", sc.next(), sc.next());
    });

    knownCommands.put("save", (Scanner s) -> {
      return new Save(imgStorage, sc.next(), sc.next());
    });

    Image image = null;
    String name = null;
    Function<Scanner, PPMModification> cmd;

    while (sc.hasNext()) {
      String in = sc.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        System.out.print("program terminated");
        return;
      }

      cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        System.out.print("Invalid Input");

      } else {
        PPMModification c = cmd.apply(sc);
        try {
          c.runFunction();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    }

  }

}