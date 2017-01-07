//Imports the following libraries to make use of
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Author: Tolaz Hewa 
 * Date: Tuesday, March 10, 2015 
 * Class Name: GamePanel
 * Functionality: JPanel extension to create the interface for the desired game
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 8120217772562859936L;
	private ArrayList<BlinkingToken> myTokens; // ArrayList of tokens
	private int boxSize; // length of the sides of the box
	private int panelWidth; // width of panel
	private int panelHeight; // height of the panel
	private UserToken userToken; //instance of user token
	private int userScore = 0; //integer variable to hold the user's score
	private JLabel score = new JLabel(); //label to display user's score
	private int completed = 0; 
	private double timeElapsed = 0;
	private JLabel time = new JLabel();
	private Timer myTimer;
	private JLabel countDown = new JLabel();

	/**
	 * Constructs an instance of the Panel getting the values required
	 * 
	 * @param width
	 *            width of the panel
	 * @param height
	 *            height of the panel
	 * @param amount
	 *            number of tokens wanted
	 */
	public GamePanel(int width, int height, int amount, int size) {
		super(); // calls and instance of JPanel (super class)
		this.boxSize = size;
		this.panelWidth = width;
		this.panelHeight = height;
		countDown.setText("Game Starting in 5 Seconds!");
		add(countDown);
		add(score);
		add(time);
		createTokens(amount);// call upon the createTokens method to create the number of desired tokens
		mouseActions();
		update();
		
		 //calls the mouseActions class to identify the mouse actions
	}

	/**
	 * returns the box side size
	 * 
	 * @return length of box sides
	 */
	public int getBoxSize() {
		return this.boxSize;
	}
	
	/**
	 * sets length of box
	 * 
	 * @param tl
	 *            length of box
	 */
	public void setBoxSize(int bs) {
		this.boxSize = bs;
	}
	/**
	 * repaints, keeps track of time, score, and decided when game is done
	 */
	public void update()
	{
		myTimer = new Timer(10, new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				remove(countDown);
				timeElapsed = timeElapsed + 0.01;
				if(completed == myTokens.size())
				{
					myTimer.stop();
					countDown.setText("Thank you for playing!");
					add(countDown);
				}
				time.setText("time: " + String.format("%.2f", timeElapsed));
				score.setText("score: " + userScore);
				repaint();
			}
		});
		myTimer.setInitialDelay(5000);
		myTimer.start();
	}
	
		
		
	/**
	 * Function to create tokens and check for overlap
	 * 
	 * @param amount
	 *            number of desired tokens
	 */
	public void createTokens(int amount) {
		Random myRandom = new Random(); // Random number generator
		boolean overlaps; // boolean to check if overlaps
		BlinkingToken newToken; // newToken to add
		myTokens = new ArrayList<BlinkingToken>(); // constructs the ArrayList
		while (myTokens.size() < amount) { // loops until the desired number of tokens are created
			overlaps = false;
			newToken = new BlinkingToken(myRandom.nextInt(panelWidth - boxSize - 30),myRandom.nextInt(panelHeight - boxSize - 30), boxSize, boxSize); // creates new token with random values
			for (BlinkingToken token : myTokens) { // checks to see if it overlaps
				if (newToken.overlaps(token)) {
					overlaps = true; // if it overlaps, sets overlaps as true and breaks out of the loop
					break;
				}
			}
			if (!overlaps) { // if it doesn't overlap then add token to the ArrayList
				myTokens.add(newToken);
			}
		}
		userToken = new UserToken(1, 0, 0, boxSize, boxSize);
		while(true)
		{
			overlaps = false;
			for(BlinkingToken token: myTokens)
			{
				if(token.overlaps(userToken))
				{
					userToken = new UserToken(1, myRandom.nextInt(panelWidth - boxSize - 30),myRandom.nextInt(panelHeight - boxSize - 30), boxSize, boxSize);
					overlaps = true;
					break;
				}
			}
			if(!overlaps){ break;}
		}
	}
	
	/**
	 * function to paint the tokens
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //instance of superclass' paintComponent method
		Graphics2D g2 = (Graphics2D) g; //parsing to Graphics2D
		for (GameToken token : myTokens) { //going through the ArrayList and draws tokens
			token.draw(g2);
		}
		userToken.draw(g2); //draws user token
	}
    
	/**
     * function to control game behavior using the mouse
     */
	public void mouseActions()
	{	
		class myMouseListener extends MouseAdapter //mouse listener class to track mouse movement, extends MouseAdapter
		{
			public void mousePressed(MouseEvent e) //overrides and modifies mouseClicked method
			{			
				userToken.setBoxLoc(e.getX() - boxSize/2, e.getY() - boxSize/2); //sets the location of the mouse
				if(SwingUtilities.isLeftMouseButton(e)) //checks if left button is clicked
				{
					for(int i = 0; i < myTokens.size(); i++) //goes through all the tokens
					{
						if(myTokens.get(i).bbox.contains(e.getX(),e.getY()) && myTokens.get(i).equals(userToken)) //checks for intersection and if the types match							{
						{	
							if(!myTokens.get(i).getFound()) //checks boolean array to see if the token is used
							{
								if(myTokens.get(i).getColor() == Color.RED) 
								{
									userScore ++;
								}
								else 
								{
									userScore+=2; //increases score by two
								}
								myTokens.get(i).setColor(Color.GREEN); //sets color to green
								myTokens.get(i).setFound(true);
								myTokens.get(i).setVisible(true);
								completed++;
								break;
							}
						}
						else if(myTokens.get(i).bbox.contains(e.getX(),e.getY())  && !myTokens.get(i).equals(userToken)) //checks for intersection and if they don't match
						{
							if(!myTokens.get(i).getFound()) //checks with boolean array to see if the token is used
							{
								myTokens.get(i).setColor(Color.RED);//sets color to red indicating that they got it wrong
								myTokens.get(i).increaseAttempts();
								if(userScore > 0 && myTokens.get(i).getAttempts() > 2) //limits the lower bound of score
								{
									userScore--; //decreases score by one
								}
							}
							break;
						}
					}
				}
				if(SwingUtilities.isRightMouseButton(e)) //checks if the button clicked is right click. If so, changes the pattern
				{
					if(userToken.getType() == 2)
					{
						userToken.setType(0);
					}
					else
					{
						userToken.setType(userToken.getType()+1);
					}
				}
			}
		}
		myMouseListener mouseListener = new myMouseListener(); //declares an instance of the mouse listener just created
		addMouseListener(mouseListener); //adds the mouse listener to panel
	}
}
