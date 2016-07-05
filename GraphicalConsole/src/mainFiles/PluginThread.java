package mainFiles;

public class PluginThread implements Runnable
{
	private Plugin pluginObj;
	
	public PluginThread(Plugin po)
	{
		pluginObj = po;//lets set the plugin object that we are going to use
	}
	@Override
	public void run() {
		pluginObj.run();//lets run that object in this thread
	}

}
