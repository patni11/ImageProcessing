import java.io.InputStreamReader;

import controller.ControllerImpl;
import controller.ImageProcessingController;
import model.ImageStorage;


/**
 * The main class to run the program from. Collects user input and delegates functionality.
 */
public class Main {

  /**
   * Main function to run the image processing program with user input and output.
   *
   * @param args takes in default args
   */
  public static void main(String[] args) {
    ImageStorage imgStorage = new ImageStorage();
    ImageProcessingController controller = new ControllerImpl(new InputStreamReader(System.in), imgStorage);
    controller.startEditor();
  }

}