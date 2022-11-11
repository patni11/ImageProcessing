import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;
import model.KernalModels.BlurKernel;
import model.KernalModels.Kernel;
import model.KernalModels.SharpenKernel;
import model.Pixel;
import modifications.Brighten;
import modifications.Filters.BlurFilter;
import modifications.Filters.Filter;
import modifications.Filters.SharpenFilter;
import modifications.Flip;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.RunFile;
import modifications.Transformations.GreyscaleTransform;
import modifications.Transformations.Sepia;
import modifications.Transformations.Transformations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

  /*
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
*/
  @Test
  public void testBlurKernelInitialization() {

    Kernel blur = new BlurKernel();
    double[][] values = blur.getKernel();
    assertEquals(values[0][0], 1.0 / 16, 0);
    assertEquals(values[0][1], 1.0 / 8, 0);
    assertEquals(values[1][1], 1.0 / 4, 0);
  }

  @Test
  public void testSharpenKernelInitialization() {

    Kernel sharp = new SharpenKernel();
    double[][] values = sharp.getKernel();

    for (int i = 0; i < 5; i++) {

      for (int j = 0; j < 5; j++) {

        if (i != 0 && i != 4 && j != 0 && j != 4) {

          if (i == 2 & j == 2) {
            assertEquals(values[2][2], 1.0, 0);
          } else {
            assertEquals(1.0 / 4, values[i][j], 0);
          }
        } else {

          assertEquals(-1.0 / 8, values[i][j], 0);
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKernelConstructorEvenDimension() {

    Kernel k = new Kernel(2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testKernelConstructorNonPositiveDimension() {

    Kernel k = new Kernel(0);

  }

  @Test
  public void testKernelGetDimension() {

    Kernel k = new Kernel(3);
    assertEquals(k.getDimension(), 3);
  }

  @Test
  public void testGetKernelCreatesCopy() {

    Kernel kernel = new BlurKernel();
    double[][] copy = kernel.getKernel();

    for (int i = 0; i < copy.length; i++) {

      for (int j = 0; j < copy[0].length; j++) {

        copy[i][j] = 0;
        assertFalse(copy[i][j] == kernel.getKernel()[i][j]);
      }
    }
  }

  @Test
  public void testBlurFilter() throws IllegalArgumentException {

    ImageStorage storage = new ImageStorage();
    Pixel[][] pixels = new Pixel[3][3];

    for (int i = 0; i < 3; i++) {

      for (int j = 0; j < 3; j++) {

        pixels[i][j] = new Pixel(20, 30, 40);
      }
    }

    Image img = new Image(3, 3, "none", pixels);
    storage.addImage("dog", img);
    Filter blurFilter = new BlurFilter(storage, "dog", "blurryDog");

    try {
      blurFilter.runFunction();
    } catch (Exception e) {

      throw new IllegalArgumentException(String.valueOf(e));
    }

    Image resulting;
    try {
      resulting = storage.getImage("blurryDog");
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }

    assertEquals("P3\n" +
            "3 3\n" +
            "255\n" +
            "10 14 22\n" +
            "13 18 29\n" +
            "10 14 22\n" +
            "13 18 29\n" +
            "17 23 38\n" +
            "13 18 29\n" +
            "10 14 22\n" +
            "13 18 29\n" +
            "10 14 22\n", resulting.toString());
  }

  @Test
  public void testSharpenFilter() {

    ImageStorage storage = new ImageStorage();
    Pixel[][] pixels = new Pixel[3][3];

    for (int i = 0; i < 3; i++) {

      for (int j = 0; j < 3; j++) {

        pixels[i][j] = new Pixel(20, 30, 40);
      }
    }

    Image img = new Image(3, 3, "none", pixels);
    storage.addImage("dog", img);
    Filter sharpFilter = new SharpenFilter(storage, "dog", "sharpDog");

    try {
      sharpFilter.runFunction();
    } catch (Exception e) {

      throw new IllegalArgumentException(String.valueOf(e));
    }

    Image resulting;
    try {
      resulting = storage.getImage("sharpDog");
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }

    assertEquals("P3\n" +
            "3 3\n" +
            "255\n" +
            "25 36 45\n" +
            "39 56 75\n" +
            "25 36 45\n" +
            "39 56 75\n" +
            "60 86 120\n" +
            "39 56 75\n" +
            "25 36 45\n" +
            "39 56 75\n" +
            "25 36 45\n", resulting.toString());
  }

  @Test
  public void testGreyScaleTransformation() {

    ImageStorage storage = new ImageStorage();
    Pixel[][] pixels = new Pixel[3][3];

    for (int i = 0; i < 3; i++) {

      for (int j = 0; j < 3; j++) {

        if (i < 1) {
          pixels[i][j] = new Pixel(20, 30, 40);
        } else {
          pixels[i][j] = new Pixel(40, 30, 20);
        }
      }
    }

    Image img = new Image(3, 3, "none", pixels);
    storage.addImage("dog", img);
    Transformations greyscale = new GreyscaleTransform(storage, "dog", "greydog");

    try {
      greyscale.runFunction();
    } catch (Exception e) {

      throw new IllegalArgumentException(String.valueOf(e));
    }

    Image resulting;
    try {
      resulting = storage.getImage("greydog");
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }

    assertEquals("P3\n" +
            "3 3\n" +
            "255\n" +
            "28 28 28\n" +
            "28 28 28\n" +
            "28 28 28\n" +
            "31 31 31\n" +
            "31 31 31\n" +
            "31 31 31\n" +
            "31 31 31\n" +
            "31 31 31\n" +
            "31 31 31\n", resulting.toString());
  }

  @Test
  public void testSepiaTransformations() {

    ImageStorage storage = new ImageStorage();
    Pixel[][] pixels = new Pixel[3][3];

    for (int i = 0; i < 3; i++) {

      for (int j = 0; j < 3; j++) {

        if (i < 1) {
          pixels[i][j] = new Pixel(20, 30, 40);
        } else {
          pixels[i][j] = new Pixel(40, 30, 20);
        }
      }
    }

    Image img = new Image(3, 3, "none", pixels);
    storage.addImage("dog", img);
    Transformations sepia = new Sepia(storage, "dog", "sepiadog");

    try {
      sepia.runFunction();
    } catch (Exception e) {

      throw new IllegalArgumentException(String.valueOf(e));
    }

    Image resulting;
    try {
      resulting = storage.getImage("sepiadog");
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }

    assertEquals("P3\n" +
            "3 3\n" +
            "255\n" +
            "39 34 26\n" +
            "39 34 26\n" +
            "39 34 26\n" +
            "43 37 29\n" +
            "43 37 29\n" +
            "43 37 29\n" +
            "43 37 29\n" +
            "43 37 29\n" +
            "43 37 29\n", resulting.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFileForRunFile() {

    ImageStorage storage = new ImageStorage();
    PPMModification run = new RunFile(storage, "424NOTEXISTENT.png");

    try {
      run.runFunction();
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }
  }

  @Test()
  public void testValidRunFile() {

    ImageStorage storage = new ImageStorage();
    PPMModification run = new RunFile(storage, "res/script.txt");

    try {
      run.runFunction();
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }

    Image resulting;
    try {
      resulting = storage.getImage("ssdog");
    } catch (Exception e) {
      throw new IllegalArgumentException(String.valueOf(e));
    }

    assertEquals("P3\n" +
            "226 223\n" +
            "255\n" +
            "197 191 22", resulting.toString().substring(0, 25));
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
