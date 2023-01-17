import java.util.ArrayList;
import java.util.HashMap;

public class TestClass {

  public static void main(String[] args) {
    HashMap<Ship, Boolean> ships = new HashMap<>();
    ArrayList<SeaMonster> monsters = new ArrayList<>();

    GameSetup shipInterface = new GameSetup(ships, monsters);
    shipInterface.setUp();

    System.out.println(shipInterface.getHitPoints());

    System.out.println(shipInterface.getShipList());
    System.out.println(shipInterface.getMonsterList());

    ArrayList<SeaMonster> newMonster = shipInterface.getMonsterList();
  }
}
