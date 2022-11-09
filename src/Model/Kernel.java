package model;

public class Kernel {

  private double[][] kernel;

  public Kernel(double[][] kernel) {

    this.kernel = kernel;
  }

  public int getDimension() {

    return this.kernel.length;
  }

}
