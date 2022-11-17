package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Image;
import model.ImageStorage;
import modifications.Load;

public class SwingImageProcessorGUI extends JFrame
        implements ImageProcessorGUIView {

  private JButton load;
  private JButton save;
  private JButton blur;
  private JButton sharpen;
  private JButton greyscale;

  public SwingImageProcessorGUI() {

    super("Image Processor");

    JFrame frame = new JFrame("Image Processor");
    ImageStorage storage = new ImageStorage();
    Load func = new Load(storage, "download.jpeg", "dog");
    Image dog = null;

    try {
      func.runFunction();
      dog = storage.getImage("dog");
    } catch (Exception e) {

      System.out.println(String.valueOf(e));
    }

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(new JScrollPane(new histogramView(dog, 800, 800)));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    this.setSize(1200,1200);



  }

  @Override
  public void addListeners(ActionListener listener) {

  }
}