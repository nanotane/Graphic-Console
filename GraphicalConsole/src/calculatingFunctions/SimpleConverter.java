/*
 * The program will have multiple menus for the user to choose from
 * first the user can choose the either volume, measurement, or speed
 * once a user has chosen a section the user can then choose the starting 
 * conversion, like gallons or miles, then they choose the second conversion 
 * and then they type in the number. it then does the calculation and restarts
 * the choices from the second to the top
 */

package calculatingFunctions;
import mainFiles.ConsoleGraphics;
import mainFiles.Plugin;
/**
 * This program converts units, speed, length, and volume.
 * @author Harry Dodwell
 * July 2015
 */
public class SimpleConverter extends Plugin
{
	// array locations                   0         1       2        3       4       5       6
	private String[] length = {"inches", "feet", "yards", "miles", "cm", "meters", "km" };
	//array locations                 0      1      2       3
	private String[] speed = {"mph", "kmh", "m/s", "ft/s"};
	private double[][] lengthChart = new double[7][7];
	private   double[][] speedChart = new double [4][4];
	


	public SimpleConverter(ConsoleGraphics a)
	{		
		super(a);
		charts();
		this.name = "Simple Converter";
		this.author = "Harry Dodwell";
		this.command = "/converter";
		this.description = "Can convert one measurment type into another, such as feet to meters";
		this.version = "1.0";
	}

	private boolean preProcessInp(String input)
	{
		return input.equalsIgnoreCase("exit");
	}
	
	public   void simpleConvertStart()
	{
		//double inputNumber = 0;
		//double answer = 0;
		String measurementInput;
		
		gConsole.addToChatLog("Basic Converter");
		gConsole.addToChatLog("Enter your measurement type: volume, length, or speed");
		measurementInput = gConsole.checkForInput();
		measurementInput = gConsole.waitForInput();//this will wait for input from the GUI
		measurementInput.replace(" ", "");//first lets replace all spaces with empty space

		while(true)
		{
			if(preProcessInp(measurementInput))
			{
				gConsole.addToChatLog("Exiting the Converter");
				return;
			}
			else if(measurementInput.equalsIgnoreCase("volume"))
			{
				volume();
			}
			else if(measurementInput.equalsIgnoreCase("length"))
			{
				length();
			}
			else if(measurementInput.equalsIgnoreCase("speed"))
			{
				speed();
			}
			else
			{
				gConsole.addToChatLog("Enter \"volume\", \"length\", or \"speed\"");
				return;
			}
		}

	}

	private   void volume()
	{
		String unitInput;
		String inputValue;
		
		gConsole.addToChatLog("Would you like to convert from gallons or liters");
		unitInput = gConsole.checkForInput();
		unitInput = gConsole.waitForInput();//this will wait for input from the GUI
		unitInput.replace(" ", "");//first lets replace all spaces with empty space
		try{
			
		if(unitInput.equalsIgnoreCase("gallons"))
		{
			gConsole.addToChatLog("How many gallons would you like to convert?");
			inputValue = gConsole.checkForInput();
			inputValue = gConsole.waitForInput();//this will wait for input from the GUI
			inputValue.replace(" ", "");//first lets replace all spaces with empty space
			double value = Double.parseDouble(inputValue);
			
			double liters = value * 3.78541;
			
			gConsole.addToChatLog(value + " gallons = "+ liters + " liters" );
			
		}
		else if(unitInput.equalsIgnoreCase("liters"))
		{
			gConsole.addToChatLog("How many liters would you like to convert?");
			inputValue = gConsole.checkForInput();
			inputValue = gConsole.waitForInput();//this will wait for input from the GUI
			inputValue.replace(" ", "");//first lets replace all spaces with empty space
			double value = Double.parseDouble(inputValue);
			
			double gallons = value * (1/3.78541);
			
			gConsole.addToChatLog(value + " liters = "+ gallons + " gallons" );
		}
		else if(unitInput.equalsIgnoreCase("exit"))
		{
			simpleConvertStart();
		}
		}
		catch(NumberFormatException e)
		{
			gConsole.addToChatLog("Type in a number");
		}
	}

	private   void length()
	{
		String unitInputA;
		int indexA = 0;
		int indexB = 0;
		String unitInputB;
		String inputValue;
		
		gConsole.addToChatLog("Which unit would you like to convert from? inches, feet, yards, miles, cm, meters, or km" );
		
		unitInputA = gConsole.checkForInput();
		unitInputA = gConsole.waitForInput();//this will wait for input from the GUI
		unitInputA.replace(" ", "");//first lets replace all spaces with empty space
		if(unitInputA.equalsIgnoreCase("exit"))
				{
					simpleConvertStart();
				}
		for(int i = 0; i < 7; i ++)
		{
			if(unitInputA.contains(length[i]))
			{
				indexA = i;
			}	
		}
		
		gConsole.addToChatLog("Which different unit would you like to convert to? inches, feet, yards, miles, cm, meters, or km" );
		
		unitInputB = gConsole.checkForInput();
		unitInputB = gConsole.waitForInput();//this will wait for input from the GUI
		unitInputB.replace(" ", "");//first lets replace all spaces with empty space
		
		for(int i = 0; i < 7; i ++)
		{
			if(unitInputB.contains(length[i]))
			{
				indexB = i;
			}
			
		}
		 
		gConsole.addToChatLog("Enter the number of " + unitInputA + " that you want to convert ");
		inputValue = gConsole.checkForInput();
		inputValue = gConsole.waitForInput();//this will wait for input from the GUI
		inputValue.replace(" ", "");//first lets replace all spaces with empty space
		double output = Double.parseDouble(inputValue);
		double answer = output * lengthChart[indexA][indexB];
		
		gConsole.addToChatLog(inputValue + " " + unitInputA +  " = " + answer + " " + unitInputB);
		
	}

	private   void speed()
	{
		String unitInputA;
		int indexA = 0;
		int indexB = 0;
		String unitInputB;
		String inputValue;
		
		gConsole.addToChatLog("Which unit would you like to convert from? mph, kmh, m/s, ft/s" );
		
		unitInputA = gConsole.checkForInput();
		unitInputA = gConsole.waitForInput();//this will wait for input from the GUI
		unitInputA.replace(" ", "");//first lets replace all spaces with empty space
		
		for(int i = 0; i < 4; i ++)
		{
			if(unitInputA.contains(speed[i]))
			{
				indexA = i;
			}	
		}
		
		gConsole.addToChatLog("Which different unit would you like to convert to? mph, kmh, m/s, ft/s" );
		
		unitInputB = gConsole.checkForInput();
		unitInputB = gConsole.waitForInput();//this will wait for input from the GUI
		unitInputB.replace(" ", "");//first lets replace all spaces with empty space
		
		for(int i = 0; i < 4; i ++)
		{
			if(unitInputB.contains(speed[i]))
			{
				indexB = i;
			}
		}
		 
		gConsole.addToChatLog("Enter the number of " + unitInputA + " that you want to convert ");
		inputValue = gConsole.checkForInput();
		inputValue = gConsole.waitForInput();//this will wait for input from the GUI
		inputValue.replace(" ", "");//first lets replace all spaces with empty space
		double output = Double.parseDouble(inputValue);
		double answer = output * speedChart[indexA][indexB];
		
		gConsole.addToChatLog(inputValue + " " + unitInputA +  " = " + answer + " " + unitInputB);
		
	}
	
	private   void charts()
	{
		speedChart[0][1] = 1.60934; 
		speedChart[0][2] = 0.44704;
		speedChart[0][3] = 1.46667;
		speedChart[1][0] = 0.621371;
		speedChart[1][2] = 0.277778;
		speedChart[1][3] = 0.911344;
		speedChart[2][0] = 2.23694;
		speedChart[2][1] = 3.6;
		speedChart[2][3] = 3.28084;
		speedChart[3][0] = 0.681818;
		speedChart[3][1] = 1.09728;
		speedChart[3][2] = 0.3048;
		
		lengthChart[0][1] = .08333333333333333;
		lengthChart[0][2] = .0277777;
		lengthChart[0][3] = .0000157828;
		lengthChart[0][4] = 2.539998;
		lengthChart[0][5] = .0253999862;
		lengthChart[0][6] = .0000253999;
		lengthChart[1][0] = 12;
		lengthChart[1][2] = .3333333;
		lengthChart[1][3] = .0001893939;
		lengthChart[1][4] = 30.47999;
		lengthChart[1][5] = .30479999;
		lengthChart[1][6] = .000304799;
		lengthChart[2][0] = 36;
		lengthChart[2][1] = 3;
		lengthChart[2][3] = .00056818;
		lengthChart[2][4] = 91.44;
		lengthChart[2][5] = 0.9144;
		lengthChart[2][6] = 0.0009144;
		lengthChart[3][0] = 63360;
		lengthChart[3][1] = 5280;  
		lengthChart[3][2] = 1760;     
		lengthChart[3][4] = 160934;
		lengthChart[3][5] = 1609.34;
		lengthChart[3][6] = 1.60934;
		lengthChart[4][0] = .393701;
		lengthChart[4][1] = 0.0328084;
		lengthChart[4][2] = 0.0109361;
		lengthChart[4][3] = .0000062137;
		lengthChart[4][5] = .01;
		lengthChart[4][6] = .00001;
		lengthChart[5][0] = 39.3701;
		lengthChart[5][1] = 3.28084;
		lengthChart[5][2] = 1.09361;
		lengthChart[5][3] = 0.000621371;
		lengthChart[5][4] = 100;
		lengthChart[5][6] = 1000;
		lengthChart[6][0] = 39370.1;
		lengthChart[6][1] = 3280.84;
		lengthChart[6][2] = 1093.61;
		lengthChart[6][3] = 0.621371;
		lengthChart[6][4] = 100000;
		lengthChart[6][5] = 1000;
	}
	
	
	@Override
	public void run()
	{
		simpleConvertStart();
	}
}