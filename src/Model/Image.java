package Model;

public class Image {
  private int width;
  private int height;
  private String comment;
  private Pixel[][] pixels;

  public Image(int height, int width, String comment, Pixel[][] pixels) {
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

  public Image(Image image) {
    this.height = image.height;
    this.width = image.width;
    this.comment = null;
    this.pixels = image.pixels;
  }

  public String toString() {
    StringBuilder pixelsString = new StringBuilder();
    for (Pixel[] rowOfPixels : pixels) {
      for (Pixel pixel: rowOfPixels) {
        pixelsString.append(pixel.toString() + "\n");
      }
    }
    return "P3\n" + this.width + "\n" + this.height + "\n" + "255\n" + pixelsString;
  }

  public Pixel[][] getPixels() {
    return this.pixels.clone();
  }

  public int getHeight(){
    return this.height;
  }

  public int getWidth(){
    return this.width;
  }


}
