import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;
/**
 * Author: Tolaz Hewa 
 * Date: Tuesday, March 10, 2015 
 * Class Name: BlinkingToken
 * Functionality: extension for GameToken to create blinking token
 */

public class BlinkingToken extends GameToken{

	Timer timer; //timer for patterns
	Random random = new Random(); //random generator
	/**
	 * constructor to call super and set visible policy with random pattern
	 * @param x x location of box
	 * @param y y location fo box
	 * @param width width of box
	 * @param height height of box
	 */
	public BlinkingToken(int x, int y, int width, int height) {
		super(x, y, width, height);
		setVisiblePolicy();
	}
	/**
	 * randomizes the visibility of pattern with a timer
	 */
	public void setVisiblePolicy()
	{
		setVisible(true);
		timer = new Timer(3000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			if(getFound())
			{
				setVisible(true);
			}
			else if(!(random.nextInt(500) % 5 == 0))
			{
				setVisible(false);
			}
			else
			{
				setVisible(true);
			}
				
			}
			
		});
		timer.setInitialDelay(5000);
		timer.start();
	}
}
