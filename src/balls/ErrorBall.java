/**
 * 
 */
package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

/**
 * Creates an ErrorBall that does is not drawn on the frame
 * 
 * @author BJ Kim and William Su
 */
public class ErrorBall extends ABall {

	/**
	 * Constructor for ErrorBall
	 * 
	 * @param radius The radius of the ball
	 * @param loc The center location of the ball
	 * @param vel The velocity of the ball
	 * @param color The color of the ball
	 * @param frame The frame to draw the ball on
	 */
	public ErrorBall(int radius, Point loc, Point vel, Color color, Component frame) {
		super(0, loc, vel, color, frame);
	}
}
