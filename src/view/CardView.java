package view;

import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JFrame;
import model.Card;
import model.CardColor;

/**
 * Need to use the model board, and then parse through the rows and columns to find the positions of
 * each card using their height and width
 */

/**
 *
 */
public class CardView extends JFrame implements MemoryView {

  private static Graphics2D g;
  private int LENGTH = 20;
  private int WIDTH = 45;

  /**
   *
   * @param g
   */
  public CardView(Graphics2D g) {
    this.g = g;
  }

  /**
   *
   * @param c
   */
  public static void drawFaceDown(Card c) {
    g.setColor(Color.BLUE);
    // g.fillRect(c.getxPos(), c.getyPos(), 20, 40);
    g.setColor(Color.YELLOW);
    int[] x = {42, 52, 72, 52, 60, 40, 15, 28, 9, 32, 42};
    int[] y = {38, 62, 68, 80, 105, 85, 102, 75, 58, 20, 38};
    g.fillPolygon(x, y, 5);
  }

  /**
   *
   * @param c
   */
  public void drawCard(Card c) {
    Color suitColor;
    if (c.getColor() == CardColor.BLACK) {
      suitColor = Color.BLACK;
    } else {
      suitColor = Color.RED;
    }
    g.setColor(Color.WHITE);
    //g.fillRect(c.getxPos(), c.getyPos(), 20, 40);
    g.setColor(suitColor);
    // g.drawString(c.toString(), ((c.getxPos() + 20) / 2), ((c.getyPos() + 40) / 2));
  }
}
