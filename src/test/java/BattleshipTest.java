import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipTest {

  // declares 3 test case
  Ship destroyer;
  Ship submarine;
  Ship airCraft;

  @BeforeEach
  void setUp() {
    // initialises the 3 objects
    destroyer = new Ship("Destroyer", 3);
    submarine = new Ship("Submarine", 3);
    airCraft = new Ship("Aircraft Carrier", 5);
  }

  @Test
  void canCreateShips() {
    // this checks if the objects are properly created and not null
    assertNotNull(destroyer);
    assertNotNull(submarine);
    assertNotNull(airCraft);
  }

  @Test
  void canGetShipNames() {
    // checks if it gets the ship names
    assertEquals("Destroyer", destroyer.getName());
    assertEquals("Submarine", submarine.getName());
    assertEquals("Aircraft Carrier", airCraft.getName());
  }

  @Test
  void canGetShipLength() {
    // gets the ships lengths
    assertEquals(3, destroyer.getLength());
    assertEquals(3, submarine.getLength());
    assertEquals(5, airCraft.getLength());
  }

  @Test
  void testNotEquals() {
    // this checks the stored length of ship to wrong value
    assertNotEquals(2, destroyer.getLength());
    assertNotEquals(5, submarine.getLength());
    assertNotEquals(4, airCraft.getLength());

    // getName
    assertNotEquals("Submarine", destroyer.getName());
  }

  @Test
  void canUpdateAndGetScore() {
    // get player score, use hit() method and updates the score
    assertEquals(0, destroyer.getScore());
    assertEquals(0, submarine.getScore());
    assertEquals(0, airCraft.getScore());

    // if ship gets hit
    destroyer.hit();
    submarine.hit();
    submarine.hit();
    submarine.hit();

    assertEquals(4, destroyer.getScore());
    assertEquals(4, submarine.getScore());

    // get ship object status
    // check if the ship is hit or not
    assertTrue(destroyer.isHit());
    assertFalse(airCraft.isHit());

    // get if sunk
    // check if the ship is sunk or not
    assertTrue(submarine.sunken());
  }
}
