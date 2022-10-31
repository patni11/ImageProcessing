package modifications;

/**
 * a function object to convert a .ppm image to greyscale (set rgb to average of rgb values?).
 */
public class Greyscale implements ppmModification {

  @Override
  public int[] modifyImage(int[] original) {
    return new int[0];
  }
}
