import java.util.Random;
import java.awt.Color;

// A note about the DO NOT ALTER comments:
// Your code is expected to pass these tests as they are given.
// If you do not pass a test, it will likely be helpful for you to add some
// print statements to the test so you can better understand why the test
// did not pass.

/**
*
* Unit Tests for the Forest class. These unit tests use if-else structures to print
* a message stating if the test passes or fails. These unit tests are not run with
* the JUint framework.
*
*/
public class TestForest {

  /**
  * PROJECT 2: DO NOT ALTER THIS METHOD
  * Tests if the Forest class constructor makes a deep copy of the parameter array.
  */
  public void testConstructorDeepCopy() {
    System.out.println("\nBegin testConstructorDeepCopy");

    ForestPatch[][] myForestArray = new ForestPatch[2][3]; // Array to pass to Forest constructor

    myForestArray[0][0] = new ForestPatch(ForestState.ASH);
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_LOW);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_MED);
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[1][2] = new ForestPatch(ForestState.BURN_MED);

    Forest myForest = new Forest(myForestArray);

    // If a deep copy has been made, a change to one of the ForestPatch object
    // in the local variable myForestArray will not be reflected in the Forest
    // object's array.
    myForestArray[0][0].setState(ForestState.BURN_MILD);
    myForestArray[0][1].setState(ForestState.ASH);
    myForestArray[0][2].setState(ForestState.GROW_LOW);
    myForestArray[1][0].setState(ForestState.GROW_MED);
    myForestArray[1][1].setState(ForestState.GROW_HIGH);
    myForestArray[1][2].setState(ForestState.BURN_HOT);

    ForestPatch[][] fromForest = myForest.getForest();

    // Ensure the Forest object's array still has the original values
    if(fromForest[0][0].getState() != ForestState.ASH
      || fromForest[0][1].getState() != ForestState.GROW_LOW
      || fromForest[0][2].getState() != ForestState.GROW_MED
      || fromForest[1][0].getState() != ForestState.GROW_HIGH
      || fromForest[1][1].getState() != ForestState.BURN_HOT
      || fromForest[1][2].getState() != ForestState.BURN_MED) {
        System.out.println("ERROR testConstructorDeepCopy: Forest object does not have deep copy of array.");

      } else {
        System.out.println("PASS testConstructorDeepCopy");
      }

    System.out.println("Finish testConstructorDeepCopy");
  } // end of testConstructorDeepCopy method


  /**
  * PROJECT 2: DO NOT ALTER THIS METHOD
  * Tests if the Forest class getForest accessor method returns a deep copy of the instance variable array.
  */
  public void testAcessorDeepCopy() {
    System.out.println("\nBegin testAcessorDeepCopy");

    ForestPatch[][] myForestArray = new ForestPatch[2][3]; // Array to pass to Forest constructor

    myForestArray[0][0] = new ForestPatch(ForestState.ASH);
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_LOW);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_MED);
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[1][2] = new ForestPatch(ForestState.BURN_MED);

    Forest myForest = new Forest(myForestArray);

    ForestPatch[][] fromForest1 = myForest.getForest();

    // If a deep copy has been made, a change to one of the ForestPatch object
    // in the local variable fromForest1 will not be reflected in the array
    // returned by the next call to getForest.
    fromForest1[0][0].setState(ForestState.BURN_MILD);
    fromForest1[0][1].setState(ForestState.ASH);
    fromForest1[0][2].setState(ForestState.GROW_LOW);
    fromForest1[1][0].setState(ForestState.GROW_MED);
    fromForest1[1][1].setState(ForestState.GROW_HIGH);
    fromForest1[1][2].setState(ForestState.BURN_HOT);

    ForestPatch[][] fromForest2 = myForest.getForest();

    // Ensure the Forest object's array still has the original values
    if(fromForest2[0][0].getState() != ForestState.ASH
      || fromForest2[0][1].getState() != ForestState.GROW_LOW
      || fromForest2[0][2].getState() != ForestState.GROW_MED
      || fromForest2[1][0].getState() != ForestState.GROW_HIGH
      || fromForest2[1][1].getState() != ForestState.BURN_HOT
      || fromForest2[1][2].getState() != ForestState.BURN_MED) {
        System.out.println("ERROR testAcessorDeepCopy: Forest accessor does not return deep copy of array.");
      } else {
        System.out.println("PASS testAcessorDeepCopy");
      }

    System.out.println("Finish testAcessorDeepCopy");
  } // end of testAcessorDeepCopy method

  /**
  * PROJECT 2: DO NOT ALTER THIS METHOD
  * Tests if the Forest class getForestColors method returns the correct colors.
  */
  public void testGetForestColors() {
    System.out.println("\nStart testGetForestColors");

    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    myForestArray[0][0] = new ForestPatch(ForestState.ASH);
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_LOW);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_MED);
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[1][2] = new ForestPatch(ForestState.BURN_MED);
    myForestArray[2][0] = new ForestPatch(ForestState.BURN_MILD);
    myForestArray[2][1] = new ForestPatch(ForestState.ASH);
    myForestArray[2][2] = new ForestPatch(ForestState.GROW_LOW);

    Forest myForest = new Forest(myForestArray);

    Color[][] fc = myForest.getForestColors();

    // Ensure the correct colors are returned
    if (!(fc[0][0].equals(Color.black))
        || !(fc[0][1].equals(Color.green))
        || !(fc[0][2].equals(Color.green.darker().darker()))
        || !(fc[1][0].equals(Color.green.darker().darker().darker().darker()))
        || !(fc[1][1].equals(Color.yellow))
        || !(fc[1][2].equals(Color.orange))
        || !(fc[2][0].equals(Color.red))
        || !(fc[2][1].equals(Color.black))
        || !(fc[2][2].equals(Color.green)) ) {
          System.out.println("ERROR testGetForestColors: incorrect color returned.");
        } else {
          System.out.println("PASS testGetForestColors");
        }

    System.out.println("Finish testGetForestColors");
  } // end of testGetForestColors method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 3x3 array with the corners burning and all other elements in high growth.
  * After 1 step, ensures the middle edges are burning, but center element is not buring.
  * After 2 steps, ensures all elements are burning.
  */
  public void testBurningCorners() {
    System.out.println("\nStart testBurningCorners");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.GROW_HIGH); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.BURN_HOT); // corner

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // Center element should not change (GROW_HIGH)
    // Corners should not change (BURN_HOT)
    // Elements on middle edge show now be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.GROW_HIGH
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testBurningCorners part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testBurningCorners part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // All elements should be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testBurningCorners part 2 of 2: Incorrect forest state after two steps.");
      } else {
        System.out.println("PASS testBurningCorners part 2 of 2");
      }

    System.out.println("End testBurningCorners");
  } // end of testBurningCorners method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 3x3 array with the center element burning and all other elements in high growth.
  * After 1 step, ensures the middle elements on the edges are burning, but corner elements are not buring.
  * After 2 steps, ensures all elements are burning.
  */
  public void testBurningCenter() {
    System.out.println("\nStart testBurningCenter");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


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

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    System.out.println(myForest.toString());
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // Center element should not change (GROW_HIGH)
    // Corners should not change (BURN_HOT)
    // Elements on middle edge show now be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.GROW_HIGH
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.GROW_HIGH
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.GROW_HIGH
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.GROW_HIGH) {
        System.out.println("ERROR testBurningCenter part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testBurningCenter part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // All elements should be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testBurningCenter part 2 of 2: Incorrect forest state after two steps.");
      } else {
        System.out.println("PASS testBurningCenter part 2 of 2");
      }

    System.out.println("End testBurningCenter");
  } // end of testBurningCenter method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 3x3 array with the top row burning and all other elements in high growth.
  * After 1 step, ensures the middle row is burning, but bottom row is not buring.
  * After 2 steps, ensures all elements are burning.
  */
  public void testTopBurnsDown() {
    System.out.println("\nStart testTopBurnsDown");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[0][2] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.GROW_HIGH); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.GROW_HIGH); // corner

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // Center element should not change (GROW_HIGH)
    // Corners should not change (BURN_HOT)
    // Elements on middle edge show now be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.GROW_HIGH
      || updatedForest[2][1].getState() != ForestState.GROW_HIGH
      || updatedForest[2][2].getState() != ForestState.GROW_HIGH) {
        System.out.println("ERROR testTopBurnsDown part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testTopBurnsDown part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // All elements should be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testTopBurnsDown part 2 of 2: Incorrect forest state after two steps.");
      } else {
        System.out.println("PASS testTopBurnsDown part 2 of 2");
      }

    System.out.println("End testTopBurnsDown");
  } // end of testTopBurnsDown method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 3x3 array with the bottom row burning and all other elements in high growth.
  * After 1 step, ensures the middle row is burning, but bottom row is not buring.
  * After 2 steps, ensures all elements are burning.
  */
  public void testBottomBurnsUp() {
    System.out.println("\nStart testBottomBurnsUp");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.GROW_HIGH); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[2][2] = new ForestPatch(ForestState.BURN_HOT); // corner

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // Center element should not change (GROW_HIGH)
    // Corners should not change (BURN_HOT)
    // Elements on middle edge show now be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.GROW_HIGH
      || updatedForest[0][1].getState() != ForestState.GROW_HIGH
      || updatedForest[0][2].getState() != ForestState.GROW_HIGH
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testBottomBurnsUp part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testBottomBurnsUp part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // All elements should be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testBottomBurnsUp part 2 of 2: Incorrect forest state after two steps.");
      } else {
        System.out.println("PASS testBottomBurnsUp part 2 of 2");
      }

    System.out.println("End testBottomBurnsUp");
  } // end of testBottomBurnsUp method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 3x3 array with the left column burning and all other elements in high growth.
  * After 1 step, ensures the middle column is burning, but right column is not buring.
  * After 2 steps, ensures all elements are burning.
  */
  public void testLeftBurnsRight() {
    System.out.println("\nStart testLeftBurnsRight");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[1][1] = new ForestPatch(ForestState.GROW_HIGH); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.GROW_HIGH); // corner

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // Center element should not change (GROW_HIGH)
    // Corners should not change (BURN_HOT)
    // Elements on middle edge show now be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.GROW_HIGH
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.GROW_HIGH
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.GROW_HIGH) {
        System.out.println("ERROR testLeftBurnsRight part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testLeftBurnsRight part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // All elements should be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testLeftBurnsRight part 2 of 2: Incorrect forest state after two steps.");
      } else {
        System.out.println("PASS testLeftBurnsRight part 2 of 2");
      }

    System.out.println("End testLeftBurnsRight");
  } // end of testLeftBurnsRight method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 3x3 array with the right column burning and all other elements in high growth.
  * After 1 step, ensures the middle column is burning, but left column is not buring.
  * After 2 steps, ensures all elements are burning.
  */
  public void testRightBurnsLeft() {
    System.out.println("\nStart testRightBurnsLeft");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.GROW_HIGH); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[2][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.BURN_HOT); // corner

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // Center element should not change (GROW_HIGH)
    // Corners should not change (BURN_HOT)
    // Elements on middle edge show now be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.GROW_HIGH
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.GROW_HIGH
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.GROW_HIGH
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testRightBurnsLeft part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testRightBurnsLeft part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    // All elements should be BURN_HOT
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testRightBurnsLeft part 2 of 2: Incorrect forest state after two steps.");
      } else {
        System.out.println("PASS testRightBurnsLeft part 2 of 2");
      }

    System.out.println("End testRightBurnsLeft");
  } // end of testRightBurnsLeft method

  /**
  * A test to ensure Forest runOneStep correctly sets cell's neighbors.
  * Creates a 6x4 array with three elements burning and all other elements in high growth.
  * After 1 step, ensures the correct elements are burning.
  * After 2 steps, ensures the correct elements are burning.
  */
  public void testLargerForest() {
    System.out.println("\nStart testLargerForest");

    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0;
    ForestPatch.burnMed_burnMild = 0;
    ForestPatch.burnMild_ashes = 0;
    ForestPatch.ashes_growLow = 0;
    ForestPatch.growLow_growMed = 0;
    ForestPatch.growMed_growHigh = 0;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 1;


    ForestPatch[][] myForestArray = new ForestPatch[6][4]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.BURN_HOT); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][3] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][3] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[2][3] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[3][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[3][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[3][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[3][3] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[4][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[4][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[4][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[4][3] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[5][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[5][1] = new ForestPatch(ForestState.BURN_HOT);
    myForestArray[5][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[5][3] = new ForestPatch(ForestState.GROW_HIGH); // corner

    Forest myForest = new Forest(myForestArray);
    myForest.runOneStep();
    ForestPatch[][] updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.GROW_HIGH
      || updatedForest[0][3].getState() != ForestState.GROW_HIGH
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.GROW_HIGH
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][3].getState() != ForestState.GROW_HIGH
      || updatedForest[2][0].getState() != ForestState.GROW_HIGH
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][3].getState() != ForestState.BURN_HOT
      || updatedForest[3][0].getState() != ForestState.GROW_HIGH
      || updatedForest[3][1].getState() != ForestState.GROW_HIGH
      || updatedForest[3][2].getState() != ForestState.BURN_HOT
      || updatedForest[3][3].getState() != ForestState.GROW_HIGH
      || updatedForest[4][0].getState() != ForestState.GROW_HIGH
      || updatedForest[4][1].getState() != ForestState.BURN_HOT
      || updatedForest[4][2].getState() != ForestState.GROW_HIGH
      || updatedForest[4][3].getState() != ForestState.GROW_HIGH
      || updatedForest[5][0].getState() != ForestState.BURN_HOT
      || updatedForest[5][1].getState() != ForestState.BURN_HOT
      || updatedForest[5][2].getState() != ForestState.BURN_HOT
      || updatedForest[5][3].getState() != ForestState.GROW_HIGH) {
        System.out.println("ERROR testLargerForest part 1 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testLargerForest part 1 of 2");
      }

    myForest.runOneStep();
    updatedForest = myForest.getForest();

    // Ensure the Forest object's array has the expected values
    if(updatedForest[0][0].getState() != ForestState.BURN_HOT
      || updatedForest[0][1].getState() != ForestState.BURN_HOT
      || updatedForest[0][2].getState() != ForestState.BURN_HOT
      || updatedForest[0][3].getState() != ForestState.GROW_HIGH
      || updatedForest[1][0].getState() != ForestState.BURN_HOT
      || updatedForest[1][1].getState() != ForestState.BURN_HOT
      || updatedForest[1][2].getState() != ForestState.BURN_HOT
      || updatedForest[1][3].getState() != ForestState.BURN_HOT
      || updatedForest[2][0].getState() != ForestState.BURN_HOT
      || updatedForest[2][1].getState() != ForestState.BURN_HOT
      || updatedForest[2][2].getState() != ForestState.BURN_HOT
      || updatedForest[2][3].getState() != ForestState.BURN_HOT
      || updatedForest[3][0].getState() != ForestState.GROW_HIGH
      || updatedForest[3][1].getState() != ForestState.BURN_HOT
      || updatedForest[3][2].getState() != ForestState.BURN_HOT
      || updatedForest[3][3].getState() != ForestState.BURN_HOT
      || updatedForest[4][0].getState() != ForestState.BURN_HOT
      || updatedForest[4][1].getState() != ForestState.BURN_HOT
      || updatedForest[4][2].getState() != ForestState.BURN_HOT
      || updatedForest[4][3].getState() != ForestState.GROW_HIGH
      || updatedForest[5][0].getState() != ForestState.BURN_HOT
      || updatedForest[5][1].getState() != ForestState.BURN_HOT
      || updatedForest[5][2].getState() != ForestState.BURN_HOT
      || updatedForest[5][3].getState() != ForestState.BURN_HOT) {
        System.out.println("ERROR testLargerForest part 2 of 2: Incorrect forest state after one step.");
      } else {
        System.out.println("PASS testLargerForest part 2 of 2");
      }

    System.out.println("End testLargerForest");
  } // end of testRightBurnsLeft method


  /**
  * Main method
  */
  public static void main(String[] args) {
    TestForest tf = new TestForest();

    tf.testConstructorDeepCopy();
    tf.testAcessorDeepCopy();
    tf.testGetForestColors();
    tf.testBurningCorners();
    tf.testBurningCenter();
    tf.testTopBurnsDown();
    tf.testBottomBurnsUp();
    tf.testLeftBurnsRight();
    tf.testRightBurnsLeft();
  }

} // end of TestForest class
