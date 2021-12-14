import java.util.Objects;

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

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;

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

//    public String sunkenReport() {
//        return "You sank my " + getName() + "\n";
//    }

//    public String getStatus() {
//        return "\nStatus >> HP: " + hitPoints +
//            ", Score: " + score;
//    }

    @Override
    public String toString() {
        return "Ships >> Lives: " + length +
            ", Name: " + name + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return length == ship.length && name.equals(ship.name) && Objects.equals(symbol, ship.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, symbol, length);
    }
}
