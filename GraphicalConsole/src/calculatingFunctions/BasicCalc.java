package calculatingFunctions;

import mainFiles.MainClass;
import mainFiles.Plugin;

public class BasicCalc extends Plugin
{
	private String[] operators = {"+", "-", "*", "/", "^" };
	public BasicCalc(MainClass a)
	{
		super(a);
		this.name = "Basic Calculator";
		this.command = "/bcalc";
		this.author = "Ian Davila";
		this.description = "A Calculator that can perform adding subtractacting multiplying dividing and exponents";
	}
	//This will preprocess the input and check if there is a need to exit the program
	private boolean preProcessInp(String input)
	{
		return input.equalsIgnoreCase("exit");
	}
	
	
	public void BasicCalcStart()
	{
		//This is the greeting
		double firstNum = 0;
		double secondNum = 0;
		double answer = 0;
		int indexOp = -1;
		int opToPerform = -1;
		String theInput;
		String operation;
		theClass.addToChatLog("Basic Calculator V0.1");
		theClass.addToChatLog("Type calc-help for commands and their functions");
		
		while(true)
		{
			theClass.addToChatLog("Type in a calculation:");
			
			theInput = theClass.checkForInput();
			theInput = theClass.waitForInput();//this will wait for input from the GUI
			theInput.replace(" ", "");//first lets replace all spaces with empty space
			if(preProcessInp(theInput))
			{
				//checks to see if we are exiting the program
				theClass.addToChatLog("Exiting Calculator Program");
				return;
			}
			else if(theInput.equals("calc-help"))
			{
				theClass.addToChatLog("Type one number, an operator such as ");
				theClass.addToChatLog("+, -, *, / or ^ and then a second number");
				theClass.addToChatLog("type exit to exit the program");
			}
			//now lets check and see if they have an operator in their statement
			for(int i = 0; i < 5;i++)
			{
				
				if(theInput.contains(operators[i]))
				{
					//get the operator index
					indexOp = theInput.indexOf(operators[i]);
					//get the string up to the op index then turn to a number
//					try
//					{
						firstNum = Double.valueOf(theInput.substring(0,indexOp));
						//get the string from opindex+1 to the end of the string
						secondNum = Double.valueOf(theInput.substring(indexOp+1, theInput.length()));
						opToPerform = i;//we set what operation we are doing in the second one to because we have passed all checks
//					}
//					catch(NumberFormatException e)
//					{
//						theClass.addToChatLog("Illegal characters in first number");
//						
//					}

					i = 5;//now lets exit
				}
				
			}
			
			
			//can we perform math?
			if(opToPerform != -1 && indexOp != -1)
			{
				//now perform the math requested
				if(opToPerform == 0)//adding
				{
					answer = firstNum + secondNum;
				}
				else if(opToPerform == 1)//subtracting
				{
					answer = firstNum - secondNum;
				}
				else if(opToPerform == 2)//multiplying
				{
					answer = firstNum * secondNum;
				}
				else if(opToPerform == 3)//dividing
				{
					answer = firstNum / secondNum;
				}
				else if(opToPerform == 4)//exponent
				{
					answer = Math.pow(firstNum, secondNum);
				}
				String temp = Double.toString(answer);
				theClass.addToChatLog(temp);
			}
			else
			{
				theClass.addToChatLog("Calculation failed");
			}
			
			//now print out the answer if we can
			indexOp = -1;
		}
	}
	
	@Override
	public void run()
	{
		BasicCalcStart();
	}
}
