package modifications;

import Model.Image;
import Model.Pixel;

/**
 * a function object to brighten a .ppm image.
 */
public class Brighten implements PPMModification {

  private int increment;
  public Brighten(int increment){
    this.increment = increment;
  }

  public Image modifyImage(Image image) {
    Image imgCopy = new Image(image);
    for (Pixel[] rowOfPixels : imgCopy.getPixels()) {
      for (Pixel pixel: rowOfPixels) {
        pixel.setR(getVal(pixel.getR()));
        pixel.setG(getVal(pixel.getG()));
        pixel.setB(getVal(pixel.getB()));
      }
    }

    return imgCopy;
  }

  private int getVal(int val){
    if ((val + this.increment) >= 255){
      return 255;
    }
    return (val + this.increment);
  }

  public void setIncrement(int increment){
    this.increment = increment;
  }
}
