import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import Model.Image;
import Model.ImageStorage;
import clientInteractions.ImageUtil;
import modifications.Brighten;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.Save;

import static org.junit.Assert.assertEquals;

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
    load.modifyImage("redWhiteRed",redWhiteRed);
    load.modifyImage("redGreenBlue",redGreenBlue);
    load.modifyImage("random5x5",random5x5);
    load.modifyImage("koala",koala);
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
  public void testBrightenRWR30() throws Exception{
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
  public void testBrightenRWR200() throws Exception{
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
  public void testBrightenRWRneg10() throws Exception{
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
  public void testBrightenRWRneg100() throws Exception{
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
  public void testGreyscaleRWRred() throws Exception{
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
  public void testGreyscaleRWRgreen() throws Exception{
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
  public void testGreyscaleRWRblue() throws Exception{
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
  public void testGreyscaleRWRvalue() throws Exception{
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
  public void testGreyscaleRWRintensity()throws Exception {
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
  public void testGreyscaleRWRluma() throws Exception{
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
  public void testGreyscaleRGBred() throws Exception{
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
  public void testGreyscaleRGBgreen() throws Exception{
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
  public void testGreyscaleRGBblue() throws Exception{
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
  public void testFlipRGBhorizontal() throws Exception{
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
  public void testFlipRGBvertical() throws Exception{
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
  public void testFlipRWRhorizontal() throws Exception{
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
  public void testFlipRGBVhorizontal() throws Exception{
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
  public void testFlipRGBVvertical() throws Exception{
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

















  /**
  @Test
  public void testBrighten() {
    PPMModification brighten = new Brighten();
    Image brwr = brighten.modifyImage("10", storage.getImage("redWhiteRed"));
    Image brgb = brighten.modifyImage("50", storage.getImage("redGreenBlue"));
    Image br5 = brighten.modifyImage("80", storage.getImage("random5x5"));

    System.out.println(brwr.toString());
    System.out.println(brgb.toString());
    System.out.println(br5.toString());

  }

  @Test
  public void testDarken(){
    PPMModification brighten = new Brighten();
    Image drwr = brighten.modifyImage("-10", storage.getImage("redWhiteRed"));
    Image drgb = brighten.modifyImage("-250", storage.getImage("redGreenBlue"));
    Image dr5 = brighten.modifyImage("-180", storage.getImage("random5x5"));

    System.out.println(drwr.toString());
    System.out.println(drgb.toString());
    System.out.println(dr5.toString());
  }

  @Test
  public void testGreyScale(){
    PPMModification greyScale = new Greyscale();
    Image rrwr = greyScale.modifyImage("red-component",storage.getImage("redWhiteRed"));
    Image grgb = greyScale.modifyImage("green-component",storage.getImage("redGreenBlue"));
    Image brgb = greyScale.modifyImage("blue-component",storage.getImage("redGreenBlue"));

    Image vdr5 = greyScale.modifyImage("value-component",storage.getImage("random5x5"));
    Image irwr = greyScale.modifyImage("intensity-component",storage.getImage("redWhiteRed"));
    Image ldr5 = greyScale.modifyImage("luma-component",storage.getImage("random5x5"));

    System.out.println(rrwr.toString());
    System.out.println(grgb.toString());
    System.out.println(brgb.toString());
    System.out.println(vdr5.toString());
    System.out.println(irwr.toString());
    System.out.println(ldr5.toString());

  }

  @Test
  public void testSave(){
    PPMModification save = new Save();
    System.out.println(storage.getImage("redWhiteRed").toString());
    save.modifyImage("shubhOutput.ppm",storage.getImage("redWhiteRed"));
  }

  @Test
  public void testKoala(){
    PPMModification save = new Save();
    PPMModification brighten = new Brighten();
    PPMModification greyScale = new Greyscale();

    Image bKoala = brighten.modifyImage("50", this.koala);
    Image dKoala = brighten.modifyImage("-80", this.koala);
    Image greyKoala = greyScale.modifyImage("red-component", this.koala);

    save.modifyImage("bKoala.ppm",bKoala);
    save.modifyImage("dKoala.ppm",dKoala);
    save.modifyImage("greyKoala.ppm",greyKoala);
  }
  **/
}
