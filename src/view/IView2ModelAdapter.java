package view;

import java.awt.Graphics;
import java.awt.Component;

/**
 * View-to-model adapter to communicate with the model without 
 * knowing about it
 * 
 * @author BJ Kim and William Su
 */
public interface IView2ModelAdapter {

	/**
	 * Updates the frame, to be implemented
	 * 
	 * @param g Graphics of the frame
	 */
	public void update(Graphics g);

	/**
	 * Adds ball to the dispatcher, to be implemented
	 * 
	 * @param className String of desired ball type
	 * @param frame Frame of the GUI
	 */
	public void addBall(String className, Component frame);

	/**
	 * Clears all balls from the dispatcher, to be implemented
	 */
	public void clearAll();

	/**
	 * Defines a view-to-model adapter, to be implemented
	 */
	public static final IView2ModelAdapter NULL_OBJECT = new IView2ModelAdapter() {

		public void update(Graphics g) {
		}

		public void addBall(String className, Component frame) {
		}

		public void clearAll() {
		}
	};
}
