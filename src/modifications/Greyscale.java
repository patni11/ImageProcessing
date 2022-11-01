package modifications;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import Model.Image;
import Model.Pixel;



/**
 * a function object to convert a .ppm image to greyscale (set rgb to average of rgb values?).
 */
public class Greyscale implements PPMModification {
  private Map<String, Function<Pixel, Pixel>> functions = new HashMap<>();

  public void PPMModification(){
    this.functions.put("red-component",);
  }

  public Image modifyImage(String args, Image image) {
    Image imgCopy = new Image(image);
    for (Pixel[] rowOfPixels : imgCopy.getPixels()) {
      for (Pixel pixel: rowOfPixels) {

      }
    }

    return new Image(4,5, new Pixel[][]{});
  }

//  private Pixel changePixels(String args, Pixel pixel){
//    switch (args){
//      case ("red-component"):
//        return red(pixel);
//        break;
//      case ("green-component"):
//        return green(pixel);
//        break;
//
//    }
//  }

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
