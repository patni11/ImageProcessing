package clientInteractions;

/**
 * controller to make the model usable
 */
public class ImageProcessorController {
  private final ImageProcessorModel model;
  private final Appendable output;

  /**
   * basic controller taking in model and output
   * @param model the model of the Image Processor to modify
   * @param output where the view will be shown
   */
  public ImageProcessorController(ImageProcessorModel model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  /**
   * runs the Image Processor application, feeding user actions to the model to modify ppm files
   */
  public void run() {
    
  }
}
