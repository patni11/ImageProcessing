package modifications;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import Controller.ControllerImpl;
import Controller.ImageProcessingController;
import model.ImageStorage;

public class RunFile implements PPMModification{

  private ImageStorage imageStorage;
  private String filename;
  public RunFile(ImageStorage imageStorage, String filename){
    this.imageStorage = imageStorage;
    this.filename = filename;

  }

  @Override
  public void runFunction() throws Exception {
    String commands = getFileData().toString();
    StringReader input = new StringReader(commands);
    ImageProcessingController controller = new ControllerImpl(input, imageStorage);
    controller.startEditor();
  }

  private StringBuilder getFileData() throws FileNotFoundException {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(this.filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      builder.append(s + System.lineSeparator());
    }

    return builder;
  }
}
