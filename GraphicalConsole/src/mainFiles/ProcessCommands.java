package mainFiles;

import gameFunctions.id2013.TarotCardDealer;
import gameFunctions.id2013.TicTacToeGame;
import gameFunctions.id2013.Blindman.Main2;

import java.awt.Color;
import java.util.ArrayList;

import utilityFunctions.ArduinoCommunication;
import utilityFunctions.DiceCalc;
import calculatingFunctions.BasicCalc;
import calculatingFunctions.ResisterCalc;
import calculatingFunctions.SimpleConverter;
import calculatingFunctions.StringBinaryConverter;

public class ProcessCommands implements Runnable {

	private MainClass theClass;
	private String theInput = "";
	private ArrayList<Program> programList = new ArrayList<Program>();
	/**
	 * Creates command objects and puts them into an array list
	 * @param a the MainClass object that this is pointing to
	 */
	public ProcessCommands(MainClass a)
	{
		theClass = a;
		//if you want to add a new program, add it here
		programList.add(new ResisterCalc(theClass));
		programList.add(new DiceCalc(theClass));
		programList.add(new BasicCalc(theClass));
		programList.add(new SimpleConverter(theClass));
		programList.add(new StringBinaryConverter(theClass));
		programList.add(new TarotCardDealer(theClass));
		programList.add(new TicTacToeGame(theClass));
		programList.add(new Main2(theClass)); //this is the blind man game
		programList.add(new ArduinoCommunication(theClass));

	}


	/**
	 * Process the input from the user and check to see if they are a command
	 */
	public void checkForCommand()
	{
		//Lets check to see if a program is going to see if a program wants to run at start up
		for(int i = 0; i < programList.size(); i++)
		{
			if(programList.get(i).runAtStart)
			{
				programList.get(i).run();
				break;//we will run only one program at start
			}
		}

		//This runs when it is first started
		theClass.addToChatLog("Welcome to the program!");
		theClass.addToChatLog("Type /help if you want a list of commands,");
		theClass.addToChatLog("Type in information into the grey space and then click Enter");
		theClass.addToChatLog("or hit the enter key on your keyboard");
		//Lets continualy check if the user typed in a help command
		//if they did then we will pause the thread until that changes
		while(true)
		{
			theInput = theClass.waitForInput();//This will wait for input then store it in theInput when it arrives
			
			if(theInput.contains("/info"))//get the info for the program
			{
				theClass.addToChatLog("-------------------------------");
				theClass.addToChatLog("Multi Function Console Program");
				theClass.addToChatLog("Version: " + theClass.version);
				theClass.addToChatLog("Designed and created by Ian Davila");
				theClass.addToChatLog("For information on the authors of programs");
				theClass.addToChatLog("please consult the appropriate class files");
				theClass.addToChatLog("--------------------------------");
			}
			else if(theInput.contains("/help"))//run through the programs list and print out authors, commands, etc.
			{

				for(int i = 0; i < programList.size(); i++)
				{
					theClass.addToChatLogsl("Program name: " + programList.get(i).name);//display the name
					theClass.addToChatLog("Command: " + programList.get(i).command);//display the author
					theClass.addToChatLog("Author: " + programList.get(i).author);
					theClass.addToChatLog("Description: " + programList.get(i).description);//displays description
					theClass.addToChatLog(" ");//leave some space to make it look pretty
				}
			}
			else if(theInput.contains("/setcolor"))//setting the colors
			{
				theClass.addToChatLog("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				cpSettings();
				theClass.addToChatLog("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			else if(theInput.contains("/setfont"))//setting the font type
			{
				theClass.addToChatLog("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				fontSettings();
				theClass.addToChatLog("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			else //if it was not a built in function then lets go through the programs list 
			{
				for(int i = 0; i < programList.size(); i++)
				{
					if(theInput.equals(programList.get(i).command))//if the program is equal to one of the programs...
					{
						theClass.addToChatLog("-------------------------------");
						programList.get(i).run();//lets run the program
						theClass.addToChatLog("-------------------------------");
					}
				}
			}
		}
	}
	/**
	 * Precise color settings method
	 */
	private void cpSettings()
	{
		String input;

		Color newColorF;
		Color newColorB;
		Color oldColorF = theClass.forgroundColor;
		Color oldColorB = theClass.backgroundColor;
		int r = 0;
		int g = 0;
		int b = 0;
		
		while(true)
		{
			//setting the forground color
			theClass.addToChatLog("~Setting foreground Color~");
			r = rgbCheck(r, "red");
			g = rgbCheck(g, "green");
			b = rgbCheck(b, "blue");
			newColorF = new Color(r,g,b);
			//setting the background color
			theClass.addToChatLog("~Setting background Color~");
			r = rgbCheck(r, "red");
			g = rgbCheck(g, "green");
			b = rgbCheck(b, "blue");
			newColorB = new Color(r,g,b);

			//change to new colors
			theClass.addToChatLog("~Setings new Colors~");
			theClass.setFontColor(newColorF);
			theClass.setBackgroundColor(newColorB);

			theClass.addToChatLog("Do you want to keep these colors? (Y/N");
			input = theClass.waitForInput();
			if(input.equalsIgnoreCase("y"))
			{
				theClass.addToChatLog("~Colors Changed~");
				break;
			}
			else
			{
				//if they want the old colors, change it back
				theClass.addToChatLog("Colors reverted");
				theClass.setBackgroundColor(oldColorB);
				theClass.setFontColor(oldColorF);
				break;
			}
		}
	}
	/**
	 * checks to see if the color values given are within range
	 * @param col color value
	 * @param name color name
	 * @return a value between 0 and 255
	 */
	private int rgbCheck(int col, String name)
	{
		while(true)
		{
			theClass.addToChatLog("Type in the "+name + " value");
			col = theClass.waitForInputInt();
			if(col == -1 || col > 255)
			{
				theClass.addToChatLog("Value was out of range or not recognized");
			}
			else
			{
				break;
			}
		}
		return col;
	}
	
	/**
	 * Allow user to see available fonts and choose from list
	 */
	private void fontSettings()
	{
		//first lets grab all available fonts if they want to view them

		String input;
		String oldFont = theClass.font.getFontName();//this will store the old font if we want to revert
		/*
		 * The idea is that the user will be able to type in a font immedietly if they already know what they want.
		 * if not then they can list off all fonts available. 
		 */
		theClass.addToChatLog("Type in a font that you would like");
		theClass.addToChatLog("type 'showfonts' to display all fonts");
		theClass.addToChatLog("type 'save' to save current settings and exit");
		theClass.addToChatLog("type 'revert' to revert to the original font and exit");
		theClass.addToChatLog("You can keep switching fonts until you find one you like");
		theClass.addToChatLog("Font names are case sensitive");
		while(true)
		{
			input = theClass.waitForInput();
			
			if(input.equals("showfonts"))//print out all the fonts
			{
				theClass.addToChatLog("~Fetching Fonts...~");
				theClass.printFonts();
			}
			else if(input.equals("save"))//save the current font
			{
				theClass.addToChatLog("~Font change Saved~");
				break;
			}
			else if(input.equals("revert"))//revert to the original font
			{
				theClass.addToChatLog("~Font Reverted~");
				theClass.setFontType(oldFont);
			}
			else//if its not a command lets see if it is a font type
			{
				if(theClass.setFontType(input))
				{
					theClass.addToChatLog("~Font changed~");
				}
				else
				{
					theClass.addToChatLog("~Unrecognized font~");
				}
				
			}
			
		}
		
	}

	/**
	 * Runs the thread
	 */
	@Override
	public void run() {
		checkForCommand();

	}

}
