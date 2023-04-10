import java.awt.Color;
import java.util.*;
/**
* Represents a forest in the ForestFire simulation using a 2-dimensional array.
*
*/
public class Forest {

  /** The two-dimensional array that represents the forest */
  private ForestPatch[][] forest;

  /**
  *
  * Creates a Forest object by making a deep copy of the parameter array.
  *
  * @param f The array to copy into this Forest object.
  */
  public Forest(ForestPatch[][] f) {
    this.forest = this.deepCopy(f);

  } // end of constructor

  /**
  *
  * Returns a deep copy of the 2D parameter array.
  *
  * @param c The 2D array that is being copied.
  *
  * @return A deep copy of the 2D array passed as a parameter.
  */
  private ForestPatch[][] deepCopy(ForestPatch[][] c){

    ForestPatch[][] newList = new ForestPatch[c.length][c[0].length];

    for(int i = 0; i < c.length; i++) {
      for(int j = 0; j < c[0].length; j++){
        newList[i][j] = new ForestPatch(c[i][j].getState());
      }
    }
    return newList;
  } // end of method


  /**
  *
  * Returns a deep copy of the array representing this Forest object.
  *
  * @return A deep copy of the array representing this Forest object.
  */
  public ForestPatch[][] getForest() {
    // Project 2: write this method
    ForestPatch[][] copy;
    copy = this.deepCopy(this.forest);

    return copy;
  } // end of getForest method

  /**
  *
  * Returns an array of the Color objects associated with each patch in this forest.
  *
  * @return An array of the Color objects associated with each patch in this forest.
  */
  public Color[][] getForestColors(){

    Color[][] colorList = new Color[this.forest.length][this.forest[0].length];
    String s = "";

    for(int i = 0; i < this.forest.length; i++) {
      for(int j = 0; j < this.forest[0].length; j++){
        colorList[i][j] = this.forest[i][j].getState().getColor();
      }
    }
    return colorList;
  } // end of getForestColors method

  /**
  *
  * Runs one step of the forest fire simulation. The next state of forest patch
  * is determined based on the current state of its neighbors.
  */
  public void runOneStep() {

    ForestPatch[][] copi  = this.deepCopy(this.forest);

    for(int i = 0; i < this.forest.length; i++){
      for(int j = 0; j < this.forest[0].length; j++){
        ForestState x = this.forest[i][j].nextState(this.neighborPatchProvider(i,j));
        copi[i][j].setState(x);
      }
    }
    this.forest = copi;

  } // end of runOneStep method

  /**
  * Returns an array that consists of neighbor forest patch objects for a particular forest patch.
  *
  * @param v1 The row in which the particular forest patch is located
  *
  * @param v2 The col in which the particular forest patch is located
  *
  * @return An array of neighbor forest patch objects associated with a particular patch.
  */
  private ForestPatch[] neighborPatchProvider(int v1, int v2){
    //create ArrayList to which neigboring forest patches are added.
    ArrayList<ForestPatch> fp = new ArrayList<ForestPatch>();

    //no neighbor below when at the bottom of the grid.
    if (v1 != this.forest.length-1){
      fp.add(this.forest[v1+1][v2]);
    }
    //no neighbor above when at the top of the grid.
    if (v1 != 0){
      fp.add(this.forest[v1-1][v2]);
    }
    //no neighbor to the right when at the right end of the grid.
    if (v2 != this.forest[0].length-1){
      fp.add(this.forest[v1][v2+1]);
    }
    //no neighbor to the left when at the left end of the grid.
    if (v2 != 0){
      fp.add(this.forest[v1][v2-1]);
    }

    //Create array that is the same size as the Forest Patch array list to hold all neighboring forest patch objects.
    ForestPatch[] forestP = new ForestPatch[fp.size()];

    //Add neighboring forest patches to the Forest Patch Array and then return this array.
    for (int iw = 0; iw < fp.size(); iw++){
      ForestPatch fr = fp.get(iw);
      forestP[iw] = fr;
    }

    return forestP;

  } // end of neighborPatchProvider method

  /**
  *
  * Returns a string representation of this forest where each patch is represented
  * by its enum value. The string has one line per row of the forest.
  *
  * @return A string representation of this forest.
  */
  @Override
  public String toString(){

    String s = "";
    String d = "";

    for(int i = 0; i < forest.length; i++){
      for(int j = 0; j < forest[0].length; j++){
        s+= this.forest[i][j].getState().getChar();
      }
      d+= s + "\n";
      s = "";
    }

    return d;
  } // end of toString method



}
