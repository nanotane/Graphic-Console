package gameFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Stack;

import mainFiles.MainClass;
import mainFiles.Plugin;

public class RogueTraderStats extends Plugin{

	public boolean creating = false;
	public RTFO current;
	public RTFO topOfList;
	public Stack<RTFO> rtfoStack = new Stack();
	public String fileName = "RogueTrader.txt";
	public String[] symbols = {"@", "#"};
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
		mainLoop:
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
			else if(input.equalsIgnoreCase("q"))//if we are quiting
			{
				break mainLoop;
			}
			else if(input.contains("e"))//if we are editing
			{
				String number = "";
				number = input.substring(input.length()-1, input.length());//grab last of string which should be the number
				for(int i = 0; i < pointer.sub.size(); i++)
				{
					if(number.equalsIgnoreCase(Integer.toString(i)))//if the section number is right
					{
						editRTFO(pointer.sub.get(i));
					}
				}
			}
			else if(input.equalsIgnoreCase("n"))
			{
				pointer = createRTFO(pointer);
			}
			else if(input.equalsIgnoreCase("s"))
			{
				theClass.addToChatLog("Saved", "~", true);
				saveRTFO(root);
			}
			else if(input.contains("d"))
			{
				String number = "";
				number = input.substring(input.length()-1, input.length());//grab last of string which should be the number
				for(int i = 0; i < pointer.sub.size(); i++)
				{
					if(number.equalsIgnoreCase(Integer.toString(i)))//if the section number is right
					{
						deleteRTFO(pointer, i);
					}
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
		theClass.addToChatLog("Exiting rogue trader program", "~~", true);
	}
	/**
	 * This will edit the contents of an RTFO 
	 * @param toEdit
	 */
	public void editRTFO(RTFO toEdit)
	{
		theClass.addToChatLog("Type in new info and hit enter", "~", true);
		String input = theClass.waitForInput();
		String symbolTemp = toEdit.contents.substring(0, 1);
		for(String sym: symbols)
		{
			if(symbolTemp.equalsIgnoreCase(sym))
			{
				input = input + symbolTemp;
			}
		}
		toEdit.contents = input;
	}
	/**
	 * This will delete an RTFO
	 * @param toDelete RTFO to be deleted
	 */
	public void deleteRTFO(RTFO root, int toDelete)
	{
		root.sub.remove(toDelete);
		theClass.addToChatLog("RTFO deleted", "~", true);
	}
	/**
	 * This will create a new RTFO
	 * @param root
	 * @return root
	 */
	public RTFO createRTFO(RTFO root)
	{
		theClass.addToChatLog("Type in the name of the folder", "~", true);
		String input = theClass.waitForInput();
		String newSym = "";
		RTFO newRTFO = new RTFO();
		//for making a new @
		if(!root.sub.isEmpty())//are there children?
		{	
			for(String sym: symbols)//if so go through the symbols list...
			{
				if(root.sub.get(0).contents.contains(sym))//see if one of the children contains a symbol
				{
					newSym = sym;
				}
			}
		}
		//for making a new #
		if(root.contents != null)
		{
			if(root.contents.contains("@"))
			{
				newSym = "#";
			}
		}
		newRTFO.contents = newSym + input;
		root.sub.add(newRTFO);
		return root;
	}
	/**
	 * This will save all RTFO contents into the current text file
	 */
	public void saveRTFO(RTFO root)
	{
		FileWriter fw;
		RTFO pointer = root;
		try {
			fw = new FileWriter(fileName);
			PrintWriter pw = new PrintWriter(fw, true);
			traverse(pointer, fw, pw);
			fw.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * This will traverse the elements of the tree and print them to the file
	 * @param pointer 
	 * @param fw
	 * @param pw
	 */
	public void traverse(RTFO pointer, FileWriter fw, PrintWriter pw)
	{
		if(!pointer.sub.isEmpty())
		{
			for(int i = 0; i < pointer.sub.size(); i++)
			{
				if(pointer.sub.get(i).contents != null)
				{
					pw.println(pointer.sub.get(i).contents);
				}
				traverse(pointer.sub.get(i), fw, pw);
				
				if(pointer.contents == null)
				{
					pw.println(";");
				}
			}
		}
		else
		{
			return;
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
					new FileReader(fileName);

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
					//tempS = line.substring(1, line.length());//remove at
					tempS.trim();//remove leading and ending spaces
					topRTFO.contents = tempS;
					root.sub.add(topRTFO);
				}
				else if(line.contains("#"))//this is a sub section
				{
					String tempS = line;
					topRTFO.sub.add(subRTFO);
					subRTFO = new RTFO();
					//tempS = line.substring(1, line.length());//remove at
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
