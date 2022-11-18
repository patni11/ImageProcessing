package model.KernalModels;

public class Kernel {

  protected double[][] kernel;

  public Kernel(int dimension) {
    this.kernel = new double[dimension][dimension];
  }

  public int getDimension() {

    return this.kernel.length;
  }

  public double[][] getKernel(){
    return this.kernel;
  }

}
