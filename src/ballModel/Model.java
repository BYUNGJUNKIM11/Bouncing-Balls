package ballModel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;
import balls.ErrorBall;
import balls.IModel2ViewAdapter;
import provided.util.dispatcher.IDispatcher;
import provided.util.dispatcher.IObserver;
import provided.util.dispatcher.impl.ParallelDispatcher;
import provided.util.dispatcher.impl.SequentialDispatcher;
import provided.util.valueGenerator.IRandomizer;
import provided.util.valueGenerator.impl.Randomizer;
import provided.util.loader.IObjectLoader;
import provided.util.loader.impl.ObjectLoader;
import java.awt.Color;
import java.awt.Component;

/**
 * Creates a model to manage balls and communicate with 
 * the view
 * 
 * @author BJ Kim and William Su
 *
 */
public class Model {

	/**
	 * Model-to-View adapter communicates model's actions to view
	 * Insures that the adapter is always valid
	 */
	private IModel2ViewAdapter model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;

	/**
	 * List of all valid class names
	 */
	List<String> classNames = Arrays.asList("Straight", "VelChange", "Trippy", "ColorBreathing");

	/**
	 * Create Sequential Dispatcher to manage balls
	 */
//	private IDispatcher<Graphics> myDispatcher = new SequentialDispatcher<Graphics>();

	/**
	 * Create Parallel Dispatcher to manage balls
	 */
		private IDispatcher<Graphics> myDispatcher = new ParallelDispatcher<Graphics>();

	/**
	 * Updates all balls in the dispatcher at once
	 * 
	 * @param g The graphics of the GUI
	 */
	public void update(Graphics g) {
		myDispatcher.updateAll(g);
	}

	/**
	 * Clears all ball objects from the dispatcher
	 */
	public void clearAll() {
		myDispatcher.removeAllObservers();
	}

	/**
	 * Adds a ball of type className to the dispatcher to
	 * then be drawn on the frame
	 * 
	 * @param className The name of the ball class selected
	 * @param frame The frame to draw the new ball on
	 */
	public void addBall(String className, Component frame) {

		/**
		 * Creates an objectloader, where if there are problems with loading, 
		 * an ErrorBall is created.
		 */
		IObjectLoader<IObserver<Graphics>> loader = new ObjectLoader<IObserver<Graphics>>(
				args -> new ErrorBall(0, new Point(0, 0), new Point(0, 0), Color.BLACK, frame));

		IRandomizer rand = Randomizer.Singleton;

		// assigns the radius
		int radius = rand.randomInt(10, 80);

		// If Random is selected, choose one of the ball types randomly
		if (className.equals("Random")) {

			className = classNames.get(rand.randomInt(0, 3));

			// if className is invalid, load an ErrorBall
		} else if (!classNames.contains(className)) {
			className = "Error";
		}

		// load the ball based on the className selected from the GUI
		IObserver<Graphics> ball = loader.loadInstance("balls." + className + "Ball", radius,
				new Point(rand.randomInt(radius, frame.getWidth() - radius),
						rand.randomInt(radius, frame.getHeight() - radius)),
				rand.randomVel(new Rectangle(25, 25)), rand.randomColor(), frame);

		// Add the observer to the dispatcher to be drawn
		myDispatcher.addObserver(ball);
	}

	/**
	 * Constructor for Model
	 * 
	 * @param model2ViewAdapter The model-to-view adapter for MVC control
	 */
	public Model(IModel2ViewAdapter model2ViewAdapter) {
		model2ViewAdpt = model2ViewAdapter;
	}

	/**
	 * sets the timer interval
	 */
	private int timeSlice = 50; // update every 50 milliseconds

	/**
	 * Tells the model to update every 50 milliseconds
	 */
	private Timer timer = new Timer(timeSlice, (e) -> {
		model2ViewAdpt.update();
	});

	/**
	 * Starts the timer
	 */
	public void start() {
		timer.start();
	}
}
