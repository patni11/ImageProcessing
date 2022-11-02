package clientInteractions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import Model.Image;
import Model.Pixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {



  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
//  public static Image readPPM(String filename) {
//    Scanner sc = null;
//
//    try {
//      sc = new Scanner(new FileInputStream(filename));
//    }
//    catch (FileNotFoundException e) {
//      System.out.println("File "+filename+ " not found!");
//      return null;
//    }
//    StringBuilder builder = new StringBuilder();
//    String comment = "";
//    //read the file line by line, and populate a string. This will throw away any comment lines
//    while (sc.hasNextLine()) {
//      String s = sc.nextLine();
//      if (s.charAt(0)!='#') {
//        builder.append(s+System.lineSeparator());
//        comment = s+System.lineSeparator();
//      }
//    }
//
//    //now set up the scanner to read from the string we just built
//    sc = new Scanner(builder.toString());
//
//    String token = sc.next();
//    if (!token.equals("P3")) {
//      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
//    }
//
//    int width = sc.nextInt();
//    int height = sc.nextInt();
//
//    Pixel[][] pixels = new Pixel[height][width];
//
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        int r = sc.nextInt();
//        int g = sc.nextInt();
//        int b = sc.nextInt();
//        pixels[i][j] = new Pixel(r, g, b);
//      }
//    }
//
//    return new Image(height, width, comment, pixels);
//  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename) throws FileNotFoundException {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] pixels = new Pixel[height][width];

    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new Pixel(r,g,b);
      }
    }

    return new Image(height,width,pixels);
  }



  /**
   * Save an image file in the PPM format.
   *
   * @param image the image to be saved
   * @param name the desired name to save the image as
   */
  public static void savePPM(Image image, String name) throws IOException {
    System.out.println("in the save function");
    String imageString = image.toString();
    System.out.println("toString is done");
    Scanner sc = new Scanner(imageString);

    FileWriter writer = null;

    try {
      writer = new FileWriter(name);
    }
    catch (FileNotFoundException e) {
      System.out.print(e);
    }

    if (writer != null) {
      //handle the P3 token and write it to the new file
      String token = sc.next();
      writer.write(token + "\n");

      //write width and height to file
      String width = sc.next();
      int widthInt = image.getWidth();
      String height = sc.next();
      int heightInt = image.getHeight();
      writer.write(width + " " + height + "\n");

      //write the maxValue for pixels to the file
      String maxValue = sc.next();
      writer.write(maxValue + "\n");

      System.out.println("wrote metaData, about to write pixels");
      //go through every pixel and write each rgb to the file
      int count = 0;
      for (int i = 0; i < heightInt; i++) {
        for (int j = 0; j < widthInt; j++) {
          String value = sc.next();
          writer.write(value + "\n");
        }
      }
      while (sc.hasNextLine()) {
        count += 1;
        System.out.println(count);
        System.out.println(sc.next());
      }
      System.out.println(count);
      System.out.println(sc.hasNextLine());
      writer.close();
    }

  }

  /**
  //demo main
  public static void main(String []args) throws IOException {
    String name = "";
    String filename;

    if (args.length > 0) {
        filename = args[0];
    }
    else {
      name = "Koala";
      filename = name + ".ppm";
    }

    Image image = ImageUtil.readPPM(filename);

    ImageUtil.savePPM(image, name + " created copy" + ".ppm");

  }
   */

}

