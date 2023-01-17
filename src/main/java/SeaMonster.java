public class SeaMonster {

  private String name;
  private String symbol;
  private int length;
  private boolean isHit;

  public SeaMonster(String name, int length, String symbol) {
    this.name = name;
    this.length = length;
    this.symbol = symbol;
    this.isHit = false;
  }

  public String getName() {
    return name;
  }

  public int getLength() {
    return length;
  }

  public boolean isHit() {
    return isHit;
  }

  public void setHit(boolean hit) {
    isHit = hit;
  }

  public String getSymbol() {
    return symbol;
  }

  @Override
  public String toString() {
    return name + ", length= " + length;
  }
}
