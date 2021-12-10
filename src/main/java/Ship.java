
/**
 * Ship class for game Battleship Assignment 3 1701
 *
 * @date 11.03.2021
 * @author Russel Rai
 * @version 1.0
 */
public class Ship {
    private final String name;
    private final String symbol;

    private final int length;
    private int intactParts;

    private static int hitPoints; // all ships will share same hitPoints
    private static int score = 0; // all ships will share same score

    private boolean sunk;
    private boolean gotHit;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.intactParts = getLength();
        hitPoints += getLength();

        switch (name) {// sets the symbol for ships in initialisation
            case "Patrol Boat":
                this.symbol = "P";
                break;
            case "Destroyer":
                this.symbol = "D";
                break;
            case "Battleship":
                this.symbol = "B";
                break;
            case "Aircraft Carrier":
                this.symbol = "A";
                break;
            case "Submarine":
            default:
                this.symbol = "S";
                break;
        }
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

    public int getHitPoints() {
        return hitPoints;
    }

    public int getScore() {
        return score;
    }

    public void resetHitpoints() {
        hitPoints = 0;
    }

    public void hit() { // call this when ship is hit
        intactParts--;
        hitPoints--;
        gotHit = true;
        score++;
    }

    public void missed() {
        if (score <= 0) {
            score = 0;
        } else {
            score--;
        }
    }

    public boolean sunken() { // check if ship is sunken
        if (intactParts == 0) {
            sunk = true;
            score += getLength() * 2; // e.g. 5 * 2 = 10pts is added
            return true;
        }
        return false;
    }

    public boolean isSunk() {
        return sunk;
    }
    public boolean isHit() {
        return gotHit;
    }

    public String sunkenReport() {
        return "You sank my " + getName() + "\n";
    }

    public void resetScore() {
        score = 0;
    }

    public String getStatus() {
        return "\nStatus >> HP: " + hitPoints +
            ", Score: " + score;
    }

    @Override
    public String toString() {
        return "Ships >> Lives: " + length +
            ", Name: " + name + "\n";
    }
}
