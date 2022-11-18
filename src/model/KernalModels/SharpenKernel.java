package model.KernalModels;

public class SharpenKernel extends Kernel {

  public SharpenKernel() {
    super(5);

    for(int i = 0; i < 5; i++) {

      for(int j = 0; j < 5; j++) {

        if(i != 0 && i != 4 && j != 0 && j != 4) {

          this.kernel[i][j] = 1/4;
        } else {

          this.kernel[i][j] = -1/8;
        }
      }
    }

    this.kernel[2][2] = 1;
  }
}
