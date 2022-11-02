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

    int height = image.getHeight();
    int width = image.getWidth();
    Pixel[][] pixels = image.getPixels();

    Pixel[][] newPixels = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int newR = getVal(pixels[r][c].getR());
        int newG = getVal(pixels[r][c].getG());
        int newB = getVal(pixels[r][c].getB());

        newPixels[r][c] = new Pixel(newR,newG,newB);
      }
    }

    return new Image(height,width,newPixels);
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
