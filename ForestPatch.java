import java.util.Random;

/**
*
* Represents a patch of a forest.
*
*/
public class ForestPatch {
  /** Probability of transitioning from Burning hot to Burning medium */
	public static double burnHot_burnMed;

	/** Probability of transitioning from Burning medium to Burning mild */
	public static double burnMed_burnMild;

	/** Probability of transitioning from Burning mild to Ashes */
	public static double burnMild_ashes;

	/** Probability of transitioning from Ashes to Growing low */
	public static double ashes_growLow;

	/** Probability of transitioning from Growing low to Growing med */
	public static double growLow_growMed;

	/** Probability of transitioning from Growing mid to Growing high */
	public static double growMed_growHigh;

	/** Probability of spontaneously transitioning from Growing high to
	 * Burning hot */
	public static double growHigh_burnHot_spon;

	/** Probability of transitioning from Growing high to Burning hot
	 * due to a burning neighbor */
	public static double growHigh_burnHot_neighbor;

	/** Random number object */
	public static Random rand;

	/** State of this ForestPatch */
	private ForestState state;

	/**
	 * Constructs a forest patch in the given state.
	 *
	 * @param state The state of this forest patch
	 */
	public ForestPatch(ForestState state)
	{
		this.state = state;
	}

	/**
	 * Returns the next state for this patch. The next state is determined based
   * on the current state of this patch, the state of neighboring patches, and
   * the probability of transitioning to the next state. This method does not
   * change the state of the patch in order to enable the simulation to make
   * the change at the appropriate moment in the simulation.
	 *
	 * @param neighbors The neighbors of this patch.
	 * @return the next state for this patch
	 */
	public ForestState nextState(ForestPatch[] neighbors)
	{
    ForestState newState = this.state;

		switch(this.state)
		{
		case GROW_LOW:
      // A forest patch in low growth can transition to medium growth
			if(rand.nextDouble() <  ForestPatch.growLow_growMed) {
				newState = ForestState.GROW_MED;
			}
			else {
				newState = this.state;
			}
			break;

		case GROW_MED:
      // A forest patch in medium growth can transition to high growth
			if(rand.nextDouble() < ForestPatch.growMed_growHigh) {
				newState = ForestState.GROW_HIGH;
			}
			else {
				newState = this.state;
			}
			break;

		case GROW_HIGH:
      // A forest patch in high growth can catch fire

      // Look at each neighbor.
      // If the neighbor is burning, there is a probability this patch ignites.
      newState = this.state;
      for(int i = 0; i < neighbors.length; i++)
      {
        if( ( (neighbors[i].state == ForestState.BURN_HOT) ||
            (neighbors[i].state == ForestState.BURN_MED) ||
            (neighbors[i].state == ForestState.BURN_MILD) ) &&
            rand.nextDouble() < ForestPatch.growHigh_burnHot_neighbor)
        {
          newState = ForestState.BURN_HOT;
        }
      }

			// The patch could spontaneously ignight
			if(rand.nextDouble() < ForestPatch.growHigh_burnHot_spon) {
				newState = ForestState.BURN_HOT;
				break;
			}
			break;

		case BURN_HOT:
      // A forest patch buring hot could cool to burning medium
			if(rand.nextDouble() < ForestPatch.burnHot_burnMed) {
				newState  = ForestState.BURN_MED;
			}
			else {
				newState = this.state;
			}
			break;

		case BURN_MED:
      // A forest patch buring medium could cool to burning mild
			if(rand.nextDouble() < ForestPatch.burnMed_burnMild) {
				newState = ForestState.BURN_MILD;
			}
			else {
				newState = this.state;
			}
			break;

		case BURN_MILD:
      // A forest patch buring medium could stop buring and become ash
			if(rand.nextDouble() < ForestPatch.burnMild_ashes) {
				newState = ForestState.ASH;
			}
			else {
				newState = this.state;
			}
			break;

		case ASH:
      // A forest patch that is ash could start growing
			if(rand.nextDouble() < ForestPatch.ashes_growLow) {
				newState = ForestState.GROW_LOW;
			}
			else {
				newState = this.state;
			}
			break;

		}
		return newState;
	} // end of changeState method

	/**
	 * Returns the state of this ForestPatch
	 * @return The state of this ForestPatch
	 */
	public ForestState getState()
	{
		return this.state;
	}

	/**
	 * Sets the state of this ForestPatch
	 * @param state The state to set this ForestPatch to
	 */
	public void setState(ForestState state)
	{
		this.state = state;
	}
}
