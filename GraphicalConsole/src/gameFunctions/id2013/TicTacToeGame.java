package gameFunctions.id2013;
import mainFiles.MainClass;
import mainFiles.Program;
/**
 * 
 * @author Jospeh C
 *
 */
public class TicTacToeGame extends Program {

	/**
	 * @param args
	 */
	public   char place1 = 's';
	public   char place2 = 's';
	public   char place3 = 's';
	public   char place4 = 's';
	public   char place5 = 's';
	public   char place6 = 's';
	public   char place7 = 's';
	public   char place8 = 's';
	public   char place9 = 's';
	public   boolean flag = true;
	public   String turn = "x";

	public TicTacToeGame(MainClass a)
	{
		super(a);
		this.name = "Tic tac toe game";
		this.author = "Joseph C";
		this.command = "/tictactoe";
		this.description = "A two player tic tac toe game";
	}

	public   boolean checkDraw()
	{
		boolean r = false;
		if(place1 != 's' 
				&& place2 != 's'
				&& place3 != 's'
				&& place4 != 's'
				&& place5 != 's'
				&& place6 != 's'
				&& place7 != 's'
				&& place8 != 's'
				&& place9 != 's')
		{
			theClass.addToChatLog("It's a draw! Would you like to play again? If so, click run. The creator of this game was Joseph Canales.");
			r = true;
		}
		return r;
	}
	public   boolean check()
	{
		boolean r = false;
		//This section checks to see if there is 3 in a row
		if(place1 == place2 && place2 == place3 && place1 != 's')
		{
			flag = false;
			r = true;
		}
		if(place4 == place5 && place5 == place6 && place4 != 's')
		{
			flag = false;
			r = true;
		}
		if(place7 == place8 && place8 == place9 && place7 != 's')
		{
			flag = false;
			r = true;
		}
		if(place1 == place4 && place4 == place7 && place1 != 's')
		{
			flag = false;
			r = true;
		}
		if(place2 == place5 && place5 == place8 && place2 != 's')
		{
			flag = false;
			r = true;
		}
		if(place3 == place6 && place6 == place9 && place3 != 's')
		{
			flag = false;
			r = true;
		}
		if(place1 == place5 && place5 == place9 && place1 != 's' )
		{
			flag = false;
			r = true;
		}
		if(place3 == place5 && place5 == place7 && place3 != 's')
		{
			flag = false;
			r = true;
		}
		return r;
	}
	public void board()
	{
		theClass.addToChatLog("~~>"+place1 + "|" + place2 + "|" + place3);
		theClass.addToChatLog("~~>" +place4 + "|" + place5 + "|" + place6);
		theClass.addToChatLog("~~>"+place7 + "|" + place8 + "|" + place9);
	}
	public void runTicTacToe() {
		// TODO Auto-generated method stub
		//Scanner kbinput = new Scanner(System.in);	
		String a = ("Welcome to Tic-Tac-Toe!");
		theClass.addToChatLog(a);
		//		theClass.addToChatLog("What would you like to play x or o?");
		while(flag)
		{
			turn = "x";
			while(true)
			{
				theClass.addToChatLog("Player 1 turn");
				String TicTac = theClass.waitForInput();
				//This section decides were player 1's x goes
				if(TicTac.equalsIgnoreCase("1") && place1 != 'o' && place1 != 'x')
				{
					place1 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("2") && place2 != 'o' && place2 != 'x')
				{
					place2 ='x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("3") && place3 != 'o' && place3 != 'x')
				{
					place3 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("4") && place4 != 'o' && place4 != 'x')
				{
					place4 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("5") && place5 != 'o' && place5 != 'x')
				{
					place5 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("6") && place6 != 'o' && place6 != 'x')
				{
					place6 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("7") && place7 != 'o' && place7 != 'x')
				{
					place7 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("8") && place8 != 'o' && place8 != 'x')
				{
					place8 = 'x';
					break;
				}
				else if(TicTac.equalsIgnoreCase("9") && place9 != 'o' && place9 != 'x')
				{
					place9 = 'x';
					break;
				}
				theClass.addToChatLog("Oops, try again.");
				board();
			}
			board();
			if(check())
			{
				theClass.addToChatLog("Good game! Player 1 wins. Would you like to play again? If so, click run. The creator of this game was Joseph Canales.");
				break;
			}
			if(checkDraw())
			{
				break;
			}
			//This section decides where player 2's o goes.
			turn = "o";
			while(true)
			{
				theClass.addToChatLog("Player 2 turn");
				String TicTacToe = theClass.waitForInput();
				if(TicTacToe.equalsIgnoreCase("1") && place1 != 'x' && place1 != 'o')
				{
					place1 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("2") && place2 != 'x' && place2 != 'o')
				{
					place2 ='o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("3") && place3 != 'x' && place3 != 'o')
				{
					place3 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("4") && place4 != 'x' && place4 != 'o')
				{
					place4 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("5") && place5 != 'x' && place5 != 'o')

				{
					place5 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("6") && place6 != 'x' && place6 != 'o')
				{
					place6 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("7") && place7 != 'x' && place7 != 'o')
				{
					place7 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("8") && place8 != 'x' && place8 != 'o')
				{
					place8 = 'o';
					break;
				}
				else if(TicTacToe.equalsIgnoreCase("9") && place9 != 'x' && place9 != 'o')
				{
					place9 = 'o';
					break;
				}
				theClass.addToChatLog("Oops, try again");
				board();
			}
			board();
			if(check())
			{
				theClass.addToChatLog("Good game! Player 2 wins. Would you like to play again? If so, click run. The creator of this game was Joseph Canales.");
				break;	
			}
			if(checkDraw())
			{
				break;
			}

		}


		{

		}

	}
	
	@Override
	public void run()
	{
		runTicTacToe();
	}

}
