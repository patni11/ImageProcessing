package modifications;

import Model.Image;
import Model.Pixel;



/**
 * a function object to convert a .ppm image to greyscale (set rgb to average of rgb values?).
 */
public class Greyscale implements PPMModification {

  public Image modifyImage(String args, Image image) {
    Image imgCopy = new Image(image);
    int width = imgCopy.getWidth();
    int height = imgCopy.getHeight();
    Pixel[][] newImg = new Pixel[width][height];

    for (int row = 0; row < height; row++){
      for (int col = 0; col < width; col++) {
        Pixel ogPixel = imgCopy.getPixels()[row][col];
        newImg[row][col] = changePixel(args,ogPixel);
      }
    }


    return new Image(height,width,newImg);
  }

  private Pixel changePixel(String arg, Pixel pixel){

    if (arg.equals("red-component")) {
      return red(pixel);
    } else if (arg.equals("green-component")) {
      return green(pixel);
    } else if (arg.equals("blue-component")) {
      return blue(pixel);
    } else if (arg.equals("value-component")) {
      return value(pixel);
    } else if (arg.equals("intensity-component")) {
      return intensity(pixel);
    } else if (arg.equals("luma-component")) {
      return luma(pixel);
    }
    return pixel;
  }

  private Pixel red(Pixel p){
    return new Pixel(p.getR(),p.getR(),p.getR());
  }

  private Pixel green(Pixel p){
    return new Pixel(p.getG(),p.getG(),p.getG());
  }

  private Pixel blue(Pixel p){
    return new Pixel(p.getB(),p.getB(),p.getB());
  }

  private Pixel value(Pixel p){
    int val = Math.max(Math.max(p.getR(),p.getG()),p.getB());
    return new Pixel(val,val,val);
  }

  private Pixel intensity(Pixel p){
    int val = (p.getR()+p.getG()+p.getB())/3;
    return new Pixel(val,val,val);
  }
  private Pixel luma(Pixel p){
    int val = (int) Math.round(0.2126*(p.getR()) + 0.7152*(p.getG()) + 0.0722*(p.getB()));
    return new Pixel(val,val,val);
  }

}
