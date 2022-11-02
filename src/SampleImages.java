import java.util.HashMap;
import java.util.Map;

import Model.Image;
import Model.Pixel;

public class SampleImages {
  public static Map<String, Image> sampleImages = new HashMap<>();
  public static void main(){

    sampleImages.put("brwr",new Image(3,3,new Pixel[][]{}));
    sampleImages.put("brgb",new Image(3,3,new Pixel[][]{}));
    sampleImages.put("br5",new Image(5,5,new Pixel[][]{}));
    sampleImages.put("drwr",new Image(3,3,new Pixel[][]{}));
    sampleImages.put("drgb",new Image(3,3,new Pixel[][]{}));
    sampleImages.put("dr5",new Image(5,5,new Pixel[][]{}));

  }
}
