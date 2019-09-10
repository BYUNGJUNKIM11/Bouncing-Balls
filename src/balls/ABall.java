package balls;

import provided.util.dispatcher.IObserver;

import java.awt.*;

import provided.util.dispatcher.IDispatcher;

/**

 * Defines an abstract ABall with color, moving, bouncing, and painting capabilities
 * 
 * @author BJ Kim and William Su
 *  *
 */
public abstract class ABall implements IObserver<Graphics> {

	/**
	 * Radius of the ball
	 */
	protected int radius;

	/**
	
	* Constructor for class ABall
	* 
	* @param radius The radius of the ball
	* @param loc The center coordinate of the ball
	* @param vel The velocity vector of the ball
	* @param color The color of the ball
	* @param frame The frame to draw the ball on
	 */
	public ABall(int radius, Point loc, Point vel, Color color, Component frame) {
		this.radius = radius;
		this.loc = loc;
		this.color = color;
		this.vel = vel;
		this.frame = frame;
	}

	/**
	 * Frame the ball will be drawn on.
	 */
	protected Component frame;

	/**
	
	* Getter for the ball's frame
	* 
	* @return Frame frame
	 */
	public Component getFrame() {
		return frame;
	}

	/**
	
	* Setter for the radius
	* 
	* @param radius The radius of the ball
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	
	* Getter for the radius
	* 
	* @return int radius of ball
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Center coordinate (x, y) of the ball.
	 */
	protected Point loc;

	/**
	 * setter for center of ball
	 * 
	 * @param loc The center of the ball
	 */

	public void setLoc(Point loc) {
		this.loc = loc;
	}

	/**
	 * Getter for center location of ball
	 * 
	 * @return int loc
	 */
	public Point getLoc() {
		return loc;
	}

	/**
	 * Color of the ball.
	 */
	protected Color color;

	/**
	 * Setter for the ball's color
	 * 
	 * @param color The color of the ball
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Getter for the color
	 *  
	 * @return Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * The velocity of the ball
	 */
	protected Point vel;

	/**
	 * Getter for the velocity
	 * 
	 * @return Point vel
	 */
	public Point getVel() {
		return vel;
	}

	/**
	 * Setter for the velocity of the ball
	 * 
	 * @param vel The velocity of the ball
	 */
	public void setVel(Point vel) {
		this.vel = vel;
	}

	/**
	 * Paints the ball on the panel
	 * 
	 * @param g The graphics object of the ball
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(loc.x - radius, loc.y - radius, 2 * radius, 2 * radius);
	}

	/**
	 * Moves the ball on the panel
	 */
	public boolean[] move() {

		//anticipate the next location
		int nextX = loc.x + vel.x;
		int nextY = loc.y + vel.y;

		//track if x or y were changed
		boolean xChanged = false;
		boolean yChanged = false;

		//If the ball bounces on the left wall
		if (nextX - radius < 0) {
			loc.x = Math.abs(nextX - radius) + radius;
			xChanged = true;
		}

		// If the ball bounces on the right wall
		else if (nextX + radius > this.getFrame().getWidth()) {
			loc.x = getFrame().getWidth() - Math.abs(nextX + radius - getFrame().getWidth()) - radius;
			xChanged = true;
		}

		// If the ball bounces on the top wall
		if (nextY - radius < 0) {
			loc.y = Math.abs(nextY - radius) + radius;
			yChanged = true;
		}

		// If the ball bounces on the bottom wall
		else if (nextY + radius > this.getFrame().getHeight()) {
			loc.y = getFrame().getHeight() - Math.abs(nextY + radius - getFrame().getHeight()) - radius;
			yChanged = true;
		}

		// move x or y normally if it has not been changed already
		if (!xChanged) {
			loc.x += vel.x;
		}
		if (!yChanged) {
			loc.y += vel.y;
		}

		// check if the ball bounced
//		bounce(xChanged, yChanged);
		
		return new boolean[] {xChanged, yChanged};
	}

	/**
	 * Checks if the ball bounces, and changes x or y velocity if bounced
	 * 
	 * @param changes Booleans stating whether velocity components were changed or not
	 */
	public void bounce(boolean[] changes) {

		//reverse velocity component if it changes from bounce
		if (changes[0]) {
			vel.x *= -1;
		}
		if (changes[1]) {
			vel.y *= -1;
		}
	}
	
	
	
	public void changeState() {
	}
	
	/**
	 * Checks if the ball bounces, and changes x or y velocity if bounced
	 *
	 * @param g Boolean stating whether X velocity was changed or not
	 * @param disp Boolean stating whether X velocity was changed or not
	 */
	public void update(IDispatcher<Graphics> disp, Graphics g) {
		changeState();
		boolean[] velChanged = move();
		bounce(velChanged);
		paint(g);
	}
	
}
