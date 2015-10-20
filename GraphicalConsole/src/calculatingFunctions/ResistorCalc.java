package calculatingFunctions;

import mainFiles.MainClass;
import mainFiles.Program;

public class ResistorCalc extends Program
{
	//Constructor

	public ResistorCalc(MainClass a)
	{
		super(a);
		this.author = "Ian Davila";
		this.name = "Resistor Calculator";
		this.description = "Calculates the resistance of a resistor based on the colors given";
		this.command = "/resistor";
	}
	//Begin calculating the resister values
	public void calcResistorValue()
	{
		double digit1;
		double digit2;
		double multiplerVal;
		double toleranceVal;
		double totalOhms;
		String theInput = theClass.checkForInput();
		theClass.addToChatLog("New Resistor Calculation");
		while(true)
		{


			theClass.addToChatLog("Type in the color of the first band");
			//This loop keeps asking for input until a correct value is put in
			while(true)
			{//wait for new input
				while(true)
				{
					theInput = theClass.checkForInput();
					if(theInput.equalsIgnoreCase("#$%NO%$#"))
					{

					}
					else
					{
						break;
					}
				}

				digit1 = colorValue(theInput, false);
				if(digit1 ==  -1)
				{
					theClass.addToChatLog("Please input a correct color");
				}
				else
				{
					break;
				}
			}


			theClass.addToChatLog("Type in the color of the second band");
			while(true)
			{
				//wait for new input
				while(true)
				{
					theInput = theClass.checkForInput();
					if(theInput.equalsIgnoreCase("#$%NO%$#"))
					{

					}
					else
					{
						break;
					}
				}
				digit2 = colorValue(theInput, false);
				if(digit2 ==  -1)
				{
					theClass.addToChatLog("Please input a correct color");
				}
				else
				{
					break;
				}
			}


			theClass.addToChatLog("Type in the color of the third band");
			while(true)
			{
				//wait for new input
				while(true)
				{
					theInput = theClass.checkForInput();
					if(theInput.equalsIgnoreCase("#$%NO%$#"))
					{

					}
					else
					{
						break;
					}
				}
				multiplerVal = colorValue(theInput, true);
				if(multiplerVal ==  -1)
				{
					theClass.addToChatLog("Please input a correct color");
				}
				else
				{
					break;
				}
			}

			theClass.addToChatLog("Type in the color of the fourth band");
			while(true)
			{
				//wait for new input
				while(true)
				{
					theInput = theClass.checkForInput();
					if(theInput.equalsIgnoreCase("#$%NO%$#"))
					{

					}
					else
					{
						break;
					}
				}
				toleranceVal = colorValue(theInput, false);
				if(toleranceVal ==  -1)
				{
					theClass.addToChatLog("Please input a correct color");
				}
				else
				{
					break;
				}
			}
			//Calculating the ohms value
			totalOhms = ((digit1 * 10) + digit2)*multiplerVal;
			String finalAnswer = "" + totalOhms + " +/- " + toleranceVal + "%";
			theClass.addToChatLog("Resistance is:");
			theClass.addToChatLog(finalAnswer);
			theClass.addToChatLog("Do you want to type another calculation?");
			theInput = theClass.waitForInput();
			if(!theInput.equalsIgnoreCase("yes"))
			{
				break;
			}
		}
	}
	//Determine what value the color means
	public double colorValue(String bandColor, boolean isMulti)
	{
		if(bandColor.equalsIgnoreCase("black"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 1;
			}
			else //if we are actually calculating the digits
			{
				return 0;
			}
		}
		else if(bandColor.equalsIgnoreCase("brown"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 10;
			}
			else //if we are actually calculating the digits
			{
				return 1;
			}
		}
		else if(bandColor.equalsIgnoreCase("red"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 100;
			}
			else //if we are actually calculating the digits
			{
				return 2;
			}
		}
		else if(bandColor.equalsIgnoreCase("orange"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 1000;
			}
			else //if we are actually calculating the digits
			{
				return 3;
			}
		}
		else if(bandColor.equalsIgnoreCase("yellow"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 10000;
			}
			else //if we are actually calculating the digits
			{
				return 4;
			}
		}
		else if(bandColor.equalsIgnoreCase("green"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 100000;
			}
			else //if we are actually calculating the digits
			{
				return 5;
			}
		}
		else if(bandColor.equalsIgnoreCase("blue"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 1000000;
			}
			else //if we are actually calculating the digits
			{
				return 6;
			}
		}
		else if(bandColor.equalsIgnoreCase("purple"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 10000000;
			}
			else //if we are actually calculating the digits
			{
				return 7;
			}
		}
		else if(bandColor.equalsIgnoreCase("grey"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return -1;
			}
			else //if we are actually calculating the digits
			{
				return 8;
			}
		}
		else if(bandColor.equalsIgnoreCase("white"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return -1;
			}
			else //if we are actually calculating the digits
			{
				return 9;
			}
		}
		else if(bandColor.equalsIgnoreCase("silver"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 0.01;
			}
			else //if we are actually calculating the tolerance value
			{
				return 10;
			}

		}
		else if(bandColor.equalsIgnoreCase("gold"))
		{
			//if we are calculating the multiplyer value
			if(isMulti)
			{
				return 0.1;
			}
			else //if we are actually calculating the tolerance value
			{
				return 5;
			}
		}
		else
		{
			return -1; // return this if what ever color they gave was not the right color
		}

	}

	public void processInput()
	{
		String theInput = theClass.checkForInput();
		//if we have not received new input
		while(theInput.equalsIgnoreCase("#$%NO%$#"))
		{

		}
	}

	@Override
	public void run()
	{
		calcResistorValue();
	}

}
