public class Ship {

  private final String name;
  private final String symbol;

  private final int length;
  private int hitCount;

  public Ship(String name, int length, String symbol) {
    this.name = name;
    this.length = length;
    this.symbol = symbol;
    hitCount = 0;
  }

  public int getHitCount() {
    return hitCount;
  }

  public void setHitCount(int hitCount) {
    this.hitCount = hitCount;
  }

  public int getLength() {
    return length;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

//    public String sunkenReport() {
//        return "You sank my " + getName() + "\n";
//    }

//    public String getStatus() {
//        return "\nStatus >> HP: " + hitPoints +
//            ", Score: " + score;
//    }

//  @Override
//  public String toString() {
//    return "Ships >> Lives: " + getHitPoints() +
//        ", Name: " + getName() + "\n";
//  }

  @Override
  public String toString() {
    return getName() + " length: " + getLength();
  }
}
