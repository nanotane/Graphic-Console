package mainFiles;

import gameFunctions.id2013.TarotCardDealer;
import gameFunctions.id2013.TicTacToeGame;
import gameFunctions.id2013.Blindman.Main2;

import java.io.IOException;
import java.util.ArrayList;

import utilityFunctions.ArduinoCommunication;
import utilityFunctions.DiceCalc;
import calculatingFunctions.BasicCalc;
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
			//if what they typed in was a built in command like help or info
			if(theInput.contains("/info"))//This needs to be the last check. If we found a / then it wasnt a command
			{
				theClass.addToChatLog("-------------------------------");
				theClass.addToChatLog("Multi Function Console Program");
				theClass.addToChatLog("Version: " + theClass.version);
				theClass.addToChatLog("Designed and created by Ian Davila");
				theClass.addToChatLog("For information on the authors of programs");
				theClass.addToChatLog("please consult the appropriate class files");
				theClass.addToChatLog("--------------------------------");
			}
			else if(theInput.contains("/help"))//This needs to be the last check. If we found a / then it wasnt a command
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
	 * Runs the thread
	 */
	@Override
	public void run() {
		checkForCommand();

	}

}
