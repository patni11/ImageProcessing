package Model;

/**
 * A class representing a ppm image file with meta data separate from data.
 */
public class Image {
  private int height;
  private int width;
  private String comment;
  private Pixel[][] pixels;

  public Image(int height, int width, String comment, Pixel[][] pixels) {
    this.height = height;
    this.width = width;
    this.comment = comment;
    this.pixels = pixels;
  }

  public Image(int height, int width, Pixel[][] pixels) {
    this.height = height;
    this.width = width;
    this.comment = null;
    this.pixels = pixels;
  }

  /**
   * translate this image object to a string of it's contents.
   * @return a string in the format of a ppm file
   */
  public String toString() {
    String pixelsString = "";
    for (Pixel[] rowOfPixels : pixels) {
      for (Pixel pixel: rowOfPixels) {
        pixelsString += pixel.toString() + "\n";
      }
    }
    return "P3\n" + this.height + "\n" + this.width + "\n" + "255\n" + pixelsString;
  }

  /**
   * retrieve the width and height of the image object.
   * @return returns the width and height in the form of an int list
   */
  public int[] getMetaData() {
    int[] metaData = new int[2];
    metaData[0] = this.width;
    metaData[1] = this.height;
    return metaData.clone();
  }

  /**
   * retrieve a clone of the 2d list of pixels of this image
   * @return returns a 2d list of Pixel objects
   */
  public Pixel[][] getPixels() {
    return this.pixels.clone();
  }
  
}
