package model.transformModels;

import model.kernalModels.Kernel;

public class GreyscaleTransformModel extends Kernel {

  public GreyscaleTransformModel() {
    super(3);
    for(int i = 0; i < getDimension(); i++) {

      for(int j = 0; j < getDimension(); j++) {

        if(j == 0) {
          this.kernel[i][j] = 0.2126;
        }
        else if(j == 1) {
          this.kernel[i][j] = 0.7152;
        } else {

          this.kernel[i][j] = 0.0722;
        }
      }
    }
  }
}
