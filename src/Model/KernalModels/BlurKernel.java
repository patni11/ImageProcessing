package model.KernalModels;

import model.Pixel;

public class BlurKernel extends Kernel {

  public BlurKernel() {
    super(3);
    this.kernel[0][0] = 1/16;
    this.kernel[0][1] = 1/8;
    this.kernel[0][2] = 1/16;
    this.kernel[1][0] = 1/8;
    this.kernel[1][1] = 1/4;
    this.kernel[1][2] = 1/8;
    this.kernel[2][0] = 1/16;
    this.kernel[2][1] = 1/8;
    this.kernel[2][2] = 1/16;
  }
}
