package mainFiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
 * @version 0.7.0
 *
 */
public class ConsoleGraphics extends JFrame
{
	public String version = "0.7.0";
	private static final long serialVersionUID = 89L;
	protected final int MAX_X = 620;
	protected final int MAX_Y = 700;
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
	protected Color foregroundColor = new Color(21, 180, 255);
	//91 255 45
	//past colors: 73, 228, 241, a shade of cyna
	protected Font font = new Font("Verdana", Font.PLAIN, 12);//setting the font size and style
	protected Color backgroundColor = Color.black;
	
	protected ProcessCommands theCommands = new ProcessCommands(this);//this is needed 
	private ReadWrite fileManager = new ReadWrite(this);
	private String settingsFile = "settings.gc";
	/*
	 * These are the strings that determine the names of certain components 
	 * in the GUI
	 */
	protected String name_Button1 = "Enter";
	protected String name_JPane_Title = "Graphical Console";
	protected Thread commandThread = new Thread(theCommands);

	/**
	 * This constructor will draw the console and run the initilizing functions.
	 * Then it will run the command thread 
	 */
	public ConsoleGraphics()
	{
		this.getSettings();
		this.setSize(MAX_X, MAX_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle(name_JPane_Title);
		this.setVisible(true);
		this.setBackground(backgroundColor);
		//This is used when the user presses the enter key to grab input
		
		//setting up the fields that will be used to declare the other JComponents
		initComponents();
		initComponentFunctions();
		commandThread.start();
	}

	/**
	 * Init the different Jcomponents
	 */
	public void initComponents()
	{

		lowerPanel = new JPanel();
		lowerPanel.setBackground(backgroundColor);
		sendInputButton = new JButton(name_Button1);
		
		Border empty = BorderFactory.createEmptyBorder();//This is used to create an empty border for the gui
		
		messageInput = new JTextField(30);
		messageInput.setBackground(Color.DARK_GRAY);
		messageInput.setForeground(foregroundColor);
		messageInput.setFont(font);
		messageInput.setBorder(empty);
		messageInput.setCaretColor(foregroundColor);
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
		chatLog.setForeground(foregroundColor);
		chatLog.setBackground(backgroundColor);
		chatLog.setBorder(empty);
		scrollypolly.setBorder(empty);
		
		
		
		
		lowerPanel.setLayout(new GridLayout(2,0, 0, 5));//Contains the lowerPanelbuttons and the inputmessage

		//setting colors
		sendInputButton.setBackground(foregroundColor);
		//adding panels
		lowerPanel.add(messageInput);
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
	 * Adds somethings to the chat log
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
			//This seems to solve problem of the thread not letting go of the processor
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
		int theInputInt;
		while(true)
		{
			//This seems to solve problem of the thread not letting go of the processor
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theInput = this.checkForInput();
			if(!theInput.equalsIgnoreCase("#$%NO%$#"))
			{
				//Lets try and see if we can turn it into an integer, if not then we disregard the input
				try{
					theInputInt = Integer.parseInt(theInput);//try and convert it
				}
				catch(InputMismatchException e)
				{
					//if not then we will return a -1 since most people would probably not be looking for that number hopefuly
					theInputInt = -1;
				}
				break;
			}
		}
		return theInputInt;

	}
	
	/**
	 * Sets the foreground color of the panel
	 * @param newColor 
	 */
	public void setFontColor(Color newColor)
	{
		chatLog.setForeground(newColor);
		chatLog.setCaretColor(newColor);
		sendInputButton.setBackground(newColor);
		messageInput.setForeground(newColor);
		
	}
	/**
	 * Sets the background color of the new panel
	 * @param newColor
	 */
	public void setBackgroundColor(Color newColor)
	{
		chatLog.setBackground(newColor);
		lowerPanel.setBackground(newColor);
		Color dialC = newColor.darker();
		messageInput.setBackground(dialC);
	}
	/**
	 * Sets the chat log font to the specified type. If what was given
	 * is not a valid font it returns false.
	 * @param newFont the new font name
	 * @return true or false
	 */
	public boolean setFontType(String newFont)
	{
		String[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i = 0; i < allFonts.length; i++)
		{
			if(allFonts[i].equalsIgnoreCase(newFont))//if what they typed equals a font type
			{
				//set that font and return true
				chatLog.setFont(new Font(allFonts[i], font.PLAIN, 12));
				messageInput.setFont(new Font(allFonts[i], font.PLAIN, 12));
				return true;
			}
		}
		//if we got here thats because no fonts matched so return false
		return false;
	}
	
	public boolean setFontSize(int newFontSize)
	{
		chatLog.setFont(new Font(font.getName(), font.PLAIN, newFontSize));
		//if we got here thats because no fonts matched so return false
		return false;
	}
	
	public int getFontSize()
	{
		return font.getSize();
	}
	/**
	 * Prints out all of the fonts available 
	 */
	public void printFonts()
	{
		String fonts[] = 
				GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i = 0; i < fonts.length; i++)
		{
			addToChatLog(fonts[i]);
		}
	}
	/**
	 * Adds a a special string to the front and to the back
	 * of the message that will be printed out. If bothSides
	 * is set to false will only print to the front of the message
	 * Sets carrot to the next line.
	 * @param stuff the content of the string
	 * @param special the special string to be added to the front and back
	 * @param bothSides should we put the special on both sides?
	 */
	public void addToChatLog(String stuff, String special, boolean bothSides)
	{
		if(bothSides)
		{
			chatLog.append(special + stuff  + special + newLine);
			chatLog.setCaretPosition(chatLog.getDocument().getLength());
		}
		else
		{
			chatLog.append(special + stuff + newLine);
			chatLog.setCaretPosition(chatLog.getDocument().getLength());
		}
		
	}
	
	public void clearChatLog()
	{
		//This wll clear the screen of text
		chatLog.setText("");
	}
	
	/**
	 * Load the settings from a settings file. If no setting file found
	 * a new one will be made with the default settings
	 */
	public void getSettings()
	{
		/*
		 * This is not the best way to read from a file but it was quick to make
		 */
		ArrayList<String> settingsContent = fileManager.read(settingsFile);
		if(settingsContent != null && settingsContent.size() > 0)
		{
			//index 1-3 have the rgb of the forgound color
			foregroundColor = new Color(Integer.parseInt(settingsContent.get(0)),
					Integer.parseInt(settingsContent.get(1)),
					Integer.parseInt(settingsContent.get(2)));
			//index 4-6 have the rgb of the background color
			backgroundColor = new Color(Integer.parseInt(settingsContent.get(3)),
					Integer.parseInt(settingsContent.get(4)),
					Integer.parseInt(settingsContent.get(5)));
		}
		
	}
	
	/**
	 * Save the settings given
	 */
	public void setSettings(ArrayList<String> contents)
	{
		fileManager.writeAll(settingsFile, contents);
	}
	
	public void createErrorLog(Exception pException)
	{
		StackTraceElement[] trace = pException.getStackTrace();
		ArrayList<String> toPrint = new ArrayList<>();
		for(int i = 0; i < trace.length ; i++)
		{
			toPrint.add(trace[i].toString());
		}
		fileManager.writeAll("ERROR_LOG.txt", toPrint);
	}
	
	public String getSettingsFileName()
	{
		return settingsFile;
	}
	
	/**
	 * Put something into the input field. Useful for allowing the user to 
	 * edit content
	 * @param input 
	 */
	public void setInputField(String input)
	{
		this.messageInput.setText(input);
	}
	
	//Main method
	public static void main(String args[])
	{
		ConsoleGraphics test = new ConsoleGraphics();
	}
}
