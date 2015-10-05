package calculatingFunctions;

import mainFiles.MainClass;

public class ElectricsCalculator 
{
	private MainClass theClass;
	public ElectricsCalculator(MainClass a)
	{
		theClass = a;
	}
	/*
	 * Formulas:
	 * r = i/v
	 * p (power in watts) = i^2 r
	 * 
	 */
	public void runElectricsCalc()
	{
		/*
		 * Ask the user what type of calculation they are using
		 * one off is to just use a variation of the basic formulas
		 * when the user asks for a formula u get the two numbers that are needed
		 */
		String choice = "";
		theClass.addToChatLog("Select a calculation");
		theClass.addToChatLog("Type in the number for the corresponding choice");
		theClass.addToChatLog("1. Resistor Needed for volateg drop");
		theClass.addToChatLog("2. forumla list");
		choice = theClass.waitForInput();
		if(choice.equalsIgnoreCase("1"))
		{
			optionResitorCalc();
		}
		else if(choice.equalsIgnoreCase("2"))
		{
			formulaListMenu();
		}
	}
	
	public void optionResitorCalc()
	{
		String choice = "";
		double startVolt;
		double endVolt;
		double amps;
		theClass.addToChatLog("Type in the starting voltage");
		theClass.addToChatLog("Type in the voltage goal");
		theClass.addToChatLog("Type in the current in amps");
		if((amps = theClass.waitForInputInt()) != -1)//grab the input and see if its equal to -1
		{
			//throw an error if we messed up somewhere
		}
		theClass.addToChatLog("The resistance you need is:");
	}
	
	public void formulaListMenu()
	{
		
	}
	/**
	 * p = i^2 * r
	 * @param i
	 * @param r
	 * @return
	 */
	public double pi2r(double i, double r)
	{
		return (Math.pow(i, 2)* r);
	}
	/**
	 * r = i /v 
	 * @param i
	 * @param v
	 * @return
	 */
	public double resVoltDrop(double i, double v)
	{
		//r = i/v
		// i = (current) amps
		//v = (force) volts 
		return (i/v);
	}
}
