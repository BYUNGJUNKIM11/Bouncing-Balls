package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.lang.Math;
import provided.util.valueGenerator.IRandomizer;
import provided.util.valueGenerator.impl.Randomizer;

/**
 * Creates "trippy" ball that travels in a circle, changes size, AND changes color (cool!)
 * 
 * @author BJ Kim and William Su
 */
public class TrippyBall extends ABall {

	/**
	 * Theta defining ball's distance traveled along the circle path
	 */
	double theta;

	/**
	 * Constructor for TrippyBall
	 * 
	 * @param radius The radius of the ball
	 * @param loc The center location of the ball
	 * @param vel The velocity of the ball
	 * @param color The color of the ball
	 * @param frame The frame to draw the ball on
	 */
	public TrippyBall(int radius, Point loc, Point vel, Color color, Component frame) {
		super(radius, loc, vel, color, frame);

		IRandomizer rand = Randomizer.Singleton;
		theta = rand.randomDouble(0.1, 0.4);
	}

	/**
	 * Moves the ball in a circle, changes its size, and changes its color.
	 */
	@Override
	public void changeState() {
		// Change the velocity to take circle path
		int newX = (int) Math.round((vel.x * Math.cos(this.theta)) - (vel.y * Math.sin(this.theta)));
		int newY = (int) Math.round((vel.y * Math.cos(this.theta)) + (vel.x * Math.sin(this.theta)));
		super.setVel(new Point(newX, newY));
		
		IRandomizer rand = Randomizer.Singleton;

		// Change the color.
		super.setColor(rand.randomColor());

		// Change the size.
		super.setRadius(rand.randomInt(10, 70));
	}
	
}
