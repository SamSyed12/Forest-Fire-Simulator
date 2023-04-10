/**
*
* Displays the Forest Fire data in a graphical window.
*
*/
public class DisplayForest {

  /** The graphical window where the forest data is displayed */
  private GridWindow window;

  /** The Forest object displayed */
  private Forest forest;

  /**
  * Constructor initializes the instance variables and displays the forest data
  *
  * @param f The Forest object to display.
  */
  public DisplayForest(Forest f) {
    this.forest = f;
    window = new GridWindow("Forest Fire Simulation", 20);
    window.updateWindow(this.forest.getForestColors());
  }

  /**
  * Updates the graphical window with the current forest data.
  */
  public void update() {
    window.updateWindow(this.forest.getForestColors());
  }

}
