/**
 * Author: Tolaz Hewa 
 * Date: Tuesday, March 10, 2015 
 * Class Name: UserToken
 * Functionality: A GameToken extension that has the ability to be moved around and modified.
 */
import java.awt.Color;

public class UserToken extends GameToken{

	/**
	 * constructor thats sets new color, visibility and makes and instance of super class
	 * @param type type of pattern
	 * @param x x position of box
	 * @param y y position of box
	 * @param width width of box
	 * @param height height of box
	 */
	public UserToken(int type, int x, int y, int width, int height) {
		super(type, x, y, width, height);
		super.setColor(Color.CYAN);
		this.setVisibilityPolicy();
	}
	/**
	 * sets the location of box
	 * @param x new x position
	 * @param y new y position
	 */
	public void setBoxLoc(int x, int y)
	{
		bbox.setLocation(x,y);
	}
	/**
	 * makes the token permanently visible
	 */
	public void setVisibilityPolicy()
	{
		setVisible(true);
	}
}
