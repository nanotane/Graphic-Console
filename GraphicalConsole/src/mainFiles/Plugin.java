package mainFiles;

public class Plugin implements RunCommand
{
	protected MainClass theClass;
	public String description = "";
	public String version = "";
	public String author = "";
	public String name = "";
	public String command = "";
	public boolean runAtStart = false;//this determines if the program will automatically run at start
	
	public Plugin(MainClass a)
	{
		theClass = a;
	}
	
	/**
	 * Is called when the program is supposed to run and should
	 * contain the relavent code
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
