package mainFiles;

public class Program implements RunCommand
{
	protected MainClass theClass;
	public String description = "";
	public String version = "";
	public String author = "";
	public String name = "";
	public String command = "";
	public Program(MainClass a)
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
