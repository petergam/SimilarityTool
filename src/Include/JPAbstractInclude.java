package Include;

import java.util.HashMap;

/**
 * The Class JPAbstractInclude.
 */
public abstract class JPAbstractInclude implements JPInclude {

	/** The number of layers. */
	private int layers = 0;
	
	private HashMap<String, Double> scores = new HashMap<String, Double>();
	
	/**
	 * Gets the layers.
	 *
	 * @return the layers
	 */
	public int getLayers() {
		return layers;
	}

	/**
	 * Sets the layers.
	 *
	 * @param layers the new layers
	 */
	public void setLayers(int layers) {
		this.layers = layers;
	}

	public HashMap<String, Double> getScores() {
		return scores;
	}

	public void setScores(HashMap<String, Double> scores) {
		this.scores = scores;
	}


	
}
