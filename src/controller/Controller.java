package controller;

import java.awt.EventQueue;
import java.awt.Component;
import java.awt.Graphics;
import ballModel.Model;
import balls.IModel2ViewAdapter;
import view.IView2ModelAdapter;
import view.MyFrame;

/**
 * Controller that allows communication between the model and view 
 * without them knowing each other.
 * 
 * @author BJ Kim and William Su
 *
 */
public class Controller {

	/**
	 * Model that manages balls and their actions
	 */
	private Model model; // starts off null but will be fine when the constructor is finished.

	/**
	 * View that displays the balls and movement
	 */
	private MyFrame view; // starts off null but will be fine when the constructor is finished.

	/**
	 * Controller constructor builds the system
	 */
	public Controller() {

		/**
		 * Instantiates the model
		 */
		model = new Model(new IModel2ViewAdapter() {

			/** 
			 * Repaints the center panel of the GUI 
			 */
			public void update() {
				view.update();
			}
		});

		/**
		 * Instantiates the view
		 */
		view = new MyFrame(new IView2ModelAdapter() {
			/**
			 *  Calls model's dispatcher to update all balls
			 */
			public void update(Graphics g) {
				model.update(g);
			}

			/** 
			 * Adds the desired ball to our current dispatcher
			 * 
			 * @param className String of ball type desired
			 * @param frame Frame to draw the ball on
			 */
			public void addBall(String className, Component frame) {
				model.addBall(className, frame);
			}

			/**
			 * Clears all ball from the dispatcher
			 */
			public void clearAll() {
				model.clearAll();
			}
		});
	}

	/**
	 * Start the system
	 */
	public void start() {
		model.start();
		view.start();
	}

	/**
	 * Launch the application.
	 * 
	 * @param args Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					Controller controller = new Controller(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}