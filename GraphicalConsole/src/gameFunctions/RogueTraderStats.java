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
import java.util.LinkedList;
import java.util.Queue;
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
		this.name = "Rogue trader program";
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
		//Loop for navigating through entries
		mainLoop:
			while(true)
			{
				for(int i = 0; i < pointer.sub.size(); i++)//for @ entries
				{
					theClass.addToChatLog(i + " " +pointer.sub.get(i).contents, "*", false);
				}

				String input = theClass.waitForInput().replaceAll(" ", "");//we will also remove all spaces

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
					number = input.substring(1, input.length());//grab last of string which should be the number
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
					number = input.substring(1, input.length());//grab last of string which should be the number
					for(int i = 0; i < pointer.sub.size(); i++)
					{
						if(number.equalsIgnoreCase(Integer.toString(i)))//if the section number is right
						{
							deleteRTFO(pointer, i);
						}
					}
				}
				else if(input.contains("w"))
				{
					writeRTFO(pointer);
				}
				else if(input == null || input.equals("") || Integer.parseInt(input) > pointer.sub.size() || Integer.parseInt(input) < 0)
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
									theClass.addToChatLog(i + " " + pointer.sub.get(i).contents, "--", true);
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
	 * This will allow the user to continue writing after they hit enter, 
	 * and until they type -qw
	 * @param root
	 */
	public void writeRTFO(RTFO root)
	{
		/*
		 *This will have a stack that saves the string objects that are to be used
		 *Once the user quits and writes, then we will go through the list and make all of these 
		 *entries their own RTFO that is a child of the root. 
		 *
		 * 0. Check and make sure the root is a #
		 * 1. check and see if what they typed was to save 
		 * 2. if not then lets add it to the stack.
		 * 3. When we are creating the RTFO's we need to first check and make sure the stack isnt empty
		 * if so then we will do nothing.
		 * 
		 */
		theClass.addToChatLog("Writing...", "-", false);
		String input;
		Queue<String> writingQ = new LinkedList<String>();
		//lets make sure its a # folder
		if(!root.contents.contains("#"))
		{
			return;
		}
		while(true)
		{
			input = theClass.waitForInput();
			//now lets check
			if(input.equalsIgnoreCase("-qw"))
			{
				if(writingQ.peek() != null)//check the top
				{
					//lets create
					while(writingQ.peek()!= null)//go through the que
					{
						RTFO newRTFO = new RTFO();//make temp rtfo
						newRTFO.contents = writingQ.remove();//lets add the string from the queue
						root.sub.add(newRTFO);//lets add it to the children
					}
				}
				break;
			}
			else
			{
				writingQ.add(input);
			}
		}

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
				input = symbolTemp + input;
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
				if(root.sub.get(0).contents == null)
				{
					if(root.sub.get(1).contents.contains(sym))//see if one of the children contains a symbol
					{
						newSym = sym;
					}
				}
				else
				{
					if(root.sub.get(0).contents.contains(sym))//see if one of the children contains a symbol
					{
						newSym = sym;
					}
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
					tempS.trim();//remove leading and ending spaces
					topRTFO.contents = tempS;
					root.sub.add(topRTFO);
				}
				else if(line.contains("#"))//this is a sub section
				{
					String tempS = line;
					if(subRTFO.contents != null)
					{
						topRTFO.sub.add(subRTFO);
					}
					
					subRTFO = new RTFO();
					tempS.trim();//remove leading and ending spaces
					subRTFO.contents = line;
				}
				else if(line.contains(";"))
				{
					if(subRTFO.contents != null)
					{
						topRTFO.sub.add(subRTFO);
					}
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
							"file" + fileName);    
			theClass.addToChatLog("Creating basic file....");
			makeNewFile();
			readRTFile(root);
		} catch (IOException e) {
			theClass.addToChatLog("IO Exception ocurred");
			e.printStackTrace();
		}
		return root;
	}

	public void makeNewFile()
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw, true);
		pw.println("@folder1");
		pw.println(";");
		pw.println("@folder2");
		pw.println(";");
		pw.println("@folder3");
		pw.println(";");
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
	}
	
	@Override
	public void run()
	{
		RogueTraderStart();
	}
}
