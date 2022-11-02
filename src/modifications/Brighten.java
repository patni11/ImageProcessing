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
  public Brighten(){

  }

  public Image modifyImage(String arg, Image image) {

    try{
      this.increment = Integer.parseInt(arg);
    }catch (Error e){

    }

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

    if ((val + this.increment) <= 0){
      return 0;
    }
    return (val + this.increment);
  }

  public void setIncrement(int increment){
    this.increment = increment;
  }
}
