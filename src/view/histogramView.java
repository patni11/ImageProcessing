package view;

import imageinfo.Histogram;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.*;

import model.Image;
import model.Pixel;

public class histogramView implements Icon {
  Image image;
  int width;
  int height;

  float barWidth;
  int maxBarHeight;

  int maxFrequency;

  Histogram histogram;

  public histogramView(Image image, int width, int height){
    this.image = image;
    this.width = width;
    this.height = height;

    this.barWidth = width/(float) 256;
    this.maxBarHeight = (int) Math.floor(0.85*height);
    this.histogram = new Histogram(image);
    this.maxFrequency = histogram.getMaxFrequency();
    Dimension size = new Dimension(width, height);

  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {

    if (histogram != null){
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.DARK_GRAY);
      g2d.drawRect(20, 20, width, height);
      drawHistorgram(g2d,histogram.getR(),new Color(255,0,0,40));
      drawHistorgram(g2d,histogram.getG(),new Color(0,255,40));
      drawHistorgram(g2d,histogram.getB(),new Color(0,0,255,40));
      drawHistorgram(g2d,histogram.getI(),new Color(255,0,255,40));

      g2d.dispose();

    }
  }

  private void drawHistorgram(Graphics2D g2d, HashMap<Integer,Integer> mapFreq, Color color){
    float xPos = 20;
    for (Integer key : mapFreq.keySet()) {
      int value = mapFreq.get(key);

      double valFreq = ((double) value / maxFrequency);

      int barHeight = (int) (Math.floor( valFreq * height));
      g2d.setColor(color);
      int yPos = height + 20 - barHeight;
//Rectangle bar = new Rectangle(xPos, yPos, barWidth, barHeight);
      Rectangle2D bar = new Rectangle2D.Float(
              xPos, yPos, (float) barWidth, barHeight);
      g2d.fill(bar);
      g2d.setColor(Color.DARK_GRAY);
      g2d.draw(bar);
      xPos += barWidth;
    }
    System.out.println(xPos);
  }


  @Override
  public int getIconWidth() {
    return this.width;
  }

  @Override
  public int getIconHeight() {
    return this.height;
  }
}



