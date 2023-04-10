import java.io.*;
import java.util.*;

/**
* IO class for forest fire simulation.
*
*/

public class ForestIO{

  /**
  *
  * Reads a forest data file, uses this data to initialize a 2D Forest Patch array object, and then
  * returns the 2D Forest Patch Array.
  *
  * @param  file The path to the input file
  * @throws FileNotFoundException if specified file is not found
  * @throws IncorrectFormatException if non numeric data in first line of file, if the first line
  *         does not contain the correct number of values, and if the remaining lines do not contain a
  *         character that corresponds to a forest state.
  * @throws IncorrectNumberOfLinesException if the data for the forest array does not match the array
  *         size specified in the first line of the file (too many or too few rows, too many or too
  *         few columns).
  * @return A 2D ForestPatch Array initialized from data read from the inout file.
  */
  public static ForestPatch[][] readFile(String file) throws FileNotFoundException, IncorrectFormatException, IncorrectNumberOfLinesException {

    Scanner inFile = null;
    String line = "";
    String otherLine = "";
    String[] list = null;
    String[] list2 = null;
    Double[] doubList = null;
    int lineNum = 1;
    char c = 'c';

    inFile = new Scanner(new File(file));
    doubList = new Double[10];

    line = inFile.nextLine();
    list = line.split(" ");

    //throw exception if incorrect number of values in list(10 expected)
    if (list.length != 10){
      throw new IncorrectFormatException("1");
    }

    //Turn string values obtained from file to doubles so they're type is compatible with the Forest Patch
    //probabilities and put these values into an array.
    for (int i = 0; i < list.length; i++){
      try {
        Double value = Double.parseDouble(list[i]);
        doubList[i] = value;
      } catch (NumberFormatException e){
        //If String cannot be converted to a double throw exception
        throw new IncorrectFormatException("1");
      }
    }

    //Initialize ForestPatch probabilities using array of doubles containing corresponding data from the file
    ForestPatch.burnHot_burnMed = doubList[0];
    ForestPatch.burnMed_burnMild = doubList[1];
    ForestPatch.burnMild_ashes = doubList[2];
    ForestPatch.ashes_growLow = doubList[3];
    ForestPatch.growLow_growMed = doubList[4];
    ForestPatch.growMed_growHigh = doubList[5];
    ForestPatch.growHigh_burnHot_spon = doubList[6];
    ForestPatch.growHigh_burnHot_neighbor = doubList[7];

    //Convert the last two values in the list of doubles to integers so that they are of right type to initialize
    //the size of the 2D forest patch array.
    Double d1 = doubList[8];
    int i1 = d1.intValue();
    Double d2 = doubList[9];
    int i2 = d2.intValue();

    //Initialize the size of the ForestArray using corresponding data obtained from the file
    ForestPatch[][] forestArray = new ForestPatch[i1][i2];


    for(int k = 0; k < forestArray.length; k++) {

      if (inFile.hasNextLine()){
        otherLine = inFile.nextLine();
        list2 = otherLine.split("");
        lineNum +=1;
      }

      //Throw an exception if file has less lines than the value specified in the input file(not enough rows)
      else{
        throw new IncorrectNumberOfLinesException(String.valueOf(i1), String.valueOf(i2));
      }

      for(int p = 0; p < forestArray[0].length; p++){

        // Throw an exception if the line does not the expected number of characters as specified in the input
        // file(either too many or too few columns)
        if (list2.length != forestArray[0].length){
          throw new IncorrectNumberOfLinesException(String.valueOf(i1), String.valueOf(i2));
        }
        //Ascertain if the characters obtained from the file are characters that correspond to an enum value
        if (list2[p].contains("a") || list2[p].contains("l") || list2[p].contains("m") || list2[p].contains("h")
        || list2[p].contains("b") || list2[p].contains("r") || list2[p].contains("n")){
          c = list2[p].charAt(0);

          //Compare against characters to decide which state the ForestPatch should have, and create a forest patch
          //with this state at position [k][p] in the forestArray
          if (c == 'a'){
            forestArray[k][p] = new ForestPatch(ForestState.ASH);
          }
          else if(c == 'l'){
            forestArray[k][p] = new ForestPatch(ForestState.GROW_LOW);
          }
          else if(c == 'm'){
            forestArray[k][p] = new ForestPatch(ForestState.GROW_MED);
          }
          else if(c == 'h'){
            forestArray[k][p] = new ForestPatch(ForestState.GROW_HIGH);
          }
          else if(c == 'b'){
            forestArray[k][p] = new ForestPatch(ForestState.BURN_HOT);
          }
          else if(c == 'r'){
            forestArray[k][p] = new ForestPatch(ForestState.BURN_MED);
          }
          else{
            forestArray[k][p] = new ForestPatch(ForestState.BURN_MILD);
          }
        }

        //If characters not equal to any of the specified enum values throw exception
        else {
          String ss = String.valueOf(lineNum);
          throw new IncorrectFormatException(ss);
        }
      }
    }
    //Throw an exception if file has more lines than the value specified in the input file(too many rows)
    if(inFile.hasNextLine()){
      throw new IncorrectNumberOfLinesException(String.valueOf(i1), String.valueOf(i2));
    }

    //return the initialized forest
    return forestArray;
  } // end of method



  /**
  *
  * Writes the data from a forest to a file.
  *
  * @param foresttt The forest for which data will be taken and written to a file
  * @param filePath The path to the output file
  * @throws FileNotFoundException if the output file is not found
  */
  public static void writeToFile(ForestPatch[][] foresttt, String filePath) throws FileNotFoundException {

    String r = "";
    String rr = "";
    PrintStream output = null;
    Double[] dList = null;

    //Create an array of doubles to hold Forest Patch probablities
    dList = new Double[8];

    dList[0] = ForestPatch.burnHot_burnMed;
    dList[1] = ForestPatch.burnMed_burnMild;
    dList[2] = ForestPatch.burnMild_ashes;
    dList[3] = ForestPatch.ashes_growLow;
    dList[4] = ForestPatch.growLow_growMed;
    dList[5] = ForestPatch.growMed_growHigh;
    dList[6] = ForestPatch.growHigh_burnHot_spon;
    dList[7] = ForestPatch.growHigh_burnHot_neighbor;

    output = new PrintStream(new File(filePath));

    //Print out the values in the array of doubles onto the first line of the file.
    for(int h = 0; h < dList.length; h++){
      rr = String.valueOf(dList[h]);
      output.print(rr + " ");
    }

    //Add the values for forest height and width onto the end of the first line.
    output.print(String.valueOf(foresttt.length)+ " " + String.valueOf(foresttt[0].length));

    //Move onto the next line.
    output.println();

    //Loop through forest and print out enum value for each forest patch
    for(int d = 0; d < foresttt.length; d++) {
      for(int q = 0; q < foresttt[0].length; q++){
        r = String.valueOf(foresttt[d][q].getState().getChar());
        output.print(r);
      }
      //Move onto the next line when enum value for an entire row has been printed.
      output.println();
    }
    output.close();
  }
}
