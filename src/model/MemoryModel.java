package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Represents the implementation of the MemoryOperations, maintains the game state and user inputs.
 */
public class MemoryModel implements MemoryOperations {

  protected boolean gameStarted;
  protected ArrayList<ArrayList<Card>> board;
  protected List<Card> cards;
  protected List<Card> correctCards;
  protected List<Card> guessed;
  protected List<Card> notGuessed;
  protected int numOfRows;

  /**
   * Constructor for a MemoryModel when the game has not started yet.
   */
  public MemoryModel() {
    this.gameStarted = false;
    this.board = new ArrayList<>();
    this.cards = new ArrayList<>();
    this.guessed = new ArrayList<>();
    this.notGuessed = new ArrayList<>();
    this.correctCards = new ArrayList<>();
    this.numOfRows = 0;
  }

  @Override
  public List getDeck() {
    List<Card> cards = new ArrayList<>(52);
    ArrayList<Integer> allV =
        new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
    ArrayList<Integer> allS =
        new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    for (Integer integer : allV) {
      for (Integer all : allS) {
        cards.add(new Card(integer, all));
      }
    }
    return cards;
  }

  @Override
  public List getGameCards() {
    return this.cards;
  }

  @Override
  public void startGame(List cards, int rows, int flipped)
      throws IllegalArgumentException {
    if (!validDeck(cards)) {
      throw new IllegalArgumentException("Invalid deck");
    }
    if (rows < 2 || rows > 6) {
      throw new IllegalArgumentException("Number of rows has to be greater than 2 and less than 6");
    }
    if (flipped < 1 || flipped > rows) {
      throw new IllegalArgumentException(
          "Number of cards flipped has to be at least 1 and less than the number of rows");
    }
    this.numOfRows = rows;
    Collections.shuffle(cards);
    this.cards = cards.subList(0, (rows * (rows + 1)));
    this.gameStarted = true;

    this.board = setBoard(rows, this.cards);
    flipCard(flipped);
  }

  /**
   * Flips a certain number of cards in the list of cards for 2 seconds before flipping them face
   * down again.
   *
   * @param flipped the number of cards to be flipped
   */
  private void flipCard(int flipped) {
    List<Card> copy = new ArrayList<>(cards);
    Collections.shuffle(copy);

    this.correctCards = copy.subList(0, flipped);

    for (Card card : correctCards) {
      card.flipCard();
    }
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (Card card : correctCards) {
      card.flipCard();
    }
  }

  /**
   * Sets the board so that there is an appropriate number of rows and columns.
   *
   * @param rows  the number of rows in the game
   * @param cards the cards that should be distributed
   */
  private ArrayList<ArrayList<Card>> setBoard(int rows, List<Card> cards) {
    ArrayList<ArrayList<Card>> board = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      ArrayList<Card> each = new ArrayList<>();
      for (int j = i; j < cards.size(); j += rows) {
        each.add(cards.get(j));
      }
      board.add(each);
    }
    return board;
  }

  /**
   * Validates a deck of cards to make sure that there are no repeated cards and that all 52 cards
   * are in the deck.
   *
   * @param deck the deck of cards to be checked
   * @return true if the deck is valid
   */
  private boolean validDeck(List<Card> deck) {
    int count = 0;
    int list = deck.size();
    for (int i = 0; i < list; i++) {
      Card one = deck.get(i);
      for (int j = i + 1; j < list; j++) {
        Card two = deck.get(j);
        if (two.equals(one)) {
          count += 1;
        }
      }
    }
    return (deck.size() == 52 && count == 0);
  }

  @Override
  public void guessCard(int rowNumber, int cardIndex) {
    if (!validCard(rowNumber, cardIndex)) {
      throw new IllegalArgumentException("This card is not valid.");
    }
    Card guessed = getCard(rowNumber, cardIndex);
    if (guessed.getFace()) {
      throw new IllegalArgumentException("This card has already been correctly picked");
    }
    for (Card card : this.correctCards) {
      if (card.equals(guessed)) {
        this.guessed.add(card);
        this.cards.remove(card);
        this.correctCards.remove(card);
        card.flipCard();
      }
    }
  }

  /**
   * Validates a certain card to see if it exists in the cards in the game.
   *
   * @param rowNumber the row that the card is on
   * @param cardIndex the column that the card is on
   * @return true if the card exists
   */
  private boolean validCard(int rowNumber, int cardIndex) {
    if (!gameStarted) {
      return false;
    }
    if (rowNumber < 0 || cardIndex < 0) {
      return false;
    }
    if (rowNumber > numOfRows || cardIndex > (numOfRows + 1)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isGameOver() {
    if (!gameStarted) {
      return false;
    }
    for (Card card : guessed) {
      if (!card.getFace()) {
        return false;
      }
    }
    return (cards.size() == 0);
  }

  @Override
  public String getGameState() {
    if (!gameStarted) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < numOfRows; i++) {
      result.append("Row").append(i + 1).append(": ");
      for (Card card : board.get(i)) {
        if (board.get(i).indexOf(card) == board.get(i).size() - 1) {
          result.append(card.toString()).append(": ").append(card.getFace()).append("\n");
        } else {
          result.append(card.toString()).append(": ").append(card.getFace() + " ");
        }
      }
    }
    return result.toString();
  }

  /**
   * Gets the given wanted card, only if it is valid.
   *
   * @param rowNumber the row that the card is on
   * @param cardIndex the column that the card is on
   * @return the card if it is valid
   */
  private Card getCard(int rowNumber, int cardIndex) {
    if (!validCard(rowNumber, cardIndex)) {
      throw new IllegalArgumentException("This card is invalid.");
    }
    ArrayList<ArrayList<Card>> temp = this.board;
    return temp.get(rowNumber).get(cardIndex);
  }

  @Override
  public ArrayList<ArrayList<Card>> getBoard() {
    return this.board;
  }

}
