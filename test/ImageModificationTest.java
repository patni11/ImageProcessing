import org.junit.Before;
import org.junit.Test;

import Model.Image;
import Model.ImageStorage;
import clientInteractions.ImageUtil;
import modifications.Brighten;
import modifications.Greyscale;
import modifications.Load;
import modifications.PPMModification;
import modifications.Save;

public class ImageModificationTest {

  ImageStorage storage;
  Image random5x5;
  Image redGreenBlue;
  Image redWhiteRed;
  Image koala;
  @Before
  public void setup(){
    redWhiteRed = ImageUtil.readPPM("testImages/redWhiteRed.ppm");
    redGreenBlue = ImageUtil.readPPM("testImages/redGreenBlue.ppm");
    random5x5 = ImageUtil.readPPM("testImages/random5x5.ppm");
    koala = ImageUtil.readPPM("Koala.ppm");

    //load images
    storage = new ImageStorage();
    PPMModification load = new Load(storage);
    load.modifyImage("redWhiteRed",redWhiteRed);
    load.modifyImage("redGreenBlue",redGreenBlue);
    load.modifyImage("random5x5",random5x5);
    load.modifyImage("koala",koala);

  }

  @Test
  public void testBrighten(){
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
}
