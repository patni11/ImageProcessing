package view;

import java.awt.event.ActionListener;

import javax.swing.*;

public class SwingImageProcessorGUI extends JFrame
        implements ImageProcessorGUIView {

  private JButton load;
  private JButton save;
  private JButton blur;
  private JButton sharpen;
  private JButton greyscale;

  public SwingImageProcessorGUI() {

    super("Image Processor");

    ImageIcon icon = new ImageIcon("download.jpeg");
    JLabel label = new JLabel(icon);
    JScrollPane scrollPane = new JScrollPane(label);
    this.add(scrollPane);

    this.setSize(1024,1024);
    this.setVisible(true);

  }

  @Override
  public void addListeners(ActionListener listener) {

  }
}