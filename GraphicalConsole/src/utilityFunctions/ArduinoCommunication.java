package utilityFunctions;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;

import mainFiles.MainClass;
import mainFiles.Program;


public class ArduinoCommunication extends Program implements SerialPortEventListener {
	public ArduinoCommunication(MainClass a) {
		super(a);
		this.name = "Testing program";
		this.command = "/arduino";
		this.author = "Ian Davila";
		this.description = "An Arduino test program";
	}

	SerialPort serialPort;
        /** The port we're normally going to use. */
	
	private static ArduinoInfo infoObject = new ArduinoInfo();
	
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
                        "/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM3", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
                // the next line is for Raspberry Pi and 
                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
//                System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
		infoObject.setDeviceID("123456");
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			//theClass.addToChatLog(currPortId.getName());
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}

			}
		}
		if (portId == null) {
			theClass.addToChatLog("Could not find COM port.");
//			theClass.addToChatLog("Defaulting to COM3");
//			portId = "COM3";
//			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		Scanner processor;
		String temp = "";
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				theClass.addToChatLog("New data recieved");
				theClass.addToChatLog(inputLine);
				processor = new Scanner(inputLine);
				processor.useDelimiter(";");
				temp = processor.next();
				if(infoObject.getDeviceID().equalsIgnoreCase("123456"))
				{
					temp = processor.next();
					infoObject.setLat(Float.parseFloat(temp));
					temp = processor.next();
					infoObject.setLon(Float.parseFloat(temp));
					theClass.addToChatLog("Lat: "+infoObject.getLat());
					theClass.addToChatLog("Lon: " + infoObject.getLon());
				}
//				theClass.addToChatLog(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	@Override
	public void run() {
		//ArduinoCommunication main = new ArduinoCommunication();
		this.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		theClass.addToChatLog("Started");
	}
}
