package mainFiles;

import java.util.ArrayList;

import calculatingFunctions.BasicCalc;
import calculatingFunctions.ResistorCalc;
import calculatingFunctions.SimpleConverter;
import calculatingFunctions.StringBinaryConverter;
import gameFunctions.id2013.TarotCardDealer;
import gameFunctions.id2013.TicTacToeGame;
import gameFunctions.id2013.Blindman.Main2;
import utilityFunctions.DiceCalc;

public class ProgramLoader {
	
	/**
	 * This will create objects for all of the programs that are given
	 * ADD NEW PROGRAMS TO THIS LIST
	 * @return an array list of all new programs
	 */
	public ArrayList<Program> createAll(MainClass theClass)
	{
		ArrayList<Program> programList = new ArrayList<Program>();
		programList.add(new ResistorCalc(theClass));
		programList.add(new DiceCalc(theClass));
		programList.add(new BasicCalc(theClass));
		programList.add(new SimpleConverter(theClass));
		programList.add(new StringBinaryConverter(theClass));
		programList.add(new TarotCardDealer(theClass));
		programList.add(new TicTacToeGame(theClass));
		programList.add(new Main2(theClass)); //this is the blind man game
		return programList;
	}
}
