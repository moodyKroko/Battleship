import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * Game's main logics
 *
 * @author Russel Rai
 * @version 1.0
 * @date 11.03.2021
 */
public class GameBoard extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;

  private final int BOARD_SIZE = 10;
  private final JButton[][] gameFieldGUI = new JButton[BOARD_SIZE][BOARD_SIZE];
  private final String hitIconName = "/images/hit.png";
  private final String missIconName = "/images/miss.png";
  private final String cetusIconName = "/images/cetus.png";
  private final String krakenIconName = "/images/kraken.png";
  private final String water = "W";
  private final String kraken = "K";
  private final String cetus = "C";
    ArrayList<Ship> shipsList = new ArrayList<>();
  JTextField statusPanel;
  JTextField updatesPanel;
  JTextArea shipAlive;
  JTextArea shipDestroyed;

  /***************/
  public GameBoard() {
    // board panel
    setMaximumSize(new Dimension(800, 800));
    setLayout(new GridLayout(10, 10));
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

    // layout board
    createShips();  // create ships and add it to list
    buildBoard();   // build the board with JButton

    // randomly insert ships to the game board
    placeShips();
    // randomly insert monsters to the game board
      int monsterCount = 2;
      placeMonsters(monsterCount);

    statusPanels();    // sets the status / updates
    setOpaque(false);
  }

  //////////////////////
  public void statusPanels() {
    statusPanel = new JTextField();
    textFieldFormat(statusPanel);
    statusPanel.setMargin(new Insets(0, 140, 0, 0));

    shipAlive = new JTextArea();
    textAreaFormat(shipAlive);

    shipDestroyed = new JTextArea();
    textAreaFormat(shipDestroyed);
    shipDestroyed.setMargin(new Insets(0, 140, 0, 0));

    updatesPanel = new JTextField();
    textFieldFormat(updatesPanel);
    updatesPanel.setMargin(new Insets(0, 170, 0, 0));

    // shows ships info to status Panel
    for (Ship ships : shipsList) {
      shipAlive.setText(shipAlive.getText() + ships.toString());
    }
    statusPanel.setText(shipsList.get(0).getStatus());
  }

  ///////////////////////////
  private void textAreaFormat(JTextArea area) {
    area.setMaximumSize(new Dimension(450, 125));
    area.setForeground(Color.BLACK);
    area.setFont(new Font("Segoe UI", Font.BOLD, 16));
    area.setEditable(false);
    area.setLineWrap(true);
    area.setWrapStyleWord(true);
    area.setMargin(new Insets(0, 95, 0, 0));
    area.setOpaque(true);
    area.setBackground(Color.decode("#99ceff"));
  }

  ///////////////////////////
  private void textFieldFormat(JTextField txtField) {
    txtField.setMaximumSize(new Dimension(450, 50));
    txtField.setForeground(Color.BLACK);
    txtField.setFont(new Font("Segoe UI", Font.BOLD, 16));
    txtField.setMargin(new Insets(0, 95, 0, 0));
    txtField.setEditable(false);
    txtField.setOpaque(true);
    txtField.setBackground(Color.decode("#99ceff"));
  }

  /////////////////////////
  private void buildBoard() {
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        JButton button = new JButton();
        button.setBackground(Color.CYAN);
        button.setFocusPainted(false);
        button.setActionCommand(water);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        setBoard(row, col, button);
        add(button);
      }
    }
  }

  ///////////////////////////////////////////////////
  private void setBoard(int row, int col, JButton cell) {
    gameFieldGUI[row][col] = cell;
  }

  /////////////////////////
  private void createShips() {
    String[] shipNames = {"Patrol Boat", "Destroyer", "Submarine", "Battleship", "Aircraft Carrier"};

    int[] shipsLength = {2, 3, 3, 4, 5};

    for (int i = 0; i < shipsLength.length; i++) {
      shipsList.add(new Ship(shipNames[i], shipsLength[i]));
    }
  }

  //////////////////////////
  private void placeShips() {
    for (int i = 0; i < 5; i++) {
      // boolean isSunk = shipsList.get(i).isSunk();
      if (!(shipsList.get(i).isSunk() || shipsList.get(i).isHit())) { // contains bug
        // delete the expression after ||
        int shipLength = shipsList.get(i).getLength();     // get ships lengths
        String shipSymbol = shipsList.get(i).getSymbol();  // get ship symbol

        boolean vertical = new Random().nextBoolean();  // random alignment
        int col = new Random().nextInt(10);
        int row = new Random().nextInt(10);

        // checks for outOfBound positions
        if (!checkField(row, col, shipLength, vertical)) { // loop breaker
          i--;
          continue; // generates a new location
        }

        // checks the cell and around it
        if (isOccupied(row, col, shipLength, vertical)) { // loop breaker
          i--;
          continue; // generates a new location
        }

        // this also saves the location of the ship for each ship
        // places the ships after every test is passed
        setShip(row, col, shipLength, shipSymbol, vertical);
      }
    }
  }

  private void setShip(int row, int col, int length, String symbol, boolean vertical) {
//    String[][] location = new String[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < length; i++) {
      if (vertical) {
        getCell(row, col).setActionCommand(symbol);
//        location[row][col] = symbol;
        row++;
      } else {
        getCell(row, col).setActionCommand(symbol);
//        location[row][col] = symbol;
        col++;
      }
    }
  }

  ////////////////////////////////////
  private JButton getCell(int row, int col) {
    return gameFieldGUI[row][col];
  }

  //////////////////////// ship collision
  private boolean isOccupied(int row, int col, int shipLength, boolean vertical) {
    if (vertical) {
      for (int i = 0; i < shipLength; i++) {
        if (!getCell(row + i, col).getActionCommand().equals(water)) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < shipLength; i++) {
        if (!getCell(row, col + i).getActionCommand().equals(water)) {
          return true;
        }
      }
    }
    return false;
  }

  //////////////////////////
  private boolean checkField(int row, int col, int shipLength, boolean vertical) {
    if (row < BOARD_SIZE && col < BOARD_SIZE) {
      if (vertical) {
          return (row + shipLength - 1) < BOARD_SIZE;
      } else {
          return (col + shipLength - 1) < BOARD_SIZE;
      }
    }
    return false;
  }

  //////////////////////////////
  private void placeMonsters(int count) {
    for (int seaMonster = 0; seaMonster < count; seaMonster++) {
      int row = new Random().nextInt(10);
      int col = new Random().nextInt(10);

      if (isOccupied(row, col)) {
        seaMonster--;
        continue;
      }
      insertMonster(row, col, seaMonster);
    }
  }

  ////////////////////////////////
  private void insertMonster(int row, int col, int monster) {
    if (monster == 0) {
      getCell(row, col).setActionCommand(kraken);
    } else {
      getCell(row, col).setActionCommand(cetus);
    }
  }

  ///////////////////////////////////
  private void shootShip(int x, int y, String iconName) {
    JButton cell = getCell(x, y);
    setCellIcon(cell, iconName);
    switch (iconName) {
      case hitIconName:
        updatesPanel.setText("My ship was hit!\n");
        break;
      case missIconName:
        updatesPanel.setText("You missed!!\n");
        break;
      case cetusIconName:
        updatesPanel.setText("You hit Cetus\n");
        break;
      case krakenIconName:
        updatesPanel.setText("You hit Kraken\n");
        break;
      default:
        break;
    }
  }

  /////////////////////////////////////////////////////////
  private void setCellIcon(JButton cell, String fileNamePath) {
    try {
      ImageIcon image = new ImageIcon(this.getClass().getResource(fileNamePath));
      cell.setIcon(image);
    } catch (Exception e) {
      System.out.println("Couldn't set cell icon: " + e);
    }
  }

  ////////////////////////////////////// Sea monster collision
  private boolean isOccupied(int row, int col) {
    return !getCell(row, col).getActionCommand().equals(water);
  }

  /////////////////////////////
  private void cetusFuntion() {
    statusPanel.setText("");
    shipDestroyed.setText("");

    removeAll();
    revalidate();
    repaint();
    buildBoard();
    placeShips();
    placeMonsters(1); // < just places kraken
  }

  /////////////////////////////////
  private void updateStatus(int row, int col, JButton button, String command) {
    switch (command) {
      case water:
        buttonFormat(button);
        shootShip(row, col, missIconName);
        shipsList.get(0).missed();
        statusPanel.setText(shipsList.get(0).getStatus());
        break;

      case kraken:
        buttonFormat(button);
        shootShip(row, col, krakenIconName);
        shipsList.get(0).resetScore();
        statusPanel.setText(shipsList.get(0).getStatus());
        break;

      case cetus:
        buttonFormat(button);
        shootShip(row, col, cetusIconName);
        cetusFuntion();
        break;

      case "P":
      case "D":
      case "S":
      case "B":
      case "A":
        buttonFormat(button);
        shootShip(row, col, hitIconName);

        for (Ship ships : shipsList) {
          if (ships.getSymbol().equals(command)) {
            ships.hit();
            if (ships.sunken()) {
              updatesPanel.setText(ships.sunkenReport());
              shipDestroyed.setText(shipDestroyed.getText() + ships.sunkenReport());
            }
          }
          statusPanel.setText(ships.getStatus());
        }
        break;

      default:
        break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    String command = e.getActionCommand();

    Rectangle rectangle = button.getBounds();
    Point point = button.getLocation();

    // calculates cell position
    int row = point.y / rectangle.height;
    int col = point.x / rectangle.width;

    updateStatus(row, col, button, command);

    // if hitpoints falls to 0 end ask if they want to restart or quit
    if (shipsList.get(0).getHitPoints() == 0) {
      int rematch = JOptionPane.showConfirmDialog(this, "Game Over, You want a Rematch ??", "Rematch",
          JOptionPane.YES_NO_OPTION);
      JPanel parent = (JPanel) getParent();

      if (rematch == JOptionPane.YES_OPTION) {
        statusPanel.setText("");
        shipDestroyed.setText("");
        shipsList.get(0).resetScore();
        shipsList.get(0).resetHitpoints();

        parent.removeAll();
        parent.add(new GamePage());
        parent.revalidate();
        parent.repaint();

      } else if (rematch == JOptionPane.NO_OPTION) {
        // exit the game and back to main menu
        shipsList.get(0).resetScore();
        shipsList.get(0).resetHitpoints();

        parent.removeAll();
        parent.add(new MainMenu());
        parent.revalidate();
        parent.repaint();
      }
    }
  }

  ///////////////////////////////////////////////////////
  private void buttonFormat(JButton button) {
    button.setActionCommand("N");        // change to command
    button.setContentAreaFilled(false); // removes the inside of button
    button.setBorderPainted(false);     // removes the border
    button.setOpaque(false);            // makes it transparent
  }
}
