package modifications;

public class MetaData implements IData {
  private int dataPoint;

  public MetaData(int data) {
    this.dataPoint = data;
  }

  public String toString() {
    return this.dataPoint + "";
  }
}
