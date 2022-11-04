import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import Model.ImageStorage;
import interactions.ImageUtil;
import Model.Image;
import Model.ImageStorage;
import modifications.Brighten;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;

import static org.junit.Assert.assertEquals;

/**
 * class ot test image modifiction functions.
 */
public class ImageModificationTest {

  ImageStorage storage;
  Image random5x5;
  Image redGreenBlue;
  Image redWhiteRed;
  Image koala;
  Image redGreenBlueVertical;

  @Before
  public void setup() throws FileNotFoundException {
    redWhiteRed = ImageUtil.readPPM("testImages/redWhiteRed.ppm");
    redGreenBlue = ImageUtil.readPPM("testImages/redGreenBlue.ppm");
    random5x5 = ImageUtil.readPPM("testImages/random5x5.ppm");
    koala = ImageUtil.readPPM("Koala.ppm");
    redGreenBlueVertical = ImageUtil.readPPM("testImages/redGreenBlueVertical.ppm");

    //load images
    storage = new ImageStorage();
    Load load = new Load(storage);
    load.modifyImage("redWhiteRed", redWhiteRed);
    load.modifyImage("redGreenBlue", redGreenBlue);
    load.modifyImage("random5x5", random5x5);
    load.modifyImage("koala", koala);
    load.modifyImage("redGreenBlueVertical", redGreenBlueVertical);

  }

  //toString Test
  @Test
  public void testToStringRWR() {
    String expected = "P3\n3 3\n255\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n";
    assertEquals(expected, redWhiteRed.toString());
  }

  @Test
  public void testToStringRGB() {
    String expected = "P3\n3 3\n255\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 0 255\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 0 255\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 0 255\n";
    assertEquals(expected, redGreenBlue.toString());
  }

  @Test
  public void testToStringRGBV() {
    String expected = "P3\n3 3\n255\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 255 0\n"
            + "0 255 0\n"
            + "0 0 255\n"
            + "0 0 255\n"
            + "0 0 255\n";
    assertEquals(expected, redGreenBlueVertical.toString());
  }


  //brighten tests
  @Test
  public void testBrightenRWR10() throws Exception {
    Brighten brighten = new Brighten();
    String expected = "P3\n3 3\n255\n"
            + "255 10 10\n"
            + "255 10 10\n"
            + "255 10 10\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 10 10\n"
            + "255 10 10\n"
            + "255 10 10\n";
    Image brwr = brighten.modifyImage("10", storage.getImage("redWhiteRed"));
    assertEquals(expected, brwr.toString());
  }

  @Test
  public void testBrightenRWR30() throws Exception {
    Brighten brighten = new Brighten();
    String expected = "P3\n3 3\n255\n"
            + "255 30 30\n"
            + "255 30 30\n"
            + "255 30 30\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 30 30\n"
            + "255 30 30\n"
            + "255 30 30\n";
    Image brwr = brighten.modifyImage("30", storage.getImage("redWhiteRed"));
    assertEquals(expected, brwr.toString());
  }

  @Test
  public void testBrightenRWR200() throws Exception {
    Brighten brighten = new Brighten();
    String expected = "P3\n3 3\n255\n"
            + "255 200 200\n"
            + "255 200 200\n"
            + "255 200 200\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 200 200\n"
            + "255 200 200\n"
            + "255 200 200\n";
    Image brwr = brighten.modifyImage("200", storage.getImage("redWhiteRed"));
    assertEquals(expected, brwr.toString());
  }

  @Test
  public void testBrightenRWRneg10() throws Exception {
    Brighten brighten = new Brighten();
    String expected = "P3\n3 3\n255\n"
            + "245 0 0\n"
            + "245 0 0\n"
            + "245 0 0\n"
            + "245 245 245\n"
            + "245 245 245\n"
            + "245 245 245\n"
            + "245 0 0\n"
            + "245 0 0\n"
            + "245 0 0\n";
    Image brwr = brighten.modifyImage("-10", storage.getImage("redWhiteRed"));
    assertEquals(expected, brwr.toString());
  }

  @Test
  public void testBrightenRWRneg100() throws Exception {
    Brighten brighten = new Brighten();
    String expected = "P3\n3 3\n255\n"
            + "155 0 0\n"
            + "155 0 0\n"
            + "155 0 0\n"
            + "155 155 155\n"
            + "155 155 155\n"
            + "155 155 155\n"
            + "155 0 0\n"
            + "155 0 0\n"
            + "155 0 0\n";
    Image brwr = brighten.modifyImage("-100", storage.getImage("redWhiteRed"));
    assertEquals(expected, brwr.toString());
  }


  //greyscale tests
  @Test
  public void testGreyscaleRWRred() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n";
    Image gsrwr = greyscale.modifyImage("red-component", storage.getImage("redWhiteRed"));
    assertEquals(expected, gsrwr.toString());
  }

  @Test
  public void testGreyscaleRWRgreen() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "0 0 0\n";
    Image gsrwr = greyscale.modifyImage("green-component", storage.getImage("redWhiteRed"));
    assertEquals(expected, gsrwr.toString());
  }

  @Test
  public void testGreyscaleRWRblue() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "0 0 0\n";
    Image gsrwr = greyscale.modifyImage("blue-component", storage.getImage("redWhiteRed"));
    assertEquals(expected, gsrwr.toString());
  }

  @Test
  public void testGreyscaleRWRvalue() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n";
    Image gsrwr = greyscale.modifyImage("value-component", storage.getImage("redWhiteRed"));
    assertEquals(expected, gsrwr.toString());
  }

  @Test
  public void testGreyscaleRWRintensity() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "85 85 85\n"
            + "85 85 85\n"
            + "85 85 85\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "85 85 85\n"
            + "85 85 85\n"
            + "85 85 85\n";
    Image gsrwr = greyscale.modifyImage("intensity-component", storage.getImage("redWhiteRed"));
    assertEquals(expected, gsrwr.toString());
  }

  @Test
  public void testGreyscaleRWRluma() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "54 54 54\n"
            + "54 54 54\n"
            + "54 54 54\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "54 54 54\n"
            + "54 54 54\n"
            + "54 54 54\n";
    Image gsrwr = greyscale.modifyImage("luma-component", storage.getImage("redWhiteRed"));
    assertEquals(expected, gsrwr.toString());
  }

  @Test
  public void testGreyscaleRGBred() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n";
    Image gsrgb = greyscale.modifyImage("red-component", storage.getImage("redGreenBlue"));
    assertEquals(expected, gsrgb.toString());
  }

  @Test
  public void testGreyscaleRGBgreen() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n";
    Image gsrgb = greyscale.modifyImage("green-component", storage.getImage("redGreenBlue"));
    assertEquals(expected, gsrgb.toString());
  }

  @Test
  public void testGreyscaleRGBblue() throws Exception {
    Greyscale greyscale = new Greyscale();
    String expected = "P3\n3 3\n255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n";
    Image gsrgb = greyscale.modifyImage("blue-component", storage.getImage("redGreenBlue"));
    assertEquals(expected, gsrgb.toString());
  }


  //flip methods
  @Test
  public void testFlipRGBhorizontal() throws Exception {
    Flip flip = new Flip();
    String expected = "P3\n3 3\n255\n"
            + "0 0 255\n"
            + "0 255 0\n"
            + "255 0 0\n"

            + "0 0 255\n"
            + "0 255 0\n"
            + "255 0 0\n"

            + "0 0 255\n"
            + "0 255 0\n"
            + "255 0 0\n";
    Image fliprgb = flip.modifyImage("horizontal", storage.getImage("redGreenBlue"));
    assertEquals(expected, fliprgb.toString());
  }

  @Test
  public void testFlipRGBvertical() throws Exception {
    Flip flip = new Flip();
    String expected = "P3\n3 3\n255\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 0 255\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 0 255\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 0 255\n";
    Image fliprgb = flip.modifyImage("vertical", storage.getImage("redGreenBlue"));
    assertEquals(expected, fliprgb.toString());
  }

  @Test
  public void testFlipRWRhorizontal() throws Exception {
    Flip flip = new Flip();
    String expected = "P3\n3 3\n255\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n";
    Image fliprwr = flip.modifyImage("horizontal", storage.getImage("redWhiteRed"));
    assertEquals(expected, fliprwr.toString());
  }

  @Test
  public void testFlipRGBVhorizontal() throws Exception {
    Flip flip = new Flip();
    String expected = "P3\n3 3\n255\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "0 255 0\n"
            + "0 255 0\n"
            + "0 0 255\n"
            + "0 0 255\n"
            + "0 0 255\n";
    Image fliprgbv = flip.modifyImage("horizontal", storage.getImage("redGreenBlueVertical"));
    assertEquals(expected, fliprgbv.toString());
  }

  @Test
  public void testFlipRGBVvertical() throws Exception {
    Flip flip = new Flip();
    String expected = "P3\n3 3\n255\n"
            + "0 0 255\n"
            + "0 0 255\n"
            + "0 0 255\n"
            + "0 255 0\n"
            + "0 255 0\n"
            + "0 255 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n";
    Image fliprgbv = flip.modifyImage("vertical", storage.getImage("redGreenBlueVertical"));
    assertEquals(expected, fliprgbv.toString());
  }
}
