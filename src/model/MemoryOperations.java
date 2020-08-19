package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface for the Memory game, includes the possible moves for the user. Parameterized over
 * the card type, you can substitute K for your own version of cards.
 */
public interface MemoryOperations<K> {

  /**
   * Return a valid and complete deck of cards for a game of Freecell. There is no restriction
   * imposed on the ordering of these cards in the deck. An invalid deck is defined as a deck that
   * has one or more of these flaws: It does not have 52 cards It has duplicate cards It has at
   * least one invalid card (invalid suit or invalid number)
   *
   * @return the deck of cards as a list
   */
  List<K> getDeck();

  /**
   * Returns the list of cards that have been randomly picked for this game given the number of rows
   * and columns needed for the game.
   *
   * @return the list of cards for this game
   */
  List<K> getGameCards();

  /**
   * First, verifies whether the given deck is valid. Then, shuffles the given cards and distributes
   * them according to the wanted number of rows. There will always be one more column than rows.
   * The cards will be face down. After it is distributed, the game will turn a number of random
   * cards face up for a few seconds.
   *
   * @param cards   the given deck of cards
   * @param rows    the wanted number of rows being distributed
   * @param flipped the number of cards to be flipped at once
   * @throws IllegalArgumentException if the deck is invalid or if the number of rows is less than 2
   *                                  or more than 6, or if the number of flipped card is less than
   *                                  1 or more than the number of rows.
   */
  void startGame(List<K> cards, int rows, int flipped);

  /**
   * Checks if the card at the given index in the specified pile - checks whether the given card is
   * one of the flipped cards, it will stay faced up if it is, other wise it will be face down
   * again. If guessed correctly, the card will be added to the guessed list and removed from the
   * original list so that it won't be picked again
   *
   * @param rowNumber the pile number, starting from 0
   * @param cardIndex the card index, starting from 0
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalArgumentException if there is no such card
   */
  void guessCard(int rowNumber, int cardIndex);

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Return the present state of the game as a string
   *
   * @return a formatted string or empty string if the game has not started
   */
  String getGameState();

  /**
   * Getter for the game board.
   *
   * @return the game board
   */
  ArrayList<ArrayList<Card>> getBoard();

}
