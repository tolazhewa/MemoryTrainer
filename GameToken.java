//Imports the following libraries to make use of
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Author: Tolaz Hewa 
 * Date: Tuesday, March 10, 2015 
 * Class Name: GameToken
 * Functionality: represents the tokens(boxes with patterns) that appear on
 * screen
 */
public class GameToken implements VisibleShape {
	private boolean visible; // Declares the visibility of the object
	private boolean found;
	public Rectangle bbox; // Box used in the token
	private Pattern pattern; // pattern inside the box
	private Color color; // color of the token
	private int attempts = 0;

	/**
	 * constructor that declares a box with a random pattern
	 * 
	 * @param x
	 *            x coordinate for the box (x value at the very left)
	 * @param y
	 *            y coordinate for the box (y value at the very top)
	 * @param width
	 *            width of the box
	 * @param height
	 *            height of the box
	 */

	public GameToken(int x, int y, int width, int height) {
		bbox = new Rectangle(x, y, width, height); // constructs the box
		pattern = new Pattern(bbox); // Constructs random pattern for the box
		color = Color.LIGHT_GRAY;
		setVisibilityPolicy();
	}

	/**
	 * constructor that declares a box with a specific pattern
	 * 
	 * @param patternType
	 *            value of the pattern type
	 * @param x
	 *            x coordinate for the box (x value at the very left)
	 * @param y
	 *            y coordinate for the box (y value at the very top)
	 * @param width
	 *            width of the box
	 * @param height
	 *            height of the box
	 */
	public GameToken(int patternType, int x, int y, int width, int height) {
		bbox = new Rectangle(x, y, width, height); // construct the box
		pattern = new Pattern(patternType, bbox); // construct specified pattern for the box
		color = Color.black;
		setVisibilityPolicy();
	}
	/**
	 * return if the token have been found
	 * @return variable to check if it has been found
	 */
	public boolean getFound()
	{
		return this.found;
	}
	
	public void setFound(boolean f)
	{
		this.found = f;
	}
	/**
	 * sets the visibility of the token
	 * 
	 * @param vis
	 *            visibility boolean
	 */
	public void setVisible(boolean vis) {
		this.visible = vis;
	}

	/**
	 * method to get the current visibility
	 * 
	 * @return the visibility
	 */
	public boolean getVisible() {
		return this.visible;
	}
	
	/**
	 * sets the pattern type
	 * @param i integer to declare type
	 */
	public void setType(int i)
	{
		pattern.setType(i);
	}
	
	/**
	 * returns type
	 * @return pattern type
	 */
	public int getType()
	{
		return this.pattern.getType();
	}
	/**
	 * method used to set the color of the token
	 * 
	 * @param c
	 *            color wanted
	 */
	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor()
	{
		return color;
	}
	/**
	 * Checks to see if the two GameTokens are of the same type
	 * 
	 * @param other
	 *            : the other object to compare to
	 */
	public boolean equals(Object other) {
		GameToken otherToken = (GameToken) other; // casting the "other" object to GameToken type
		if (otherToken.pattern.getType() == this.pattern.getType()) { // checks to see if the patterns match
			return true;
		}
		return false;
	}
	/**
	 * sets increases number attemps on the token
	 */
	public void increaseAttempts()
	{
		this.attempts++;
	}
	/**
	 * gets attempts on the token
	 * @return
	 */
	public int getAttempts()
	{
		return this.attempts;
	}
	/**
	 * method to draw our token
	 */
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fill(bbox);
		g2.setColor(color); // sets color
		g2.draw(bbox); // draws box
		if(visible)
			{
				pattern.draw(g2); // calls to draw the pattern
			}
		}

	/**
	 * method to set up the visibility
	 */
	public void setVisibilityPolicy() {
		visible = true;
	}

	/**
	 * method to check if this token overlaps with a given one
	 * 
	 * @param other
	 *            the other token to check with
	 */
	public boolean overlaps(VisibleShape other) {
		GameToken otherToken = (GameToken) other; // casting the "other" object to GameToken type
		if (otherToken.bbox.intersects(this.bbox)) { // check to see if the two tokens overlap
			return true;
		}
		return false;
	}
}
