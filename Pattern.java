//Imports the following libraries to make use of
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Author: Tolaz Hewa 
 * Date: Tuesday, March 10, 2015 
 * Class Name: Pattern
 * Functionality: To create the patterns for the GameToken Class
 */
public class Pattern {

	public static final int CROSS = 0; // Declares an constant integer to represent the cross pattern with a value of 0
	public static final int CIRCLEPLUS = 1; // Declares an constant integer to represent the CirclePlus pattern with a value of 1
	public static final int SQUARE_X = 2; // Declares an constant integer to represent the Square-X pattern with a value of 2
	int type; // Declares the type of pattern for this instance
	Rectangle bbox; // The rectangle that will be drawn inside of

	/**
	 * Constructor that will produce a random pattern for the rectangle
	 * 
	 * @param bbox
	 *            the rectangle to draw the pattern inside
	 */
	public Pattern(Rectangle box) {
		Random newRandom = new Random(); // Instance of a random number
											// generator
		this.bbox = box; // passes on the rectangle obtained to bbox variable
		this.type = newRandom.nextInt(3); // declares a random pattern type
	}

	/**
	 * Constructor that will produce a specified pattern for the rectangle
	 * 
	 * @param type
	 *            declares the type of pattern
	 * @param bbox
	 *            the rectangle to draw the pattern inside
	 */
	public Pattern(int type, Rectangle box) {
		this.bbox = box; // sets the rectangular box
		this.type = type; // sets the type of pattern
	}

	/**
	 * method to get the box used
	 * 
	 * @return the box used
	 */
	public Rectangle getBox() {
		return this.bbox;
	}

	/**
	 * to set the box used for the pattern
	 * 
	 * @param box
	 *            box used for the pattern
	 */
	public void setBox(Rectangle box) {
		this.bbox = box;
	}

	/**
	 * method to get the pattern type
	 * 
	 * @return the pattern type
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * to set the type of pattern
	 * 
	 * @param type
	 *            type of pattern
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Checks to see if the two Patterns are of the same type
	 * 
	 * @param other
	 *            the other object to compare to
	 */
	public boolean equals(Object other) {
		Pattern otherPattern = (Pattern) other; // parses the "other" object to a Pattern type
		if (otherPattern.type == this.type) // checks to see if the two pattern types match and returns true if so, else returns false
		{
			return true;
		}
		
		return false;
	}

	/**
	 * A method to draw the patterns into the box
	 * 
	 * @param g2
	 *            graphics interface to work with
	 */
	public void draw(Graphics2D g2) {
		if (this.type == CROSS) // Declares if the type is Cross, if so draws a Cross into the box
		{
			g2.drawLine((int) bbox.getCenterX(), (int) bbox.getY(),
					(int) bbox.getCenterX(), (int) bbox.getMaxY());
			g2.drawLine((int) bbox.getX(), (int) bbox.getCenterY(),
					(int) bbox.getMaxX(), (int) bbox.getCenterY());
		}
		if (this.type == CIRCLEPLUS) // Declares if the type is CirclePlus, if so draws a CirclePlus into the box
		{
			g2.drawLine((int) bbox.getCenterX(), (int) bbox.getY(),
					(int) bbox.getCenterX(), (int) bbox.getMaxY());
			g2.drawLine((int) bbox.getX(), (int) bbox.getCenterY(),
					(int) bbox.getMaxX(), (int) bbox.getCenterY());
			g2.drawOval((int) bbox.getX(), (int) bbox.getY(),
					(int) bbox.getWidth(), (int) bbox.getHeight());
		}
		if (this.type == SQUARE_X) // Declares if the type is Square_X, if so draws a Square_X into the box
		{
			g2.drawLine((int) bbox.getX(), (int) bbox.getY(),
					(int) bbox.getMaxX(), (int) bbox.getMaxY());
			g2.drawLine((int) bbox.getX(), (int) bbox.getMaxY(),
					(int) bbox.getMaxX(), (int) bbox.getY());
		}

	}
}