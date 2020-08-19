import static org.junit.Assert.assertEquals;

import model.Card;
import model.CardColor;
import org.junit.Test;

/**
   * Test cases for Card, verifying that the card methods are correct.
   */
  public class CardTest {

    private Card ace = new Card(1, 1);
    private Card king = new Card(13, 2);
    private Card red = new Card(10, 3);
    private Card black = new Card(3, 4);
    private Card five = new Card(5, 2);

    @Test
    public void getValue() {
      assertEquals(5, five.getValue());
    }

    @Test
    public void getSuit() {
      assertEquals(1, ace.getSuit());
    }

    @Test
    public void getColorRed() {
      assertEquals(CardColor.RED, red.getColor());
    }

    @Test
    public void getColorBlack() {
      assertEquals(CardColor.BLACK, black.getColor());
    }

    @Test
    public void testToString() {
      assertEquals("5♦", five.toString());
    }

    @Test
    public void testEquals() {
      assertEquals(new Card(13, 2), king);
    }

    @Test
    public void testHashCode() {
      assertEquals(26, king.hashCode());
    }
  }

