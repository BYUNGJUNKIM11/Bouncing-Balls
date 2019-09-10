package balls;

import java.awt.*;

import provided.util.dispatcher.IDispatcher;
import provided.util.valueGenerator.IRandomizer;
import provided.util.valueGenerator.impl.Randomizer;

/**
 * Creates a ball that changes color and size as it moves
 * 
 * @author BJ Kim and William Su
 *
 */
public class ColorBreathingBall extends ABall {

	/**
	 * Constructor for ColorBreathingBall
	 * 
	 * @param radius The radius of the ball
	 * @param loc The center location of the ball
	 * @param vel The velocity of the ball
	 * @param color The color of the ball
	 * @param frame The frame to draw the ball on
	 */
	public ColorBreathingBall(int radius, Point loc, Point vel, Color color, Component frame) {
		super(radius, loc, vel, color, frame);
	}

	/**
	 * Changes the color and size of the ball randomly
	 * with each move.
	 */

	@Override
	public void changeState() {
		IRandomizer rand = Randomizer.Singleton;
		// Set the new color.
		super.setColor(rand.randomColor());

		// Set the new size.
		super.setRadius(rand.randomInt(10, 30));
	}
}
