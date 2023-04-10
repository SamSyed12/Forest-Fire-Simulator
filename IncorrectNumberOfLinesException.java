/**
**
* Thrown when the input file does not have the correct number of lines
*/
public class IncorrectNumberOfLinesException extends Exception {

  /**
  * Creates a IncorrectNumberOfLinesException object.
  *
  * @param str The number of lines that were expected.
  */
  public IncorrectNumberOfLinesException(String str1, String str2) {
    super("Input file has does not have the expected number of lines or expected elements in each line, the expected number of of lines is: " + str1 +
    ". The expected number of elements in each line is " + str2);
  }

}
