package mainFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReadWrite
{

	private MainClass theClass;
	public ReadWrite(MainClass a)
	{
		theClass = a;
	}
	/**
	 * This will print all of the contents of a string array
	 * @param contents
	 */
	public void writeAll(String fileName,  ArrayList<String>contents) {
		//make the file and handle exceptions
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//print things out
		PrintWriter pw = new PrintWriter(fw, true);
		for(int i = 0; i < contents.size(); i++)
		{
			pw.println(contents.get(i));
		}
		
		//close everything
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
	}
	/**
	 * This will read a file containing standard characters
	 * @param fileName file location 
	 * @throws IOException if there is not file
	 */
	public void readPrint(String fileName)
	{

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) 
            {
                theClass.addToChatLog(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            theClass.addToChatLog(
                "Unable to open file '" + 
                fileName + "'");                
        } catch (IOException e) {
			// TODO Auto-generated catch block
        	theClass.addToChatLog("IO Exception ocurred");
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the contents of the file and return them as an array list
	 * @param fileName
	 * @return contents an array list
	 */
	public ArrayList<String> read(String fileName)
	{
        // This will reference one line at a time
        String line = null;
        ArrayList<String> contents = new ArrayList<String>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) 
            {
            	contents.add(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
        	if(theClass.chatLog != null)
        	{
        		 theClass.addToChatLog(
        	                "Unable to open file '" + 
        	                fileName + "'");
        	}
            return null;
        } catch (IOException e) {
        	theClass.addToChatLog("IO Exception ocurred");
			e.printStackTrace();
			return null;
		}
        
        return contents;
	}
}
