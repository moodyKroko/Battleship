import java.awt.Color;
import java.awt.Component;
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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * GameGUI for Battleship Assignment 3 1701
 *
 * @author Russel Rai
 * @version 1.0
 * @date 11.03.2021
 */
public class LeaderBoard extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;

  private Image backgroundImage;

  /***** constructor */
  public LeaderBoard() {
    JPanel playerInfoPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JLabel title = new JLabel("LEADERBOARD");

    int V_SPACE = 50;
    int BTN_WIDTH = 700;
    int BTN_HEIGHT = 100;

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setMaximumSize(new Dimension(1600, 900));

    // getting then saving image
    try {
      backgroundImage = ImageIO.read(getClass().getResource("images/leaderboardImage.jpg"));
    } catch (IOException e) {
      System.out.println("Couldn't set background Image: " + e);
    }

    // panel that contains title
    title.setFont(new Font("Imperial One", Font.PLAIN, 75));
    title.setForeground(Color.WHITE);

    titlePanel.setMaximumSize(new Dimension(800, 100));
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titlePanel.add(title);
    titlePanel.setOpaque(false);

    // panel that contains players info
    playerInfoPanel.setMaximumSize(new Dimension(1500, 650));
    playerInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    playerInfoPanel.setOpaque(false);

    // creating a back button to main menu button
    JButton backButton = new JButton("Return");
    backButton.setToolTipText("Click this to return to main menu");
    backButton.setActionCommand("Return");
    backButton.setFont(new Font("Perfect Dark (BRK)", Font.BOLD, 26));
    backButton.setMaximumSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    backButton.setMnemonic(KeyEvent.VK_R);
    backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    // listen for any event from the button
    backButton.addActionListener(this);

    // add all the components to panel
    add(titlePanel);
    add(playerInfoPanel);
    // adds a separator space between the sub-panel and button
    add(Box.createRigidArea(new Dimension(0, V_SPACE)));
    add(backButton);
    // adds a separator space below button
    add(Box.createRigidArea(new Dimension(0, V_SPACE)));
    setBackground(Color.GREEN);
    setOpaque(true);
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(backgroundImage, 0, 0, null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Return")) {
      MainMenu mainMenu = new MainMenu();

      removeAll();
      add(mainMenu);
      revalidate();
      repaint();
    }
  }
}
