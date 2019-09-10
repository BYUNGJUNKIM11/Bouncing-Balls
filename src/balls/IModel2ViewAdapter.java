package balls;

/**
 * Model-to-view adapter to communicate with the view without 
 * knowing about it
 * 
 * @author BJ Kim and William Su
 */
public interface IModel2ViewAdapter {

	/**
	 * Updates the balls, to be implemented
	 */
	public void update();

	/**
	 * Defines a model-to-view adapter, to be implemented
	 */
	public static final IModel2ViewAdapter NULL_OBJECT = new IModel2ViewAdapter() {

		public void update() {
		}
	};
}
