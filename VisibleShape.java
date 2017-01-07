//imports libraries to be used
import java.awt.Graphics2D;
/**
 * Author: Tolaz Hewa
 * Date: Tuesday, March 10, 2015
 * Interface Name: VisibleShape
 * Functionality: a backbone/structure for all the tokens to follow
 */
public interface VisibleShape 
{
	void draw(Graphics2D g2); //to draw the tokens
	void setVisibilityPolicy(); //to declare visibility
	boolean overlaps(VisibleShape other); //to check if there is overlap
}
