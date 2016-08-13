package mainFiles;

import gameFunctions.RogueTraderStats;
import gameFunctions.id2013.TarotCardDealer;
import gameFunctions.id2013.TicTacToeGame;
import gameFunctions.id2013.Blindman.Main2;

import java.util.ArrayList;

import utilityFunctions.DiceCalc;
import calculatingFunctions.BasicCalc;
import calculatingFunctions.ResistorCalc;
import calculatingFunctions.SimpleConverter;
import calculatingFunctions.StringBinaryConverter;

public class PluginLoader {
	
	/**
	 * This will create objects for all of the programs that are given
	 * ADD NEW PROGRAMS TO THIS LIST
	 * @return an array list of all new programs
	 */
	public ArrayList<Plugin> createAll(MainClass theClass)
	{
		ArrayList<Plugin> programList = new ArrayList<Plugin>();
		programList.add(new ResistorCalc(theClass));
		programList.add(new DiceCalc(theClass));
		programList.add(new BasicCalc(theClass));
		programList.add(new SimpleConverter(theClass));
		programList.add(new StringBinaryConverter(theClass));
		programList.add(new TarotCardDealer(theClass));
		programList.add(new TicTacToeGame(theClass));
		programList.add(new Main2(theClass)); //this is the blind man game
		programList.add(new RogueTraderStats(theClass));
		return programList;
	}
}
