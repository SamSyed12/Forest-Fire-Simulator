import java.awt.Color;

/**
*
* Enumeration of forest states. Associates each state with a color.
*
*/
public enum ForestState {
  ASH (Color.black, 'a'),
  GROW_LOW (Color.green, 'l'),
  GROW_MED (Color.green.darker().darker(), 'm'),
  GROW_HIGH (Color.green.darker().darker().darker().darker(), 'h'),
  BURN_HOT (Color.yellow, 'b'),
  BURN_MED (Color.orange, 'r'),
  BURN_MILD (Color.red, 'n');

  /** The color value associated with the enum name */
  private final Color color;

  /** The character value associated with the enum name */
  private final char character;

  /**
  * Constructor for the enum. Assigns the integer value associated with the
  * enum name to the typeCode instance variable.
  *
  * @param code The integer value to associate with the enum name.
  */
  private ForestState(Color c, char x) {
    this.color = c;
    this.character = x;
  }

  /**
  * Returns the color associated with the enum name.
  *
  * @return The color associated with the enum name.
  */
  public Color getColor() { return this.color; }

  /**
  * Returns the character associated with the enum name.
  *
  * @return The character associated with the enum name.
  */
  public char getChar() { return this.character; }
}
