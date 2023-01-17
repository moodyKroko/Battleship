import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameSetup {

  private HashMap<Ship, Boolean> shipList;
  private ArrayList<SeaMonster> monsterList;
  private int hitPoints;

  public GameSetup(HashMap<Ship, Boolean> shipList, ArrayList<SeaMonster> monsterList) {
    this.shipList = shipList;
    this.monsterList = monsterList;
    this.hitPoints = 0;
  }
  public void setUp() {
    createShips();
    createSeaMonster();
    genMaxHitPoints();
  }

  public int getHitPoints() {
    return hitPoints;
  }

  private void genMaxHitPoints() {
    for (Ship ship : shipList.keySet()) {
      hitPoints += ship.getLength();
    }
  }

  private void createShips() {
    Ship[] shipArr = new Ship[5];

    shipArr[0] = new Ship("Patrol Boat", 2, "P");
    shipArr[1] = new Ship("Destroyer", 3, "D");
    shipArr[2] = new Ship("Destroyer1", 3, "D");
    shipArr[3] = new Ship("Submarine", 4, "S");
    shipArr[4] = new Ship("Aircraft Carrier", 5, "A");

    for (Ship ship : shipArr) {
      shipList.put(ship, false);
    }
  }

  private void createSeaMonster() {
    SeaMonster[] monsters = new SeaMonster[2];

    monsters[0] = new SeaMonster("Kraken", 1, "K");
    monsters[1] = new SeaMonster("Cetus", 1, "C");

    monsterList.addAll(Arrays.asList(monsters));
  }

  public ArrayList<SeaMonster> getMonsterList() {
    return monsterList;
  }

  public HashMap<Ship, Boolean> getShipList() {
    return shipList;
  }

}
