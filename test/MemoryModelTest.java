import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Card;
import model.MemoryModel;
import model.MemoryOperations;
import org.junit.Test;


public class MemoryModelTest {

  private MemoryOperations memory = new MemoryModel();
  private List<Card> startingDeck = new ArrayList<>(
      Arrays.asList(new Card(1, 1), new Card(1, 2),
          new Card(1, 3), new Card(1, 4), new Card(2, 1),
          new Card(2, 2), new Card(2, 3), new Card(2, 4),
          new Card(3, 1), new Card(3, 2), new Card(3, 3),
          new Card(3, 4), new Card(4, 1), new Card(4, 2),
          new Card(4, 3), new Card(4, 4), new Card(5, 1),
          new Card(5, 2), new Card(5, 3), new Card(5, 4),
          new Card(6, 1), new Card(6, 2), new Card(6, 3),
          new Card(6, 4), new Card(7, 1), new Card(7, 2),
          new Card(7, 3), new Card(7, 4), new Card(8, 1),
          new Card(8, 2), new Card(8, 3), new Card(8, 4),
          new Card(9, 1), new Card(9, 2), new Card(9, 3),
          new Card(9, 4), new Card(10, 1), new Card(10, 2),
          new Card(10, 3), new Card(10, 4), new Card(11, 1),
          new Card(11, 2), new Card(11, 3), new Card(11, 4),
          new Card(12, 1), new Card(12, 2), new Card(12, 3),
          new Card(12, 4), new Card(13, 1), new Card(13, 2),
          new Card(13, 3), new Card(13, 4)));
  private List<Card> invalidDeck = new ArrayList<>();

  //test to see if the deck size is correct and doesn't change in the memory game
  @Test
  public void getDeck() {
    List<Card> deck = memory.getDeck();
    assertEquals(52, deck.size());
    assertEquals(deck, startingDeck);
  }

  //test to see if cards in a deck is correct
  @Test
  public void getDeck1() {
    List<Card> deck = memory.getDeck();
    assertEquals(deck.get(5), new Card(2, 2));
    assertEquals(deck.get(51), new Card(13, 4));
  }

  //test to see if cards in a deck is correct
  @Test
  public void getDeck2() {
    List<Card> deck = memory.getDeck();
    assertEquals(deck.get(51), new Card(13, 4));
  }

  //test to see if the board is setting up correctly
  @Test
  public void setBoard() {
    memory.startGame(startingDeck, 3, 1);
    ArrayList<ArrayList<Card>> board = memory.getBoard();
    assertNotEquals(board.get(0).get(0), board.get(1).get(0));
    assertEquals(board.size(), 3);
    assertEquals(board.get(0).size(), 4);
  }

  //test to see if the game starts correctly
  @Test
  public void startGame() {
    memory.startGame(startingDeck, 3, 1);
    assertNotEquals(memory.getDeck(), startingDeck);
    assertFalse(memory.isGameOver());
  }

  //test to see if the deck is shuffling correctly
  @Test
  public void shuffle() {
    memory.startGame(startingDeck, 3, 1);
    assertNotEquals(memory.getGameCards(), memory.getDeck());
  }


  //test for getting the game state
  @Test
  public void gameState() {
    memory.startGame(startingDeck, 5, 3);
    System.out.println(memory.getGameState());
  }
}
