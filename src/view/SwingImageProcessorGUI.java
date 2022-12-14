package view;

import java.awt.*;
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
  private JPanel mainPanel;

  private JPanel imageHistorgram;
  private JScrollPane mainScrollPane;

  public SwingImageProcessorGUI() {

    super("Image Processor");

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane((mainPanel));


    // Image & Histogram
    imageHistorgram = new JPanel();
    imageHistorgram.setLayout(new GridLayout(1,0,10,10));
    imageHistorgram.setBorder(BorderFactory.createTitledBorder("Image & Histogram"));

    ImageStorage storage = new ImageStorage();
    Load func = new Load(storage, "download.jpeg", "dog");
    Image dog = null;

    try {
      func.runFunction();
      dog = storage.getImage("dog");
    } catch (Exception e) {

      System.out.println(String.valueOf(e));
    }

    JPanel test = new JPanel();
    imageHistorgram.add(new JLabel(new ImageIcon("download.jpeg")));
    imageHistorgram.add((new JLabel(new histogramView(dog,400,400))));


    mainPanel.add(imageHistorgram);
    add(mainScrollPane);

    setVisible(true);
    this.setSize(1200,1200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Load and Save Buttons
    JPanel loadSave = new JPanel();
    loadSave.setLayout(new GridLayout(1,0,10,10));
    loadSave.setBorder(BorderFactory.createTitledBorder("Load & Save"));
    mainPanel.add(loadSave);
    makeButton(loadSave, "Load an Image");
    makeButton(loadSave, "Save Image");


    // Transformation Buttons
    JPanel allButtons = new JPanel();
    allButtons.setLayout(new GridLayout(4,4,10,10));
    allButtons.setBorder(BorderFactory.createTitledBorder("Image Modifications"));
    mainPanel.add(allButtons);

    //  Buttons without Input
    makeButton(allButtons, "Blur");
    makeButton(allButtons, "Sharpen");
    makeButton(allButtons, "Greyscale");
    makeButton(allButtons, "Red Greyscale");
    makeButton(allButtons, "Blue Greyscale");
    makeButton(allButtons, "Green Greyscale");
    makeButton(allButtons, "Sepia");
    makeButton(allButtons, "Horizontal Flip");
    makeButton(allButtons, "Vertical Flip");
    makeButton(allButtons, "Luma Greyscale");
    makeButton(allButtons, "Value Greyscale");
    makeButton(allButtons, "Intensity Greyscale");


    // ImageEditing Buttons that need Input
    JPanel brighten = new JPanel();
    brighten.setLayout(new FlowLayout());
    mainPanel.add(brighten);
    JButton blurButton = new JButton("Brightness");
    JTextField amountToBrighten = new JTextField(5);
    brighten.add(blurButton);
    brighten.add(amountToBrighten);

    // To-add
    // -- File?
    // -- Brighten

  }

  @Override
  public void addListeners(ActionListener listener) {

  }

  private void makeButton(JPanel main, String name) {

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    main.add(panel);
    JButton blurButton = new JButton(name);
    panel.add(blurButton);
  }
}