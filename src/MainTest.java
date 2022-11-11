import java.io.StringReader;

import controller.ControllerImpl;
import controller.ImageProcessingController;
import model.ImageStorage;

/**
 * The main class to run the program from for testing.
 * This includes a test Script and input commands
 */

public class MainTest {

  /**
   * Main function to run the image processing program with test input.
   *
   * @param args takes in default args
   */
  public static void main(String[] args) {
    StringReader input = new StringReader("load res/redWhiteRed.ppm rwr\n" +
            "brighten -50 rwr Drwr\n" +
            "load res/redGreenBlue.ppm rgb\n" +
            "brighten -100 rgb Drgb\n" +
            "brighten 30 rgb rgbB30\n" +
            "horizontal-flip Drgb hFlipDrgb\n" +
            "load res/redGreenBlueVertical.ppm rgbv\n" +
            "vertical-flip rgbv Vrgbv\n" +
            "load res/random5x5.ppm random\n" +
            "brighten 45 random Brandom\n" +
            "brighten -50 random Drandom\n" +
            "red-component rgb gRgb\n" +
            "blue-component Drandom DGreyrandom\n" +
            "green-component rwr Grwr\n" +
            "value-component Drgb DVrgb\n" +
            "intensity-component random Grandom\n" +
            "luma-component Vrgbv rgbB30\n" +
            "horizontal-flip rgbB30 rgbV30\n" +
            "save res/Drwr.ppm Drwr\n" +
            "save res/Drgb.ppm Drgb\n" +
            "save res/rgbB30.ppm rgbB30\n" +
            "save res/Vrgbv.ppm Vrgbv\n" +
            "save res/Brandom.ppm Brandom\n" +
            "save res/Drandom.ppm Drandom\n" +
            "save res/gRgb.ppm gRgb\n" +
            "save res/DGreyrandom.ppm DGreyrandom\n" +
            "save res/rgbV30.ppm rgbV30\n");

    ImageStorage imgStorage = new ImageStorage();
    ImageProcessingController controller = new ControllerImpl(input, imgStorage);
    controller.startEditor();
  }
}
