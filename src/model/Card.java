package model;

/**
 * Represents a single card in the 52 cards of Freecell game. Each card has a value and a suit. Each
 * card is either currently faced up or down.
 */
public class Card {

  private final int value;
  private final int suit;
  private final CardColor color;
  private boolean faceUp;


  /**
   * Constructor for a card.
   *
   * @param value the value of the card, can be from 1 (Ace) to 13 (King), with 11 (Jack) and 12
   *              (Queen).
   * @param suit  the suit of the card, can be clubs, diamonds, hearts, or spades. clubs and spades
   *              must be black, diamonds and hearts must be red.
   */
  public Card(int value, int suit) {
    if (value <= 13 && value >= 1 && suit <= 4 && suit >= 1) {
      this.faceUp = false;
      this.value = value;
      this.suit = suit;
      if (suit == 1 || suit == 4) {
        this.color = CardColor.BLACK;
      } else {
        this.color = CardColor.RED;
      }
    } else {
      throw new IllegalArgumentException("Invalid Card");
    }
  }

  /**
   * Getter for whether the card is currently face up or not.
   *
   * @return the boolean that represents the card facing up if true
   */
  public boolean getFace() {
    return this.faceUp;
  }

  /**
   * Reverses the current face state of the card
   */
  public void flipCard() {
    this.faceUp = !faceUp;
  }

  /**
   * Gets the string that represents whether a card is face up or down.
   *
   * @return the string faced up in the card is faced up, and faced down if it's not
   */
  public String printFace() {
    if (faceUp) {
      return "Faced up";
    } else {
      return "Faced down";
    }
  }

  /**
   * Getter for the card value.
   *
   * @return the value of the card
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Getter for the card suit.
   *
   * @return the suit of the card
   */
  public int getSuit() {
    return this.suit;
  }

  /**
   * Getter for the card color.
   *
   * @return the color of the card
   */
  public CardColor getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return number() + symbol();
  }

  /**
   * Helper to get the symbol of the card.
   *
   * @return the String of the symbol of the card
   */
  private String symbol() {
    switch (this.suit) {
      case 1:
        return "♣";
      case 2:
        return "♦";
      case 3:
        return "♥";
      case 4:
        return "♠";
      default:
        throw new IllegalArgumentException("Invalid Suit");
    }
  }

  /**
   * Helper to get the number or letter of the card.
   *
   * @return the number of letter of the card
   */
  private String number() {
    switch (this.value) {
      case 1:
        return "A";
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      default:
        return Integer.toString(getValue());
    }
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Card)) {
      return false;
    }
    return this.value == ((Card) that).value && this.suit == ((Card) that).suit;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.value + 13);
  }
}
