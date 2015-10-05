package gameFunctions.id2013.Blindman;
import mainFiles.MainClass;
import mainFiles.Program;
//to do:make boxes work,create new levels, music?
/**
 * 
 * @author Sirkorski
 *
 */
public class Main2 extends Program
{
//	Scanner kbreader = new Scanner(System.in);
	String s1 = "yes";
	boolean gameEnd = true;
	String s2 = "no";
	String c1 = "forward";
	String c2 = "back";
	String c3 = "left";
	String c4 = "right";
	public   int x = 6;
	public   int y = 1;
	public   Level boardData = new Level();
	public   String commandOveride = "Skip";
	public   String s3 = "";
	public   int reCord = 0;
	public   boolean complete = false;
	public   int level = 1;

	public Main2(MainClass a)
	{
		super(a);
		this.name = "Blind man Game";
		this.author = "Sirkorski";
		this.command = "/blindmangame";
		this.description = "You are a blind man navigating a maze. Can you make it out?";
	}
	
	
	
	public   void position() {
		theClass.addToChatLog(y + "");
		theClass.addToChatLog(x+ "");
	}

	public   boolean wallcheck() throws InterruptedException {// TODO
		boolean flag = true;
		if ((boardData.board[y + 1][x] == 2 || boardData.board[y + 1][x] == 3 || boardData.board[y + 1][x] == 8)
				&& s3.equalsIgnoreCase(c1)) {
			flag = false;
			if (boardData.board[y + 1][x] == 3) {
				gameCheck();
			}
			trapCheck();
		}
		if ((boardData.board[y - 1][x] == 2 || boardData.board[y - 1][x] == 3 || boardData.board[y - 1][x] == 8)
				&& s3.equalsIgnoreCase(c2)) {
			flag = false;
			if (boardData.board[y - 1][x] == 3) {
				gameCheck();
			}
			trapCheck();
		}
		if ((boardData.board[y][x + 1] == 2 || boardData.board[y][x + 1] == 3 || boardData.board[y][x + 1] == 8)
				&& s3.equalsIgnoreCase(c3)) {
			flag = false;
			if (boardData.board[y][x + 1] == 3) {
				gameCheck();
			}

			trapCheck();
		}
		if ((boardData.board[y][x - 1] == 2 || boardData.board[y][x - 1] == 3 || boardData.board[y][x - 1] == 8)
				&& s3.equalsIgnoreCase(c4)) {
			flag = false;
			if (boardData.board[y][x - 1] == 3) {
				gameCheck();
			}
			trapCheck();
		}
		if (boardData.board[y + 1][x] == 7 && s3.equalsIgnoreCase(c1)) {
			theClass.addToChatLog("What is this metal shape in the darkness? You decide to hide it from Him.");
			reCord++;
		}
		if (boardData.board[y - 1][x] == 7 && s3.equalsIgnoreCase(c2)) {

			theClass.addToChatLog("What is this metal shape in the darkness? You decide to hide it from Him.");
			reCord++;
		}
		if (boardData.board[y][x + 1] == 7 && s3.equalsIgnoreCase(c3)) {

			theClass.addToChatLog("What is this metal shape in the darkness? You decide to hide it from Him.");
			reCord++;
		}
		if (boardData.board[y][x - 1] == 7 && s3.equalsIgnoreCase(c4)) {
			theClass.addToChatLog("What is this metal shape in the darkness? You decide to hide it from Him.");
			reCord++;
		}

		return flag;

	}

	public   boolean gameCheck() throws InterruptedException {
		boolean complete = false;
		theClass.addToChatLog("In the dark you feel a door handle");
		// TODO
		theClass.addToChatLog("Would you like to leave, Blind Man?");
		s3 = theClass.waitForInput();
		if (s3.equalsIgnoreCase(s1)) {
			complete = true;
		}

		theClass.addToChatLog("You decide to leave.");
		reCord = 0;
		theClass.addToChatLog("Would you like to continue Blind Man?");
		s3 = theClass.waitForInput();
		if (s3.equalsIgnoreCase(s1)) {
			boardData.lvl2();
			level++;
		} else {
			theClass.addToChatLog("This is the end Blind Man.");
			Thread.sleep(3000);
			System.exit(0);

		}
		return false;
	}

	public   boolean trapCheck() {// TODO
		if (boardData.board[y + 1][x] == 8 && s3.equalsIgnoreCase(c1)) {// forward
			theClass.addToChatLog("Your screams are cut short as the trap finishes it's work.");
			gameEnd = false;
			reCord = 0;
		}
		if (boardData.board[y - 1][x] == 8 && s3.equalsIgnoreCase(c2)) {
			theClass.addToChatLog("Your screams are cut short as the trap finishes it's work.");
			gameEnd = false;
			reCord = 0;
		}
		if (boardData.board[y][x + 1] == 8 && s3.equalsIgnoreCase(c3)) {
			theClass.addToChatLog("Your screams are cut short as the trap finishes it's work.");
			gameEnd = false;
			reCord = 0;
		}
		if (boardData.board[y][x - 1] == 8 && s3.equalsIgnoreCase(c4)) {
			theClass.addToChatLog("Your screams are cut short as the trap finishes it's work.");
			gameEnd = false;
			reCord = 0;
		}
		return false;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public void runBlindMan() throws InterruptedException {
		boardData.lvl1();
		theClass.addToChatLog("Would you like to play the game?");
		s3 = theClass.waitForInput();

		if (s3.equalsIgnoreCase(s1)) {
			theClass.addToChatLog("Listen to Commands.");
			Thread.sleep(3000);
			theClass.addToChatLog("Follow the Rules.");
			Thread.sleep(3000);
			theClass.addToChatLog("You may move forward, back, to the left or to the right.");
			theClass.addToChatLog("Listen to Commands.");
			Thread.sleep(3000);
			theClass.addToChatLog("Explore");
			Thread.sleep(3000);
			theClass.addToChatLog("Goodbye Blind Man.");
			Thread.sleep(12000);

		} else if (s3.equalsIgnoreCase(commandOveride)) {
			theClass.addToChatLog("Cutscene skipped.");
		} else {
			theClass.addToChatLog("This is the end Blind Man.");
			Thread.sleep(3000);
			System.exit(0);

		}
		reCord = 0;
		theClass.addToChatLog("Hello.");
		Thread.sleep(3000);
		theClass.addToChatLog("Wake.");
		Thread.sleep(3000);
		theClass.addToChatLog("Escape.");
		Thread.sleep(3000);
		theClass.addToChatLog("Survive.");
		Thread.sleep(3000);
		theClass.addToChatLog("Let the game begin again.");
		Thread.sleep(3000);
		while (gameEnd) {
			theClass.addToChatLog("Now which way would you like to go?");
			s3 = theClass.waitForInput();
			// theClass.addToChatLog("Data recieved");
			if (s3.equalsIgnoreCase(c1)) // forward
			{
				// theClass.addToChatLog("forward statement entered");
				if (wallcheck()) {
					// theClass.addToChatLog("wall check entered");
					y++;
					boardData.board[y][x] = 1;

					boardData.board[y - 1][x] = 0;

				} else {
					theClass.addToChatLog("You have hit something solid.");
					Thread.sleep(1000);
					theClass.addToChatLog("This could be a box you can break through by pushing");
					theClass.addToChatLog("or a wall.");

				}

			} else if (s3.equalsIgnoreCase(c2))// backwards
			{
				if (wallcheck()) {
					y--;
					boardData.board[y][x] = 1;
					boardData.board[y + 1][x] = 0;

				} else {
					theClass.addToChatLog("You have hit something solid.");
					Thread.sleep(1000);
					theClass.addToChatLog("This could be a box you can break through by pushing");
					theClass.addToChatLog("or a wall.");
				}
			} else if (s3.equalsIgnoreCase(c3))// left TODO
			{
				if (wallcheck()) {
					x++;
					boardData.board[y][x] = 1;
					boardData.board[y][x - 1] = 0;

				} else {
					theClass.addToChatLog("You have hit something solid.");
					Thread.sleep(1000);
					theClass.addToChatLog("This could be a box you can break through by pushing");
					theClass.addToChatLog("or a wall.");
					;
				}
			} else if (s3.equalsIgnoreCase(c4))// right
			{
				if (wallcheck()) {
					x--;
					boardData.board[y][x] = 1;

					boardData.board[y][x + 1] = 0;

				} else {
					theClass.addToChatLog("You have hit something solid.");
					Thread.sleep(1000);
					theClass.addToChatLog("This could be a box you can break through by pushing");
					theClass.addToChatLog("or a wall.");
				}
			}

		}
		if (reCord < 3 && reCord > 0 && complete == true && level == 1) {
			theClass.addToChatLog("The objects are voice recorders. You listen in secret.");
			Thread.sleep(3000);
			theClass.addToChatLog("eports of...sudden attacks..3 million de...");
		}
		if (reCord < 5 && reCord > 3 && complete == true && level == 1) {
			theClass.addToChatLog("The objects are voice recorders. You listen in secret.");
			Thread.sleep(3000);
			theClass.addToChatLog("eports of...sudden attacks..3 million de...");
			theClass.addToChatLog("erment is...ilitary law...coup...project titled...");
		}
		if (reCord < 7 && reCord > 4 && complete == true && level == 1) {
			theClass.addToChatLog("The objects are voice recorders. You listen in secret.");
			Thread.sleep(3000);
			theClass.addToChatLog("eports of...sudden attacks..3 million de...");
			theClass.addToChatLog("erment is...ilitary law...coup...project titled...");
			theClass.addToChatLog("d Man...bility...church...ope has decr...eath on the stre...nuclear opti...");

		}
		if (reCord == 1 && complete == true && level == 2) {
			theClass.addToChatLog("The objects are voice recorders. You listen in secret.");
			theClass.addToChatLog("SA...ussia...ar...vasion.");
		}
		if (reCord == 2 && complete == true && level == 2) {
			theClass.addToChatLog("The objects are voice recorders. You listen in secret.");
			theClass.addToChatLog("SA...ussia...ar...vasion.");
			theClass.addToChatLog("d Man...revolution...he Blue Guard...");
		}
		if (reCord == 3 && complete == true && level == 2) {
			theClass.addToChatLog("The objects are voice recorders. You listen in secret.");
			theClass.addToChatLog("SA...ussia...ar...vasion.");
			theClass.addToChatLog("d Man...revolution...he Blue Guard...");
			theClass.addToChatLog("shington...dest...ast hope...Blin...wa...");

		}
	}
	@Override
	public void run()
	{
		try {
			runBlindMan();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			theClass.addToChatLog("THE BLIND MAN GAME HAS CRASHED");
			theClass.addToChatLog("INTERRUPT EXCEPTION OCCURRED");
		}
	}
}
