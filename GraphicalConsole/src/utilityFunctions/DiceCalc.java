package utilityFunctions;

import mainFiles.ConsoleGraphics;
import mainFiles.Plugin;

public class DiceCalc extends Plugin
{
	public DiceCalc(ConsoleGraphics a)
	{
		super(a);
		this.name = "Dice Program";
		this.command = "/dice";
		this.author = "Ian Davila";
		this.description = "A simple dice program where you can roll or several dice of varying sides";
	}
	public void rollDieStart()
	{
		gConsole.addToChatLog("Dice Rolling program started:");
		String theInput = gConsole.checkForInput();
		//This is the large while loop that runs as long as the program is working
		while(true)
		{
			//wait for new input
			gConsole.addToChatLog("Do you want to roll a single die or multiple?");
			gConsole.addToChatLog("enter one or multi");
			//This waits for new input from the user
			theInput = gConsole.waitForInput();
			//Check to see if that input was the one we wanted
			int tempProcess = -1;
			if(theInput.equalsIgnoreCase("one"))
			{
				///throw a die
				throwOneDie(theInput);
				break;
			}
			else if(theInput.equalsIgnoreCase("multi"))
			{
				throwMultipleDie(theInput);
				break;
			}
			else
			{
				gConsole.addToChatLog("Please enter a valid response");
			}
		}
	}
	//This will roll a single die based on the number of sides
	//This uses the math.random calculation instead of importing the random class
	public static int rollADie(int sides)
	{
		int result;
		result = 1 + (int) (Math.random() * sides);
		return result;
	}
	//This method will roll a number of dice and return an array with the numbers rolled for each and also the total of those numbers
	public static int[] rollMultipleDice(int numOfDie, int sides)
	{
		int rollNumbers[] = new int[numOfDie+1];//creates a new array based on the number of dice to roll
		int total = 0;
		int temp;
		for(int i = 0; i < numOfDie; i++)
		{
			//This stores the number in temp, stores that roll in rollNumbers,
			//then adds it to the total of the rolls
			temp = rollADie(sides);
			rollNumbers[i] = temp;
			total = total +temp;
		}
		//Add the total to the end of the array
		rollNumbers[numOfDie] = total;
		return rollNumbers;
	}
	/**
	 * This is used when u have asked for 1 die to be thrown
	 */
	public void throwOneDie(String theInput)
	{
		int tempProcess = -1;
		gConsole.addToChatLog("How many Sides does this die Have?");
		while(true)
		{
			theInput = gConsole.waitForInput();//This will wait for input then store it in theInput when it arrives
			//Tries to catch an error if the user type something that wasnt a number
			try
			{
				tempProcess = Integer.parseInt(theInput);
				break;
				
			}
			catch (NumberFormatException e)
			{
				gConsole.addToChatLog("Please type a number");
			}
			
		}
		
		//Roll that die!
		String tempAnswer ="The number you rolled was: " + rollADie(tempProcess);
		gConsole.addToChatLog(tempAnswer);
	}
	/**
	 * Simulates multiple die with a set number of sides being thrown.
	 */
	public void throwMultipleDie(String theInput)
	{
		//Get the number of sides
		int tempProcess = -1;
		int tempProcess2 = -1;
		gConsole.addToChatLog("How many sides will each die have?");
		while(true)
		{
			theInput = gConsole.waitForInput();//This will wait for input then store it in theInput when it arrives
			//Tries to catch an error if the user type something that wasnt a number
			try
			{
				tempProcess = Integer.parseInt(theInput);
				break;
				
			}
			catch (NumberFormatException e)
			{
				gConsole.addToChatLog("Please type a number");
			}
			
		}
		//Get the number of dice that we want to throw
		gConsole.addToChatLog("How many dice do you want to throw?");
		while(true)
		{
			theInput = gConsole.waitForInput();//This will wait for input then store it in theInput when it arrives
			//Tries to catch an error if the user type something that wasnt a number
			try
			{
				tempProcess2 = Integer.parseInt(theInput);
				break;
				
			}
			catch (NumberFormatException e)
			{
				gConsole.addToChatLog("Please type a number");
			}
			
		}
		
		int[] tempAnswers = rollMultipleDice(tempProcess2, tempProcess);
		for(int i = 0; i < tempAnswers.length-1; i++)
		{
			String tempString = "Die number " + (i +1) + ": " + tempAnswers[i];
			gConsole.addToChatLog(tempString);
		}
		String tempString2 = "Total of all die thrown: " + tempAnswers[tempAnswers.length-1];
		gConsole.addToChatLog(tempString2);
		
		
		
	}
	
	@Override
	public void run()
	{
		rollDieStart();
	}

}
