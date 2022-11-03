package Model;

import java.util.HashMap;
import java.util.Map;

public class ImageStorage {
  Map<String, Image> images = new HashMap<>();

  public ImageStorage(){
    images = new HashMap<>();
  }

  public void addImage(String name, Image image){
    this.images.put(name,image);
  }

  public Image getImage(String name) throws Exception {
    if (this.images.get(name) == null) {
      throw new Exception("Image not in storage");
    }
    return this.images.get(name);
  }
}
