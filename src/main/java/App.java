import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * GameGUI for Battleship Assignment 3 1701
 *
 * @author Russel Rai
 * @version 1.0
 * @date 11.03.2021
 */
public class App implements Runnable {

  public static void main(String[] args) {
    // Initialise GameWindow object to get our window
    App game = new App();
    // runs after all pending AWT events have been processed
    javax.swing.SwingUtilities.invokeLater(game);
  }

  @Override
  public void run() {
    // Set System Look and feel

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    } catch (Exception e) {
    }

    // base that creates a desktop window
    JFrame frame = new JFrame("Battleship");

    // Initialising our main panel
    MainMenu newContentPane = new MainMenu();

    // adding our panel to the window
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);
    frame.setLocation(190, 10);
    frame.setPreferredSize(new Dimension(1600, 900));

    // disable the exit from windows
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    // Displays the window
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
  }
}
