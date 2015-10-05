package mainFiles;

public class Program 
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
	
	public void run()
	{
		theClass.addToChatLog("THE PROGRAM " + name + "HAS NOT SET UP A RUN METHOD!");
	}
}
