//Imports the following libraries to make use of
import java.awt.Color;

import javax.swing.JFrame; 
import javax.swing.JPanel;

/**
 * Author: Tolaz Hewa
 * Date: Tuesday, March 10, 2015
 * Class Name: GameStarter
 * Functionality: the main class to start the game
 */
public class GameStarter
{
	/**
	 * A main function to allows console to know where to start running. It sets us the framework for our game and allows for functionality.
	 * @param args: Stores a great number string functionality for the main method and also is a standard when creating java functions
	 */
	public static void main(String[] args) 
	{
		//Declarations
		int amount = 12; //number of squares
		int width = 500; //Width of the frame
		int height = 500; // Height of the frame
		int frameX = 100; //x location of frame
		int frameY = 100; // y location of frame
		int boxSize = 45; //desired size of side length for box
		JFrame myFrame = new JFrame("Brain Train"); //Creates an instance JFrame 
		JPanel myPanel = new GamePanel(width, height, amount, boxSize); //Creates an instance of our customized GamePanel. This instance is where all the graphic work will be done
		
		//Actions
		myFrame.setBounds(frameX, frameY, width, height); //Sets the boundaries and locations of the frame
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Tells the application to exit as soon as the JFrame is closed
		myPanel.setBackground(Color.LIGHT_GRAY); //sets the background color
		myFrame.add(myPanel); //adds the panel to our frame to display
		myFrame.setVisible(true); //sets the visibility of the frame as true
	}
}
