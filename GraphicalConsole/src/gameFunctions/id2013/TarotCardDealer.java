package gameFunctions.id2013;
import java.util.Random;

import mainFiles.MainClass;
import mainFiles.Program;
/**
 * 
 * @author Jonathan G
 *
 */
public class TarotCardDealer extends Program{//This program generates random Tarot cards and gives the according fortune

	/**
	 * @param args
	 */
	//global variables
	public  int p; // position of the card
	public  int r; // number of the card
	public  int s; // suit of minor arcana

	//Major flags
	//To ensure the same card isn't drawn twice
	//This is NOT a foolproof tactic.
	public  boolean fool = false;
	public  boolean magi = false;
	public  boolean prie = false;
	public  boolean empr = false;
	public  boolean empe = false;
	public  boolean heir = false;
	public  boolean love = false;
	public  boolean chrr = false;
	public  boolean just = false;
	public  boolean herm = false;
	public  boolean fort = false;
	public  boolean stre = false;
	public  boolean hang = false;//yayyyyyy booleans!
	public  boolean deat = false;
	public  boolean temp = false;
	public  boolean devi = false;
	public  boolean towe = false;
	public  boolean star = false;
	public  boolean moon = false;
	public  boolean sun = false;
	public  boolean judg = false;
	public  boolean worl = false;

	//Minor Flags
	public  boolean car1 = false;
	public  boolean car2 = false;
	public  boolean car3 = false;
	public  boolean car4 = false;
	public  boolean car5 = false;
	public  boolean car6 = false;
	public  boolean car7 = false;//more booleans!
	public  boolean car8 = false;
	public  boolean car9 = false;
	public  boolean car0 = false;
	public  boolean page = false;
	public  boolean knig = false;
	public  boolean quee = false;
	public  boolean king = false;
	
	public TarotCardDealer(MainClass a)
	{
		super(a);
		this.name = "Tarot Card Dealer";
		this.author = "Jonathan G";
		this.command = "/tarot";
		this.description = "Randomly deals out tarot cards to the user";
		this.version = "1.0";
	}
	// Template for major Arcana meanings
	public  void arcanaTemp(String x, String y, String z) {// Upright,Reversed,Arcana
		theClass.addToChatLog(z);
		if (p == 0)
			theClass.addToChatLog(x);
		else
			theClass.addToChatLog(y);
	}

	// Template for minor meanings
	public  void minorTemp(String x, String y) {
		if (p == 0)
			theClass.addToChatLog("This card represents " + x);
		else
			theClass.addToChatLog("This card represents " + y);
	}

	//Duplicate prevention(unused)
	public  void dupPrevent(boolean a,boolean b,boolean c,int d,int e,int f){
		if ((r == f && !a)||((r==d && b)&& !a)||((r==e && c)&& b));
	}

	//Minor Meanings
	public  void minorMean(int r){
		// Individual meanings
		// pips
		if ((r == 1 && !car1)||((r==14 && king)&&!car1)||((r==13 && quee)&&king)){//redirecting duplicate cards
			theClass.addToChatLog("1");
			minorTemp("a beginning", "improper use");
			car1 = true;
		}
		else if ((r == 2 && !car2)||((r==1 && car1)&&!car2)||((r==14 && king)&&car1)){
			theClass.addToChatLog("2");
			minorTemp("balance", "a lack of balance");
			car2 = true;
		}
		else if ((r == 3 && !car3)||((r==2 && car2)&&!car3)||((r==1 && car1)&&car2)){
			theClass.addToChatLog("3");
			minorTemp("finding success", "defeat");
			car3 = true;
		}
		else if ((r == 4 && !car4)||((r==3 && car3)&&!car4)||((r==2 && car2)&&car3)){
			theClass.addToChatLog("4");
			minorTemp("security", "chaos");
			car4 = true;
		}
		else if ((r == 5 && !car5)||((r==4 && car4)&&!car5)||((r==3 && car3)&&car4)){
			theClass.addToChatLog("5");
			minorTemp("loss", "gain");
			car5 = true;
		}
		else if ((r == 6 && !car6)||((r==5 && car5)&&!car6)||((r==4 && car4)&&car5)){
			theClass.addToChatLog("6");
			minorTemp("realization", "ignorance");
			car6 = true;
		}
		else if ((r == 7 && !car7)||((r==6 && car6)&&!car7)||((r==5 && car5)&&car6)){
			theClass.addToChatLog("7");
			minorTemp("a choice", "foolishness");
			car7 = true;
		}
		else if ((r == 8 && !car8)||((r==7 && car7)&&!car8)||((r==6 && car6)&&car7)){
			theClass.addToChatLog("8");
			minorTemp("action", "inaction");
			car8 = true;
		}
		else if ((r == 9 && !car9)||((r==8 && car8)&&!car9)||((r==7 && car7)&&car8)){
			theClass.addToChatLog("9");
			minorTemp("completion", "anxeity");
			car9 = true;
		}
		else if ((r == 10 && !car0)||((r==9 && car9)&&!car0)||((r==8 && car8)&&car9)){
			theClass.addToChatLog("10");
			minorTemp("burden", "being overwhelmed");
			car0 = true;
		}
		// page
		else if ((r == 11 && !page)||((r==10 && car0)&&!page)||((r==9 && car9)&&car0)){
			theClass.addToChatLog("Page");
			minorTemp("childishness", "irresponsibility");
			page = true;
		}
		// knight
		else if ((r == 12 && !knig)||((r==11 && page)&&!knig)||((r==10 && car0)&&page)){
			theClass.addToChatLog("Knight");
			minorTemp("action", "heedless action");
			knig = true;
		}
		// queen
		else if ((r == 13 && !quee)||((r==12 && knig)&&!quee)||((r==11 && page)&&knig)){
			theClass.addToChatLog("Queen");
			minorTemp("intuition", "injustice");
			quee = true;
		}
		// king
		else if ((r == 14 && !king)||((r==13 && quee)&&!king)||((r==12 && knig)&&quee)){
			theClass.addToChatLog("King");
			minorTemp("power and leadership", "abuse of power");
			king = true;
		}
	}

	//Major Arcana
	public  void majorArcana(int r) {
		Random rndm = new Random();


		p = 0 + rndm.nextInt(2);
		if (p == 0)
			theClass.addToChatLog("Upright");
		else
			theClass.addToChatLog("Reversed");
		theClass.addToChatLog("Major Arcana");
		// Fool
		if ((r == 0 && !fool)||((r==21 && worl)&&!fool)||((r==20 && judg)&worl))//redirect duplicate cards
		{
			theClass.addToChatLog("0");//print correct number
			arcanaTemp("You will begin a Journey","You have not the courage to go forward", "Fool");
			fool = true;//protect against duplicate cards
		}
		// Magician
		else if ((r == 1 && !magi)||((r==0 && fool)&&!magi)||((r==21 && worl)&&fool)){
			theClass.addToChatLog("1");
			arcanaTemp("You will take action", "You will be inactive","Magician");
			magi = true;
		}
		// High Priestess
		else if ((r == 2 && !prie)||((r==1 && magi)&&!prie)||((r==0 && fool)&&magi)){
			theClass.addToChatLog("2");
			arcanaTemp("You will find hidden knowledge", "You cannot focus","High Priestess");
			prie = true;
		}
		// Empress
		else if ((r == 3 && !empr)||((r==2 && prie)&&!empr)||((r==1 && magi)&&prie)){
			theClass.addToChatLog("3");
			arcanaTemp("You will do well with your family","You will have problems in family matters", "Empress");
			empr = true;
		}
		// Emperor
		else if ((r == 4 && !empe)||((r==3 && empr)&&!empe)||((r==2 && prie)&&empr)){
			theClass.addToChatLog("4");
			arcanaTemp("You will be a strong leader", "You will be weak","Emperor");
			empe = true;
		}
		// Heirophant
		else if ((r == 5 && !heir)||((r==4 && empe)&&!heir)||((r==3 && empr)&&empe)){
			theClass.addToChatLog("5");
			arcanaTemp("You will be reverent and honest","You will be dishonest and disloyal", "Heirophant");
			heir = true;
		}
		// Lovers
		else if ((r == 6 && !love)||((r==5 && heir)&&!love)||((r==4 && empe)&&heir)){
			theClass.addToChatLog("6");
			arcanaTemp("You will either find love, or have a choice","You will find a meaningless relationship", "Lovers");
			love = true;
		}
		// Chariot
		else if ((r == 7 && !chrr)||((r==6 && love)&&!chrr)||((r==5 && heir)&&love)){
			theClass.addToChatLog("7");
			arcanaTemp("You will find victory", "You will find defeat","Chariot");
			chrr = true;
		}
		// Justice
		else if ((r == 8 && !just)||((r==7 && chrr)&&!just)||((r==6 && love)&&chrr)){
			theClass.addToChatLog("8");
			arcanaTemp("You will find fairness and rationality","You encounter unfairness", "Justice");
			just = true;
		}
		// Hermit
		else if ((r == 9 && !herm)||((r==8 && just)&&!herm)||((r==7 && chrr)&&just)){
			theClass.addToChatLog("9");
			arcanaTemp("You will find yourself alone, searching for answers","You may be surrounded by people, but oblivious to the truth","Hermit");
			herm = true;
		}
		// Wheel of Fortune
		else if ((r == 10 && !fort)||((r==9 && herm)&&!fort)||((r==8 && just)&&herm)){
			theClass.addToChatLog("10");
			arcanaTemp("Fate will guide you","You must forge your own path in life", "Wheel of Fortune");
			fort = true;
		}
		// Strength
		else if ((r == 11 && !stre)||((r==10 && fort)&&!stre)||((r==9 && herm)&&fort)){
			theClass.addToChatLog("11");
			arcanaTemp("You will find courage and virtue","You will lack courage and virtue", "Strength");
			stre = true;
		}
		// Hanged Man
		else if ((r == 12 && !hang)||((r==11 && stre)&&!hang)||((r==10 && fort)&&stre)){
			theClass.addToChatLog("12");
			arcanaTemp("You must make sacrifices for others","You will act in selfishness", "The Hanged Man");
			hang = true;
		}
		// DEATH
		else if ((r == 13 && !deat)||((r==12 && hang)&&!deat)||((r==11 && stre)&&hang)){
			theClass.addToChatLog("13");
			arcanaTemp("Things will change","You will find yourself reluctant to change", "Death");
			deat = true;
		}
		// Temperance
		else if ((r == 14 && !temp)||((r==13 && deat)&&!temp)||((r==12 && hang)&&deat)){
			theClass.addToChatLog("14");
			arcanaTemp("You will find balance in your life","You will have trouble balancing your life", "Temperance");
			temp = true;
		}
		// Devil
		else if ((r == 15 && !devi)||((r==14 && temp)&&!devi)||((r==13 && deat)&&temp)){
			theClass.addToChatLog("15");
			arcanaTemp("You will encounter greed","You will encounter charity", "Devil");
			devi = true;
		}
		// Tower
		else if ((r == 16 && !towe)||((r==15 && devi)&&!towe)||((r==14 && temp)&&devi)){
			theClass.addToChatLog("16");
			arcanaTemp("You will experience a catastrohpe","You will be able to avoid a catastrophe", "Tower");
			towe = true;
		}
		// Star
		else if ((r == 17 && !star)||((r==16 && towe)&&!star)||((r==15 && devi)&&towe)){
			theClass.addToChatLog("17");
			arcanaTemp("You will encounter great hope","You will find yourself without hope", "Star");
			star = true;
		}
		// Moon
		else if ((r == 18 && !moon)||((r==17 && star)&&!moon)||((r==16 && towe)&&star)){
			theClass.addToChatLog("18");
			arcanaTemp("You will face illusion", "You will find sanity", "Moon");
			moon = true;
		}
		// Sun
		else if ((r == 19 && !sun)||((r==18 && moon)&&!sun)||((r==17 && star)&&moon)){
			theClass.addToChatLog("19");
			arcanaTemp("Out of despair, there is hope", "There is no hope","Sun");
			sun = true;
		}
		// Judgement
		else if ((r == 20 && !judg)||((r==19 && sun)&&!judg)||((r==18 && moon)&&sun)){
			theClass.addToChatLog("20");
			arcanaTemp("You will come to terms with your true self","You will have trouble knowing yourself", "Judgement");
			judg = true;
		}
		// THE WORLD!
		else if ((r == 21 && !worl)||((r==20 && judg)&&!worl)||((r==19 && sun)&&judg)){
			theClass.addToChatLog("21");
			arcanaTemp("You will reach great knowledge","You will find yourself empty", "The World");
			worl = true;
		}
	}

	// Minor Arcana
	public  void minorArcana(int r, int s) {

		Random rndm = new Random();
		p = 0 + rndm.nextInt(2);
		if (p == 0)
			theClass.addToChatLog("Upright");
		else
			theClass.addToChatLog("Reversed");

		theClass.addToChatLog("Minor Arcana");
		// Suits
		if (s == 1) {
			theClass.addToChatLog("Swords");
			theClass.addToChatLog("This card has to do with strength and weakness");
			minorMean(r);
		} else if (s == 2) {
			theClass.addToChatLog("Cups");
			theClass.addToChatLog("This card has to do with emotions and relationships");
			minorMean(r);
		} else if (s == 3) {
			theClass.addToChatLog("Wands");
			theClass.addToChatLog("This card has to do with spirit and life");
			minorMean(r);
		} else {
			theClass.addToChatLog("Coins");
			theClass.addToChatLog("This card has to do with wealth");
			minorMean(r);
		}

	}

	// Main Method
	public void runTarotReader() {
		// TODO Auto-generated method stub
		theClass.addToChatLog("Welcome to Tarot Card generator v1.1.5!");
		theClass.addToChatLog("This program uses a modified version of the Rider-Waite Deck");
		Random rndm = new Random();
		//Scanner kbInput = new Scanner(System.in);
		String input = "";
		while(!input.equalsIgnoreCase("no"))
		{
			int y = 31;//number of cards
			while(y>30)
			{
				theClass.addToChatLog("How many cards in your spread?");
				try
				{
					input = theClass.waitForInput();
					y = Integer.parseInt(input);
				}
				catch(NumberFormatException e)
				{
					theClass.addToChatLog("Not a valid number, try again.");
				}
			
			}
			int l;//for loop manager
			int a;//arcana type
			theClass.addToChatLog("This is your fortune.");
			theClass.addToChatLog("If you're confused, ask an expert to explain it for you.");
			theClass.addToChatLog(" ");
			// The meanings are as interpreted by me.

			for (l = 0; l < y; l++) {

				a = 0 + rndm.nextInt(3);
				// Major Arcana
				if (a == 0) {
					r = 0 + rndm.nextInt(22);
					majorArcana(r);
				}
				// Minor Arcana
				else {
					r = 1 + rndm.nextInt(14);
					s = 1 + rndm.nextInt(4);
					minorArcana(r, s);
				}
				theClass.addToChatLog(" ");

			}
			theClass.addToChatLog("Do you want another reading? Yes or no?");
			input = theClass.waitForInput();
		}
		theClass.addToChatLog("Thanks for using this program!");
	}
	
	@Override
	public void run()
	{
		runTarotReader();
	}
}