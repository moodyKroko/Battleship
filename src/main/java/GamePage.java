import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GameGUI for Battleship Assignment 3 1701
 *
 * @author Russel Rai
 * @version 1.0
 * @date 11.03.2021
 */
public class GamePage extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;

  private final GameBoard board;

  private Image backgroundImage;

  public GamePage() { // constructor
    // initialise new panels
    board = new GameBoard();

    // layout of the main container
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setMaximumSize(new Dimension(1600, 900));

    // get background image and save it
    try {
      backgroundImage = ImageIO.read(getClass().getResource("images/boardBG.jpg"));
    } catch (IOException e) {
      System.out.println("Couldn't set background Image: " + e);
    }

    //status label
    JLabel statusLabel = new JLabel("Status Panel");
    labelFormat(statusLabel);

    // shipList label
    JLabel shipAliveLabel = new JLabel("Ships List");
    labelFormat(shipAliveLabel);

    // ship destroyed label
    JLabel shipSunkLabel = new JLabel("Ships Destroyed");
    labelFormat(shipSunkLabel);

    // update label
    JLabel updateLabel = new JLabel("Update Panel");
    labelFormat(updateLabel);

    // status Panels
    //  ||
    JPanel statusPanel = new JPanel();
    statusFormat(statusPanel, board.statusPanel, statusLabel);
    statusFormat(statusPanel, board.shipAlive, shipAliveLabel);
    statusFormat(statusPanel, board.shipDestroyed, shipSunkLabel);

    //  ||
    JPanel updatePanel = new JPanel();
    statusFormat(updatePanel, board.updatesPanel, updateLabel);

    // status Container that contains 2 status panel
    //<==
    JPanel statusContainer = new JPanel();
    statusContainer.setMaximumSize(new Dimension(500, 900));
    statusContainer.setLayout(new BoxLayout(statusContainer, BoxLayout.Y_AXIS));
    statusContainer.setOpaque(false);
    statusContainer.add(addGap(450, 50));
    statusContainer.add(statusPanel);
    statusContainer.add(updatePanel);

    // creating a back button to main menu button
    JButton pauseButton = new JButton("Pause");
    buttonFormat(pauseButton);

    // add all components to the main JPanel
    add(statusContainer);
    int h_SPACE = 50;
    add(addGap(h_SPACE, 0)); // add gaps
    add(board);              // add to the window/JFrame
    add(addGap(h_SPACE, 0)); // add gaps
    add(pauseButton);
    setOpaque(true);
  }

  ///////////////////////////////////////
  private void buttonFormat(JButton button) {
    button.setToolTipText("Click to Pause the game");
    button.setActionCommand("Pause");
    button.setFont(new Font("Perfect Dark (BRK)", Font.BOLD, 30));
    int BTN_WIDTH = 150;
    int BTN_HEIGHT = 80;
    button.setMaximumSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    // below removes the background of the button
    button.setFocusPainted(false);
    button.setOpaque(false);
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.setMnemonic(KeyEvent.VK_P);
    // listen for any event from the button
    button.addActionListener(this);
  }

  //////////////////////////////////////
  private void statusFormat(JPanel panel, Container object, JLabel label) {
    panel.add(label);
    int GAP = 25;
    panel.add(addGap(0, GAP));
    panel.add(object);
    panel.add(addGap(0, GAP));
    panel.setOpaque(false);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
  }

  /////////////////////////////////////
  private void labelFormat(JLabel label) {
    label.setFont(new Font("Segoe UI", Font.BOLD, 20));
    label.setAlignmentX(Container.CENTER_ALIGNMENT);
  }

  ////////////////////////////////////
  private Component addGap(int i, int j) {
    return Box.createRigidArea(new Dimension(i, j));
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(backgroundImage, 0, 0, null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Pause")) {
      int pauseOption = JOptionPane.showConfirmDialog(this, "Continue the Game ??", "Pause", JOptionPane.YES_NO_OPTION);

      if (pauseOption == JOptionPane.NO_OPTION) {
        MainMenu mainMenu = new MainMenu();
        board.shipsList.get(0).resetHitpoints();
        board.shipsList.get(0).resetScore();

        removeAll();
        add(mainMenu);
        revalidate();
        repaint();
      }
    }
  }
}
