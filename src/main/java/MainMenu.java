import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * GameGUI for Battleship Assignment 3 1701
 *
 * @author Russel Rai
 * @version 1.0
 * @date 11.03.2021
 */
public class MainMenu extends JPanel {

  private static final long serialVersionUID = 1L;
  private static final int HEIGHT = 100;
  private static final int WIDTH = 700;
  private static final int v_SPACE = 50;
  BufferedImage backgroundImage;


  /************** constructor */
  public MainMenu() {
    JPanel titlePanel = new JPanel();
    JLabel gameTitle = new JLabel("BATTLESHIP");

    // creates a box layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setMaximumSize(new Dimension(1600, 900));

    loadImage();
    createFont();

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Font[] allFonts = ge.getAllFonts();
    String fontName = "";
    for (Font font : allFonts) {
      if (font.getFontName().equals("Imperial One.ttf")) {
        fontName = font.getFontName();
      }
    }

    gameTitle.setFont(new Font(fontName, Font.BOLD, 80));

    // adding game title to the main panel
    titlePanel.setMaximumSize(new Dimension(800, 100));
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titlePanel.add(gameTitle);
    titlePanel.setOpaque(false);
    add(titlePanel);

    add(Box.createRigidArea(new Dimension(0, v_SPACE + 20)));

    // creating 4 buttons
    String[] btnTxts = {"Play a Match", "Leaderboard", "About", "Quit"};
    MenuButton[] menuButtons = new MenuButton[btnTxts.length];
    for (int i = 0; i < menuButtons.length; i++) {
      String[] commands = {"Play", "Show Leaderboard", "Show about", "Quit"};

      menuButtons[i] = new MenuButton(WIDTH, HEIGHT, btnTxts[i], commands[i], this);
      menuButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      menuButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
      // adds extra space
      add(Box.createRigidArea(new Dimension(0, v_SPACE)));
    }

    setOpaque(true);
  }


  private void loadImage() {
    try {
      backgroundImage = ImageIO.read(getClass().getResource("images/menuImage.jpg"));
    } catch (IOException e) {
      System.out.println("Couldn't set mainMenu Image: " + e);
    }
  }

  public void createFont() {
    String[] fontList = {"Perfect Dark BRK.ttf", "Imperial One.ttf"};

    for (String fontName : fontList) {
      File file = new File("target/classes/fonts/" + fontName).getAbsoluteFile();
      System.out.println(file);

      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      try {
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));
      } catch (FontFormatException | IOException ignored) {
      }
    }
  }


  @Override
  public void paintComponent(Graphics g) {
    // putting the image as background in the main panel
    g.drawImage(backgroundImage, 0, 0, null);
  }
}

/**********************************************************/
class MenuButton extends JButton implements ActionListener {

  private static final long serialVersionUID = 1L;
  MainMenu base;


  /******************** constructor */
  public MenuButton(int width, int height, String txt, String cmd,
      MainMenu pnl) {

    String fontName = "";
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Font[] allFonts = ge.getAllFonts();
    for (Font font : allFonts) {
      if (font.getFontName().equals("Perfect Dark BRK")) {
        fontName = font.getFontName();
      }
    }

    setText(txt);                                   // text shown in button
    setActionCommand(cmd);                          // button commands
    setMaximumSize(new Dimension(width, height));   // button size
    setFont(new Font(fontName, Font.BOLD, 40));
    addActionListener(this);                        // adding actionListener when created

    this.base = pnl;                                // linking buttons to menu
    this.base.add(this);                            // adding to JPanel
  }


  private void updateComponents(Component comp) {
    this.base.removeAll();      // removes all component from this container
    this.base.add(comp);        // adds new component after
    this.base.revalidate();     // this needs to be called after changing component
    this.base.repaint();        // repaints the container with new component
  }


  @Override
  public void actionPerformed(ActionEvent e) { // button logics
    // displays game board
    if (e.getActionCommand().equals("Play")) {
      GamePage board = new GamePage();
      // removes old panel and shows new panel
      updateComponents(board);
    }

    // displays Leaderboard Page
    if (e.getActionCommand().equals("Show Leaderboard")) {
      LeaderBoard lBoardPage = new LeaderBoard();
      updateComponents(lBoardPage);
    }

    // displays About page
    if (e.getActionCommand().equals("Show about")) {
      AboutPage aboutPage = new AboutPage();
      updateComponents(aboutPage);
    }

    if (e.getActionCommand().equals("Quit")) {
      JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

      // asks for confirmation and quit only if true
      int result = JOptionPane.showConfirmDialog(parent,
          "Are you sure you want to exit the game ?", "Exit Game",
          JOptionPane.YES_NO_OPTION);

      if (result == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    }
  }
}
