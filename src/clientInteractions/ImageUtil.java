package clientInteractions;

import java.io.FileWriter;
import java.io.IOException;
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
  public static Image readPPM(String filename) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    String comment = new String();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
        comment = s+System.lineSeparator();
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    Pixel[][] pixels = new Pixel[height][width];

    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        pixels[i][j] = new Pixel(r,g,b);
      }
    }

    return new Image(height,width,comment,pixels);
  }


//
//  /**
//   * Save an image file in the PPM format.
//   *
//   * @param ppm the path of the file.
//   */
//  public static void savePPM(Image image, String name) throws IOException {
//
//    FileWriter writer = new FileWriter(name);
//
//    //handle the P3 token and write it to the new file
//    writer.write("P3\n");
//
//    //write width and height to file
//    String width = image.get;
//    String height = ppm[1][1].toString();
//    writer.write(width + " " + height + "\n");
//
//    //write the maxValue for pixels to the file
//    String maxValue = ppm[2][0].toString();
//    writer.write(maxValue + "\n");
//
//    //go through every pixel and write each rgb to the file
//    for (int i=3;i<height;i++) {
//      for (int j = 0; j < width; j++) {
//        writer.write(ppm[i][j].toString());
//      }
//    }
//    writer.close();
//  }

  //demo main
//  public static void main(String []args) {
//    String filename;
//
//    if (args.length>0) {
//        filename = args[0];
//    }
//    else {
//        filename = "Koala.ppm";
//    }
//
//    int[] ppm_output = new int[2];
//    try {
//      ppm_output = ImageUtil.savePPM(filename);
//    } catch (FileNotFoundException e) {
//      System.out.println(e.getMessage());
//    } catch (IOException e) {
//      System.out.println(e);
//    }
//  }

}

