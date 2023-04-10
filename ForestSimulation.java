import java.io.*;
import java.util.Random;
/**
* Runs the forest fire simulation.
*
*/
public class ForestSimulation {

  /** The simulated forest */
  private Forest simForest;

  /** The grid display of the simulated forest */
  private DisplayForest simDisplay;

  /**
  *
  * Creates a ForestSimulation object with a 3x3 grid. All transition probablities
  * are set to 0.5, probability of spontaneous combustion is set to 0. The grid
  * is initialized with the middle patch burning and all other patches in the
  * high growth state.
  *
  * @param inFile The file containing the data for the starting forest.
  */
  public ForestSimulation() {
    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0.5;
    ForestPatch.burnMed_burnMild = 0.5;
    ForestPatch.burnMild_ashes = 0.5;
    ForestPatch.ashes_growLow = 0.5;
    ForestPatch.growLow_growMed = 0.5;
    ForestPatch.growMed_growHigh = 0.5;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 0.5;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.BURN_HOT); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.GROW_HIGH); // corner

    simForest = new Forest(myForestArray);

    simDisplay = new DisplayForest(simForest);

  } // end of default constructor

  /**
  *
  * Creates a ForestSimulation object using data from a file as the starting forest, if the input file
  * cannot be opened the program is terminated. If there is an error in the files data the program is
  * terminated. Two errors are caught are, these are: incorrect format of data in file, and wrong number
  * of lines in a file.
  *
  * @param inFile The path to the input file
  */
  public ForestSimulation(String inFile) {

    ForestPatch[][] forestt = null;
    ForestPatch.rand = new Random(226);

    try {
      forestt = ForestIO.readFile(inFile);
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
      System.out.println("Terminating Program.");
      System.exit(1);
    }
    catch(IncorrectFormatException e){
      e.printStackTrace();
      System.out.println("Terminating Program.");
      System.exit(1);
    }
    catch(IncorrectNumberOfLinesException e){
      e.printStackTrace();
      System.out.println("Terminating Program.");
      System.exit(1);
    }

    this.simForest = new Forest(forestt);

    this.simDisplay = new DisplayForest(simForest);

  } // end of constructor

  /**
  *
  * Runs the forest fire simulation for the specified number of steps then saves
  * the ending state to the output file, terminates if output file is not found.
  *
  * @param steps The number of steps of the simulation
  *
  * @param outFile The path to the output file
  */
  public void runSimulation(int steps, String outFile) {

    int count = 0;

    while(count < steps){
      this.simForest.runOneStep();
      this.simDisplay.update();

      count +=1;
    }

    try{
      ForestIO.writeToFile(this.simForest.getForest(), outFile);
    } catch (FileNotFoundException f){
      f.printStackTrace();
      System.out.println("Terminating Program.");
      System.exit(1);
    }

    System.out.println("Simulation Finished");
  } // end of runSimulation method

  /**
  *
  * main method starts the simulation. Command line arguments: the input
  * file pathname, the output file pathname, and the number of simulation steps.
  *
  * @param args The pathnames for the input and output files
  */
  public static void main(String[] args) {

    // Validate the command line arguments.
    if(args.length != 3) {
      System.out.println("Expected 3 command line arguments (Input file, output file, and number of steps): Found " + args.length + ".");
      System.out.println("Terminating program");
      System.exit(1);
    }

    // Create a ForestSimulation object.
    ForestSimulation ForestSim = new ForestSimulation(args[0]);

    // Run the simulation for the specified number of steps.
    ForestSim.runSimulation(Integer.valueOf(args[2]), args[1]);

  } // end of main method
}
