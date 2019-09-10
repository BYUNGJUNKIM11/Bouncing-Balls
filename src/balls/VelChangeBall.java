package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import provided.util.valueGenerator.IRandomizer;
import provided.util.valueGenerator.impl.Randomizer;

/**
 * Creates a ball that changes velocity when it hits a wall
 * (just changes the velocity component that would have changed when the 
 * ball bounces)
 * 
 * @author BJ Kim and William Su
 *
 */
public class VelChangeBall extends ABall {

	/**
	 * Constructor for VelChangeBall
	 * 
	 * @param radius The radius of the ball
	 * @param loc The center location of the ball
	 * @param vel The velocity of the ball
	 * @param color The color of the ball
	 * @param frame  The frame to draw the ball
	 */
	
	public VelChangeBall(int radius, Point loc, Point vel, Color color, Component frame) {
		super(radius, loc, vel, color, frame);
	}

	/**
	 * Changes the relevant velocity component randomly if the ball bounces
	 * 
	 * @param changeX Boolean stating whether X velocity was changed or not
	 * @param changeY Boolean stating whether X velocity was changed or not
	 */

	@Override
	public void bounce(boolean[] changes) {
		IRandomizer rand = Randomizer.Singleton;

		if (changes[0]) {
			if (vel.x < 0) {
				vel.x = rand.randomInt(10, 25);
			} else {
				vel.x = rand.randomInt(-25, -10);
			}
		}
		if (changes[1]) {
			if (vel.y < 0) {
				vel.y = rand.randomInt(10, 25);
			} else {
				vel.y = rand.randomInt(-25, -10);
			}
		}
	}
}
