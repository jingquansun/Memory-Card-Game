package controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.MemoryOperations;

/**
 * Represents a Controller for Freecell: handle user moves by executing them using the model.
 */
public class MemoryController implements IMemoryController {

  Readable rd;
  final Appendable ap;

  /**
   * Constructor for the freecell controller with a readable and appendable to take input and give
   * output.
   *
   * @param rd reads and handles inputs from the user
   * @param ap appends and handles outputs based on inputs from the user
   */
  public MemoryController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Must be initialized");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List deck, MemoryOperations model, int numRows, int flipped) {
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Deck and Model cannot be null");
    }
    try {
      model.startGame(deck, numRows, flipped);
    } catch (IllegalArgumentException ia) {
      apPrint(ap, "Could not start game.");
      return;
    }

   int count = flipped;

    Scanner scan = new Scanner(rd);
    String rowNumber = "";
    String cardIndex = "";

    while (scan.hasNext() && !model.isGameOver() && count > 0) {
      String current = scan.next();

      if (rowNumber.equals("")) {
        String row = checkInput(current);
        if (row.contains("Invalid")) {
          apPrint(ap, row);
          return;
        } else {
          rowNumber = row;
        }
      } else if (cardIndex.equals("")) {
        String index = checkInput(current);
        if (index.contains("Invalid")) {
          apPrint(ap, index);
          return;
        } else {
          cardIndex = index;
        }
      }
      if (!rowNumber.equals("") && !cardIndex.equals("")) {
        try {
          model.guessCard(Integer.parseInt(rowNumber), Integer.parseInt(cardIndex));
          count -= 1;
          apPrint(ap, model.getGameState() + "\n");
        } catch (IllegalArgumentException ia) {
          apPrint(ap, "Invald move. Try again. " + ia.getMessage() + "\n");
        }
      }
      if (model.isGameOver()) {
        apPrint(ap, model.getGameState() + "\nGame over.\n");
        return;
      }
      rowNumber = "";
      cardIndex = "";
    }
    if (!scan.hasNext() && !model.isGameOver() && !(count == 0)) {
      throw new IllegalStateException("Game not over, looking for input");
    }
  }

  /**
   * @param input
   * @return
   */
  private String checkInput(String input) {
    if (validInt(input)) {
      if (Integer.parseInt(input) >= 0) {
        return input;
      } else {
        return "Invalid row, please try again.\n";
      }
    }
    return "";
  }

  /**
   * @param s
   * @return
   */
  private boolean validInt(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException nf) {
      return false;
    }
  }

  /**
   * @param ap
   * @param s
   */
  private void apPrint(Appendable ap, String s) {
    try {
      ap.append(s);
    } catch (IOException io) {
      io.printStackTrace();
    }
  }
}
