package modifications;

import Model.Image;
import Model.ImageStorage;
import Model.Pixel;



/**
 * a function object to convert a .ppm image to greyscale (set rgb to average of rgb values?).
 */
public class Greyscale implements PPMModification {

  private ImageStorage imgStorage;
  private String type;
  private String imgName;
  private String destName;

  public Greyscale(ImageStorage imgStorage, String type, String imgName, String destName) {
    this.imgStorage = imgStorage;
    this.type = type;
    this.imgName = imgName;
    this.destName = destName;
  }

  public void go() throws Exception {
    Image image = ModificationUtils.getImage(imgStorage, this.imgName);

    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] newImg = new Pixel[height][width];

    for (int row = 0; row < height; row++){
      for (int col = 0; col < width; col++) {
        Pixel ogPixel = image.getPixels()[row][col];
        newImg[row][col] = changePixel(type,ogPixel);
      }
    }

    this.imgStorage.addImage(destName,new Image(height,width,newImg));
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
