package controller;

import java.util.List;
import model.MemoryOperations;

/**
 * Represents a Controller for the Memory game; handles user moves by executing them using the
 * model
 */
public interface IMemoryController<K> {

  /**
   * This method should start a new game of Memory using the provided model and the number of rows
   * there should be in the game.
   *
   * @param deck    the deck of cards used to play this game
   * @param model   the model used for the game
   * @param numRows the number of rows in the game
   * @param flipped the number of cards being flipped at once
   */
  void playGame(List<K> deck, MemoryOperations<K> model, int numRows, int flipped);

}
