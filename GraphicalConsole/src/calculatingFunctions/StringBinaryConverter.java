package calculatingFunctions;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import mainFiles.MainClass;
import mainFiles.Program;
/**
 * 
 * @author Luke B
 * @since 2013
 *
 */
public class StringBinaryConverter extends Program
{
	public StringBinaryConverter(MainClass a)
	{
		super(a);
		this.name = "String to Binary Converter";
		this.author = "Luke B";
		this.command = "/sbconverter";
		this.description = "A String to binary/binary to string converter that will also save the output as a text file";
		this.version = "1.0";
	}
	
	
	public void runSBConverter() throws IOException,
	InterruptedException {
		//Scanner kbInput = new Scanner(System.in);
		FileWriter fw = new FileWriter("BinaryString.txt");
		PrintWriter pw = new PrintWriter(fw, true);
		boolean flagExit = true;
		while (flagExit) {
			// Menu
			theClass.addToChatLog("What do you want to do?");
			theClass.addToChatLog("1 Encrypt a string into binary.");
			theClass.addToChatLog("2 Decrypt binary into a string.");
			theClass.addToChatLog("3 Exit.");
			theClass.addToChatLogsl("~ ");
			String decision = theClass.waitForInput();

			// Encryption
			if (decision.equalsIgnoreCase("1")) {
				Thread.sleep(500);
				int symbolArray[] = { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
						42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
						56, 57, 58, 59, 60, 61, 62, 63 };
				theClass.addToChatLog("Enter a string you want to encrypt.");
				theClass.addToChatLogsl("~ ");
				String encryptInput = theClass.waitForInput();
				int[] encryptArray = new int[encryptInput.length()];
				Thread.sleep(500);
				theClass.addToChatLogsl("Your string in binary is: ");
				boolean flagEncrypt = true;
				for (int e = 0; e < encryptInput.length(); e++) {
					flagEncrypt = true;
					encryptArray[e] = encryptInput.charAt(e);

					for (int i = 0; i < 32; i++) {
						if (encryptArray[e] == symbolArray[i]) {
							theClass.addToChatLogsl("0"
									+ Integer.toBinaryString(symbolArray[i]));
							flagEncrypt = false;
						}
					}
					if (flagEncrypt)
						theClass.addToChatLogsl(Integer
								.toBinaryString(encryptArray[e]));
				}

				// Printing to Text File
				theClass.addToChatLog("");
				theClass.addToChatLog("Would you like to save this binary string as a text file? [Y/N]");
				theClass.addToChatLogsl("~ ");
				String txtDecision = theClass.waitForInput();

				if (txtDecision.equalsIgnoreCase("Y")
						|| txtDecision.equalsIgnoreCase("Yes")) {
					Thread.sleep(500);
					boolean flagTxt = true;
					for (int e = 0; e < encryptInput.length(); e++) {
						flagTxt = true;
						encryptArray[e] = encryptInput.charAt(e);
						for (int i = 0; i < 32; i++) {
							if (encryptArray[e] == symbolArray[i]) {
								pw.print("0"
										+ Integer
										.toBinaryString(symbolArray[i]));
								flagTxt = false;
							}
						}
						if (flagTxt)
							pw.print(Integer.toBinaryString(encryptArray[e]));
					}
					fw.close();
					pw.close();
					theClass.addToChatLog("Your string has been saved as BinaryString.txt");
				} else if (txtDecision.equalsIgnoreCase("N")
						|| txtDecision.equalsIgnoreCase("No"))
					Thread.sleep(500);
				else
					Thread.sleep(500);
			}

			// Decryption
			else if (decision.equalsIgnoreCase("2")) {
				Thread.sleep(500);
				theClass.addToChatLog("Enter a string of binary that you want to decrypt.");
				theClass.addToChatLogsl("~ ");
				String decryptInput = theClass.waitForInput();
				boolean flagCatch = true;
				if (decryptInput.length() % 7 == 0) {
					Thread.sleep(500);
					theClass.addToChatLogsl("Your string is: ");
					for (int d = 0; d < decryptInput.length(); d += 7) {
						try {
							String decryptSub = decryptInput
									.substring(d, d + 7);
							int decryptDec = Integer.parseInt(decryptSub, 2);
							theClass.addToChatLogsl(((char) decryptDec) + "");
						} catch (NumberFormatException e) {
							flagCatch = false;
							d = decryptInput.length();
						}

					}
					if (flagCatch == false) {
						theClass.addToChatLog("ERROR");
						theClass.addToChatLogsl("You entered a normal string instead of a binary string.");
					}
				} else {
					theClass.addToChatLog("Your binary string isn't divisible by 7! Please recheck your binary.");
					Thread.sleep(500);
				}
				theClass.addToChatLog("");
			}

			// Exit
			else if (decision.equalsIgnoreCase("3")) {
				flagExit = false;
			}

			// Error
			else {
				theClass.addToChatLog("Silly duck. Try again.");
				Thread.sleep(500);
			}
		}
	}
	
	@Override
	public void run()
	{
		try {
			runSBConverter();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			theClass.addToChatLog("SBCONVERTER HAS CRASHED");
			theClass.addToChatLog("IO EXCEPTION");
		}
	}
}