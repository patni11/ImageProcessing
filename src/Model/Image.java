package Model;

/**
 * A class representing a ppm image file with meta data separate from data.
 */
public class Image {
  private int width;
  private int height;
  private String comment;
  private Pixel[][] pixels;

  public Image(int width, int height, String comment, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.comment = comment;
    this.pixels = pixels;
  }

  public Image(int height, int width, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.comment = null;
    this.pixels = pixels;
  }

  /**
   * translate this image object to a string of it's contents.
   * @return a string in the format of a ppm file
   */
  public String toString() {
    StringBuilder pixelsString = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelsString.append(pixels[j][i].toString() + "\n");
      }
    }
    System.out.println("out of the double loop");
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
