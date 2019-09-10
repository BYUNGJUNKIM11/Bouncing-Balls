package balls;

import java.awt.*;

/**
 * Creates a straight-traveling constant velocity ball
 * 
 * @author BJ Kim and William Su
 */
public class StraightBall extends ABall {

	/**
	 * Constructor for StraightBall
	 * 
	 * @param radius The radius of the ball
	 * @param loc The location of the ball
	 * @param vel The velocity of the ball
	 * @param color The color of the ball
	 * @param frame The frame of the ball
	 */
	public StraightBall(int radius, Point loc, Point vel, Color color, Component frame) {
		super(radius, loc, vel, color, frame);
	}
}
