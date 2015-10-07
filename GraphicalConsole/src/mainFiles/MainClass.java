package mainFiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;




/**
 * @author Ian Davila
 * @version 0.6.0
 *
 */
public class MainClass extends JFrame
{
	/**
	 * 
	 */
	public String version = "0.6.0";
	private static final long serialVersionUID = 89L;
	protected final int MAX_X = 620;
	protected final int MAX_Y = 400;
	private final String newLine = "\n";
	protected JPanel lowerPanel;//used to contain the buttons for the lower section
	protected JTextField messageInput;
	protected JButton sendInputButton;
	protected JTextArea chatLog;
	protected JScrollPane scrollypolly;
	protected String lastUserInput;
	protected boolean newInput = false;
	//This is a special string that indicates that we do not have new input
	protected String noInputString = "#$%NO%$#";//This is a weird sequence of characters so that way we know a user didnt type it
	protected Color forgroundColor = new Color(255, 247, 74);
	//past colors: 73, 228, 241, a shade of cyna
	protected Font font = new Font("Verdana", Font.PLAIN, 12);//setting the font size and style
	protected Color backgroundColor = Color.black;
	
//	protected ResisterCalc rco;
//	protected DiceCalc dicecal;
	//####
	protected ProcessCommands theCommands = new ProcessCommands(this);//this is needed 
	/*
	 * These are the strings that determine the names of certain components 
	 * in the GUI
	 */
	protected String name_Button1 = "Enter";
	protected String name_JPane_Title = "Multi tool";
	protected Thread commandThread = new Thread(theCommands);

	/**
	 * This constructor will draw the console and run the initilizing functions.
	 * Then it will run the command thread 
	 */
	public MainClass()
	{
		this.setSize(MAX_X, MAX_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(name_JPane_Title);
		this.setVisible(true);
		this.setBackground(backgroundColor);
		//This is used when the user presses the enter key to grab input
		
		//setting up the fields that will be used to declare the other JComponents
		initComponents();
		initComponentFunctions();
		
		//creating the calculating objects
		//addFunctions();
		
		commandThread.start();
	}

	/**
	 * Init the different Jcomponents
	 */
	public void initComponents()
	{

		lowerPanel = new JPanel();
		lowerPanel.setBackground(backgroundColor);

		
		//		lowerPanelRight = new JPanel();
		sendInputButton = new JButton(name_Button1);
		
		Border empty = BorderFactory.createEmptyBorder();//This is used to create an empty border for the gui
		
		messageInput = new JTextField(30);
		messageInput.setBackground(Color.DARK_GRAY);
		messageInput.setForeground(forgroundColor);
		messageInput.setFont(font);
		messageInput.setBorder(empty);
		messageInput.setCaretColor(forgroundColor);
		//This is the key listener so the component can listen for the enter key to send the commands
		messageInput.addKeyListener(new KeyListener(){
			//unused
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			//unused
			@Override
			public void keyReleased(KeyEvent arg0) {}
			
			//This is the section for when the enter key is typed
			@Override
			public void keyTyped(KeyEvent k) {
				//System.out.println("TYPED");
				//Code was being weird so I used the get char method to detect the enter key
				if(k.getKeyChar() == '\n')
				{
					lastUserInput = messageInput.getText(); //This stores the last user input to be used in other functions
					//When another function wants to know what a user typed in it will look at the lastUserInput
					newInput = true; //this is telling the computer that we have received new input and it can/should be read by other programs
					String tempAddingCharacters = ">" + lastUserInput;
					addToChatLog(tempAddingCharacters);
					messageInput.setText("");
				}
			}
			
		});

		chatLog = new JTextArea(8, 20);
		
        chatLog.setFont(font);
		scrollypolly = new JScrollPane(chatLog);
		chatLog.setEditable(false);
		chatLog.setForeground(forgroundColor);
		chatLog.setBackground(backgroundColor);
		chatLog.setBorder(empty);
		scrollypolly.setBorder(empty);
		
		
		
		
		lowerPanel.setLayout(new GridLayout(2,0, 0, 5));//Contains the lowerPanelbuttons and the inputmessage
//		lowerPanel2.setLayout(new GridLayout(1,0, 10, 8));//(rows, Columns, horizontal gap, vertical gap)
//		upperPanel.setLayout(new GridLayout(2, 0, 0, 5));
//		upperPanel2.setLayout(new GridLayout(1, 0, 10, 8)); TODO

		//setting colors
		sendInputButton.setBackground(forgroundColor);
		//adding panels
//		this.add("North", upperPanel);
		lowerPanel.add(messageInput);
//		lowerPanel.add(lowerPanel2);
//		upperPanel.add(upperPanel2);
		//		upperPanel.add(chatLog);
		//adding components to the second panel
		lowerPanel.add(sendInputButton);
		//adding  components to the upper panel
		this.add("South",lowerPanel);
		this.add("Center", scrollypolly);
		validate();//Need this for things to show up
	}
	/**
	 * This is sets all of the functions for the different JComponents
	 */
	public void initComponentFunctions()
	{
		sendInputButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				lastUserInput = messageInput.getText(); //This stores the last user input to be used in other functions
				//When another function wants to know what a user typed in it will look at the lastUserInput
				newInput = true; //this is telling the computer that we have received new input and it can/should be read by other programs
				String tempAddingCharacters = ">" + lastUserInput;
				addToChatLog(tempAddingCharacters);

			}
		});

	}
	/**
	 * Checks to see if new input has been given
	 * @return a string once we have new input
	 */
	public String checkForInput()
	{
		if(newInput)//if there is new input
		{
			newInput = false;
			return lastUserInput;
		}
		else
			return "#$%NO%$#";//#$%NO%$# is used as a special string to indicate that we do not have new information
	}
	/**
	 * Adds somethings to the chat lof
	 * @param stuff Strings you want to add to the screen
	 */
	public void addToChatLog(String stuff)
	{
		chatLog.append(stuff + newLine);
		chatLog.setCaretPosition(chatLog.getDocument().getLength());
	}
	
	/**
	 * Adds something to the chat log without adding a new line
	 * @param stuff Strings you want to add to the screen
	 */
	public void addToChatLogsl(String stuff)
	{
		chatLog.append(stuff);
		chatLog.setCaretPosition(chatLog.getDocument().getLength());
	}
	/**
	 * This will pause the program until the user types in something new
	 * @return the input from the console once there is new input
	 */
	public String waitForInput()
	{
		String theInput = "%%%%ERROR%%%";//if this is somehow returned then there is a problem!!
		
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theInput = this.checkForInput();
			if(theInput.equalsIgnoreCase("#$%NO%$#"))
			{
				//if we havnt actually gotten new input then lets just get out
			}
			else
			{	
				//otherwise lets get out of the loop and return the new input
				break;
			}
		}

		return theInput;

	}
	/**
	 * This is used for grabbing integers from the console. If it cant
	 * turn the input into an integer it returns -1 as a default
	 * @return a number from the console once there is new integer input. If not it will return -1
	 */
	public int waitForInputInt()
	{
		String theInput = "%%%%ERROR%%%";//if this is somehow returned then there is a problem!!
		int theInputInt = -1;
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theInput = this.checkForInput();
			if(theInput.equalsIgnoreCase("#$%NO%$#"))
			{
				//if there is not any new inpur then lets just do nothing
			}
			else //if there is input lets do stuff
			{
				//Lets try and see if we can turn it into an integer, if not then we disregard the input
				try{
					theInputInt = Integer.parseInt(theInput);//try and convert it
				}
				catch(InputMismatchException e)
				{
					//if not then we will return a -1 since most people would probably not be looking for that number hopefuly
					
				}
				break;
			}
		}

		return theInputInt;

	}
	

	//Main method
	public static void main(String args[])
	{
		MainClass test = new MainClass();
	}
}
