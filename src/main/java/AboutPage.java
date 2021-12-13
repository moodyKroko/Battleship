import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/*
  GameGUI for Battleship Assignment 3 1701

  @date 11.03.2021
 * @author Russel Rai
 * @version 1.0
 */

/***********************************/
public class AboutPage extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;

  BufferedImage backgroundImage;

  /**** constructor */
  public AboutPage() {
    JLabel title = new JLabel("ABOUT");
    JPanel titlePanel = new JPanel();
    JPanel aboutPanel = new JPanel();

    int V_SPACE = 50;
    int BTN_WIDTH = 700;
    int BTN_HEIGHT = 100;

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setMaximumSize(new Dimension(1600, 900));

    loadImage();

    title.setFont(new Font("Imperial One", Font.PLAIN, 75));

    // settings for the title panel
    titlePanel.setMaximumSize(new Dimension(800, 100));
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titlePanel.add(title);
    titlePanel.setOpaque(false);

    // panel that contains info
    aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.X_AXIS));
    aboutPanel.setMaximumSize(new Dimension(1500, 650));
    aboutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    aboutPanel.setOpaque(false);
    setMessage(aboutPanel);

    // creating a back button to return to main menu
    JButton backButton = new JButton("Return");
    backButton.setActionCommand("Return");
    backButton.setToolTipText("Click this to return to main menu");
    backButton.setFont(new Font("Perfect Dark (BRK)", Font.BOLD, 26));
    backButton.setMnemonic(KeyEvent.VK_R);
    backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    backButton.setMaximumSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    // listen to button event
    backButton.addActionListener(this);

    // add all the components to the panel
    add(titlePanel);
    add(aboutPanel);
    // adds a separator space between the sub-panel and button
    add(Box.createRigidArea(new Dimension(0, V_SPACE)));
    add(backButton);
    // adds a separator below the button
    add(Box.createRigidArea(new Dimension(0, V_SPACE)));
    setBackground(Color.GREEN);
    setOpaque(true);
  }

  private void loadImage() {
    try {
      backgroundImage = ImageIO.read(this.getClass().getResource("images/aboutPageImage.jpg"));
    } catch (IOException e) {
      System.out.println("Couldn't set background Image: " + e);
    }
  }

  private void setMessage(JPanel aboutPanel) {
    JTextField message = new JTextField();
    message.setText("This game is made by Russel for Assignment 3 CS1701");
    message.setMargin(new Insets(0, 100, 0, 0));
    message.setFont(new Font("Imperial One", Font.BOLD, 30));
    message.setForeground(Color.BLACK);
    message.setOpaque(false);
    message.setBorder(BorderFactory.createEmptyBorder());
    aboutPanel.add(message);
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(backgroundImage, 0, 0, null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Return")) {
      // initialise new main menu
      MainMenu mainMenu = new MainMenu();

      removeAll();
      add(mainMenu);
      revalidate();
      repaint();
    }
  }
}
