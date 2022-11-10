package modifications.Transformations;
import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;
import model.KernalModels.Kernel;
import model.Pixel;
import modifications.AbstractTransform;
import modifications.ModificationUtils;


public class Transformations extends AbstractTransform {

  public Transformations(ImageStorage imageStorage, String imageName,
                         Kernel kernel, String destName){
    super(imageStorage,imageName,kernel,destName);
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, applyFunction(image));
  }

  @Override
  protected Image applyFunction(Image img){
    int width = img.getWidth();
    int height = img.getHeight();
    Pixel[][] pixels = img.getPixels();
    Pixel[][] newImg = new Pixel[height][width];

    for (int row = 0;  row < height;  row++){
      for (int col = 0; col < width; col++){
        newImg[row][col] = getNewPixel(row,col,pixels);
      }
    }

    return new Image(height,width,newImg);
  }

  private Pixel getNewPixel(int row, int col, Pixel[][] pixels ){
    int ogR = pixels[row][col].getR();
    int ogG = pixels[row][col].getG();
    int ogB = pixels[row][col].getB();

    int[] ogRGB = new int[]{ogR,ogG,ogB};

    return new Pixel(multiplyMatrices(0,ogRGB),
            multiplyMatrices(1,ogRGB),
            multiplyMatrices(2,ogRGB));

  }

  private int multiplyMatrices(int startRow, int[] rgb){
    double newVal = 0;
    for (int col = 0; col <= 2; col++){
      newVal += this.kernel.getKernel()[startRow][col] * rgb[col];
    }
    return ImageUtil.clamp((int) newVal);
  }
}
