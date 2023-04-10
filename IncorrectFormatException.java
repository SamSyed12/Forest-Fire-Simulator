/**
**
* Thrown when the input file has incorrect format
*/
public class IncorrectFormatException extends Exception {

  /**
  * Creates a IncorrectFormatException object.
  *
  * @param str The line on which the error was found.
  */
  public IncorrectFormatException(String str) {
    super("Input file has incorrect format, error found on line number: " + str);
  }

}
