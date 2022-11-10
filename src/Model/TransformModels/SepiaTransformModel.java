package model.TransformModels;

import model.KernalModels.Kernel;

public class SepiaTransformModel extends Kernel
{
  public SepiaTransformModel() {
    super(3);
    this.kernel[0][0] = 0.393;
    this.kernel[0][1] = 0.796;
    this.kernel[0][2] = 0.189;
    this.kernel[1][0] = 0.349;
    this.kernel[1][1] = 0.686;
    this.kernel[1][2] = 0.168;
    this.kernel[2][0] = 0.272;
    this.kernel[2][1] = 0.534;
    this.kernel[2][2] = 0.131;
  }
}
