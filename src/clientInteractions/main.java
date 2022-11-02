package clientInteractions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import Model.Image;

/**
 * The main class to run the program from. Collects user input and delegates functionality.
 */
public class main {

  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(InputStream.nullInputStream());
    Image image = null;
    String name = null;
    while (image == null) {
      System.out.println("please enter the name of the file you would like to modify");
      name = sc.next();
      String filename = name + ".ppm";
      try {
        image = ImageUtil.readPPM(filename);
      } catch (FileNotFoundException e) {
        System.out.println(e);
        image = null;
      }
    }

    ImageUtil.savePPM(image, name + " created copy" + ".ppm");

  }
}