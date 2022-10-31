package modifications;

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

  public String toString() {
    String pixelsString = "";
    for (Pixel[] rowOfPixels : pixels) {
      for (Pixel pixel: rowOfPixels) {
        pixelsString += pixel.toString() + "\n";
      }
    }
    return "P3\n" + this.height + "\n" + this.width + "\n" + "255\n" + pixelsString;
  }


}
