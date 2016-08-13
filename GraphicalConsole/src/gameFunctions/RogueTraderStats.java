package gameFunctions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import mainFiles.MainClass;
import mainFiles.Plugin;

public class RogueTraderStats extends Plugin{

	public boolean creating = false;
	public RTFO current;
	public RTFO topOfList;
	public Stack<RTFO> rtfoStack = new Stack();
	public RogueTraderStats(MainClass a) {
		super(a);
		this.name = "Basic Calculator";
		this.command = "/rtf";
		this.author = "Ian Davila";
		this.description = "Rogue trader file system";
	}

	public void RogueTraderStart()
	{
		theClass.addToChatLog("Rogue trader file system", "~~", true);
		theClass.addToChatLog("");
		RTFO root = new RTFO();
		RTFO pointer = root;
		root = readRTFile(root);
		//		now for navigating through it
		//				for(int i = 0; i < rtfoList.size(); i++)//for @ entries
		//				{
		//					theClass.addToChatLog(rtfoList.get(i).contents);
		//					for(int j = 0; j < rtfoList.get(i).sub.size(); j++)//for # entries
		//					{
		//						theClass.addToChatLog(rtfoList.get(i).sub.get(j).contents);
		//						for(int k = 0; k < rtfoList.get(i).sub.get(j).sub.size(); k++) //for normal entries
		//						{
		//							theClass.addToChatLog(rtfoList.get(i).sub.get(j).sub.get(k).contents);
		//						}
		//					}
		//						
		//				}
		//Loop for navigating through entries
		while(true)
		{
			for(int i = 0; i < pointer.sub.size(); i++)//for @ entries
			{
				theClass.addToChatLog(i + " " +pointer.sub.get(i).contents, "*", false);
			}

			String input = theClass.waitForInput();

			if(input.equals("b"))//if they want to go back
			{
				if(!rtfoStack.isEmpty())//and the stack is not empty
				{
					pointer =  rtfoStack.pop();//then lets pop the stack and set the pointer to what we popped
				}
			}
			else if(Integer.parseInt(input) > pointer.sub.size() || Integer.parseInt(input) < 0 || input == null)
			{
				theClass.addToChatLog("out of bounds or null");
			}
			else//if not then lets see what they wanted
			{
				boolean topwLoop = true;//flag for if we found something
				while(topwLoop)
				{
					//now choose a section
					topfLoop:
						for(int i = 0; i < pointer.sub.size(); i++)
						{
							if(input.equalsIgnoreCase(Integer.toString(i)))//if the section number is right
							{
								RTFO temp = pointer;
								rtfoStack.push(temp);
								theClass.addToChatLog(i + " " + pointer.sub.get(i).contents);
								pointer = pointer.sub.get(i);
								topwLoop = false;
								break topfLoop;
							}
						}
				}
			}
		}
	}
	/**
	 * THis will read the rogue trader file and create the file system
	 */
	public RTFO readRTFile(RTFO root)
	{
		String line = null;
		//make the rtfo list from the file
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader("RogueTrader.txt");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			RTFO topRTFO = new RTFO();
			RTFO subRTFO = new RTFO();
			RTFO subsubRTFO = new RTFO();
			while((line = bufferedReader.readLine()) != null) 
			{
				//if its an at then its the head of something
				if(line.contains("@"))
				{
					String tempS = line;
					tempS = line.substring(1, line.length());//remove at
					tempS.trim();//remove leading and ending spaces
					topRTFO.contents = tempS;
					root.sub.add(topRTFO);
				}
				else if(line.contains("#"))//this is a sub section
				{
					String tempS = line;
					topRTFO.sub.add(subRTFO);
					subRTFO = new RTFO();
					tempS = line.substring(1, line.length());//remove at
					tempS.trim();//remove leading and ending spaces
					subRTFO.contents = line;
					//					topRTFO.sub.add(subRTFO);
				}
				else if(line.contains(";"))
				{
					topRTFO.sub.add(subRTFO);
					subRTFO = new RTFO();
					topRTFO = new RTFO();
				}
				else
				{
					subsubRTFO = new RTFO();
					subsubRTFO.contents = (line.trim());
					subRTFO.sub.add(subsubRTFO);
				}
			}   

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			theClass.addToChatLog(
					"Unable to open file '" + 
							"file" + "'");                
		} catch (IOException e) {
			// TODO Auto-generated catch block
			theClass.addToChatLog("IO Exception ocurred");
			e.printStackTrace();
		}
		return root;
	}
	@Override
	public void run()
	{
		RogueTraderStart();
	}
}
