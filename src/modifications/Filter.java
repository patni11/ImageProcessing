package modifications;

import interactions.ImageUtil;
import model.Image;
import model.ImageStorage;
import model.KernalModels.Kernel;
import model.Pixel;

public abstract class Filter implements PPMModification {

  private ImageStorage imgStore;

  private String destName;
  private String imgName;
  private Kernel kernel;

  public Filter(ImageStorage imageStorage, String imageName,
                Kernel kernel, String destName) {
    this.imgStore = imageStorage;
    this.imgName = imageName;
    this.kernel = kernel;
    this.destName = destName;
  }

  @Override
  public void runFunction() throws Exception {
    Image image = ModificationUtils.getImage(this.imgStore, this.imgName);
    this.imgStore.addImage(this.destName, applyFilter(image));
  }

  private Image applyFilter(Image img) {
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
    int[][] rChannel = new int[this.kernel.getDimension()][this.kernel.getDimension()];
    int[][] gChannel = new int[this.kernel.getDimension()][this.kernel.getDimension()];
    int[][] bChannel = new int[this.kernel.getDimension()][this.kernel.getDimension()];
    int imgKernelRow = 0;
    int imgKernelCol = 0;

    for (int i = row - (this.kernel.getDimension()/2) ; i <= row +(this.kernel.getDimension()/2); i++ ){
      for (int j = col - (this.kernel.getDimension()/2) ; j <= col +(this.kernel.getDimension()/2); j++ ){

        Pixel currentPixel;

        try{
          currentPixel = pixels[i][j];
        }catch (ArrayIndexOutOfBoundsException e){
          currentPixel = new Pixel(0,0,0);
        }

        rChannel[imgKernelRow][imgKernelCol] = currentPixel.getR();
        gChannel[imgKernelRow][imgKernelCol] = currentPixel.getG();
        bChannel[imgKernelRow][imgKernelCol] = currentPixel.getB();
        imgKernelCol += 1;
      }
      imgKernelRow += 1;
      imgKernelCol = 0;
    }

    return new Pixel(multiplyMatrices(rChannel),multiplyMatrices(gChannel), multiplyMatrices(bChannel));

  }

  private int multiplyMatrices(int[][] channel){
    double[][] kernel = this.kernel.getKernel();
    int val = 0;
    for (int row = 0; row <= this.kernel.getDimension() - 1; row ++){
      for (int col = 0; col <= this.kernel.getDimension() - 1; col ++){
        int calc = (int) (channel[row][col] * kernel[row][col]);
        val += calc;
      }
    }
    return ImageUtil.clamp(val);
  }
}
