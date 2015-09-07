// DEMO FOR CUBIVERSE \\
//  This class file contains: array(s), File I/O, User-defined methods, Conditionals, Constant(s), and Iterations.
import java.io.*;
import java.util.Scanner;
public class Demo
{
	//saves to txt
	public static void save(Object[] stored) throws IOException
	{
		File file = new File("settings.txt");
		PrintWriter outputFile = new PrintWriter(file);
		for (int i = 0; i < stored.length; i++) if (stored[i] != null) outputFile.println(stored[i]);
		outputFile.close();
		System.out.println("Settings saved to file.");
	}
	//loads from txt
	public static void load(File file, Object[] stored) throws IOException
	{
		Scanner inputFile = new Scanner(file);
		int i = 0;
		String cubiverseName = inputFile.nextLine();
		stored[i] = cubiverseName;
		i++;
		String line;
		if(inputFile.hasNext())
		{	
			line = inputFile.nextLine();
			Integer size = new Integer(Integer.parseInt(line));
			stored[i] = size;
			i++;
		}
		if(inputFile.hasNext())
		{	
			line = inputFile.nextLine();
			Double life = new Double(Double.parseDouble(line));
			stored[i] = life;
			i++;
		}
		if(inputFile.hasNext())
		{	
			line = inputFile.nextLine();
			Integer cycles = new Integer(Integer.parseInt(line));
			stored[i] = cycles;
			i++;
		}
		if(inputFile.hasNext())
		{	
			line = inputFile.nextLine();
			Integer genWait = new Integer(Integer.parseInt(line));
			stored[i] = genWait;
			i++;
		}
		if(inputFile.hasNext())
		{	
			line = inputFile.nextLine();
			Integer eventWait = new Integer(Integer.parseInt(line));
			stored[i] = eventWait;
			i++;
		}
		inputFile.close();
	}
	public static char readChar() throws IOException
	{
		char input = (char)System.in.read();
		//eat the \n and \r
		System.in.read();
		System.in.read();
		return Character.toUpperCase(input);
	}
	public static void errorExit(Object[] stored) throws IOException
	{
		System.out.println("Warning: This will overwrite any existing settings."+
					"\nWould you like to save your settings? Respond with Y/N");
		char input = readChar();
		if (input == 'Y') save(stored);
		System.out.println("Exiting SIMULATION.");
		System.exit(0);
	}
	public static void errorExit()
	{
		System.out.println("Exiting SIMULATION.");
		System.exit(0);
	}
	
	//int input
	public static boolean inputValidate(int input, int min, int max)
	{
		boolean solid = true;
		if (input < min) solid = false;
		else if (input > max) solid = false;
		if (solid == false)
		{
			System.out.println("Input was out of the accepted range."+
				"\nThe value should range from "+min+" to "+max+".");
		}
		return solid;
	}
	
	//double input
	public static boolean inputValidate(double input, double min, double max)
	{
		boolean solid = true;
		if (input < min) solid = false;
		else if (input > max) solid = false;
		if (solid == false)
		{
			System.out.println("Input was out of the accepted range."+
				"\nThe value should range from "+min+" to "+max+".");
		}
		return solid;
	}
	
	//string input
	public static boolean inputValidate(String input)
	{
		boolean solid = true;
		if (input.equals("")) solid = false;
		if (solid == false)
		{
			System.out.println("Input was out of the accepted range."+
				"\nThis input must be at least 1 character.");
		}
		return solid;
	}
	
	//MAIN METHOD IS HERE
	public static void main(String[] args) throws IOException
	{
		Symbols symbols = new Symbols();
		Scanner keyboard = new Scanner(System.in);
		final int TOTAL_INPUT = 9;
		final int SYMBOL_OFFSET = 5;
		int tries = 0;
		Object[] stored = new Object[TOTAL_INPUT];
		String cubiverseName = "";
		int size = 0,cycles = 0,genWait = 0,eventWait = 0;
		double life = 0;
		boolean loaded = false;
		
		//File Input - check for file and load
		File existingFile = new File("settings.txt");
		if (existingFile.exists() && existingFile.length() != 0)
		{
			System.out.println("There is an existing file containing settings from a previous attempt. "+
				"Would you like to load? Respond with Y/N");
			char input = readChar();
			if (input == 'Y') load(existingFile,stored);
			System.out.println("\n");
			loaded = true;
		}
		//NAME
		if (stored[0] == null)
		{
		System.out.println("Please name your CUBIVERSE...");
		cubiverseName = keyboard.nextLine();
		while(inputValidate(cubiverseName) == false){
			if (tries == 2) errorExit();cubiverseName = keyboard.nextLine();tries += 1;}
		tries = 0;
		stored[0] = cubiverseName;
		}
		else cubiverseName = stored[0].toString();
		
		//SIZE
		if (stored[1] == null)
		{
		System.out.println("How big is "+ cubiverseName+"?");
		System.out.println("2 - 100 cubes tall/wide/deep");
		System.out.println("Larger CUBIVERSES will take exponentially longer to simulate.");
		size = keyboard.nextInt();
		while(inputValidate(size,2,100) == false){
			if (tries == 2) errorExit(stored);size = keyboard.nextInt();tries += 1;}
		tries = 0;
		stored[1] = new Integer(size);
		}
		else size = (int)stored[1];
		
		//LIFE
		if (stored[2] == null)
		{
		System.out.println("What percentage of cubes in " + cubiverseName +" start with life?");
		System.out.println("0.1 - 100%");
		life = keyboard.nextDouble();
		while(inputValidate(life,0.1,100) == false){
			if (tries == 2) errorExit(stored);life = keyboard.nextDouble();tries += 1;}
		tries = 0;
		stored[2] = new Double(life);
		}
		else life = (double)stored[2];
		life /= 100;
		life *= (Math.round(Math.pow(size,3)));
		//intialize cubiverse
		Cubiverse myCubiverse = new Cubiverse(size,(int)life,symbols);
		
		//GENERATIONS
		if (stored[3] == null)
		{
		System.out.println("How many generations would you like to simulate?");
		System.out.println("1 - 999,999,999 generations");
		System.out.println("The more generations the longer the simulation will take.");
		cycles = keyboard.nextInt();
		while(inputValidate(cycles,1,999999999) == false){
			if (tries == 2) errorExit(stored);cycles = keyboard.nextInt();tries += 1;}
		tries = 0;
		stored[3] = new Integer(cycles);
		}
		else cycles = (int)stored[3];
		
		//GEN PAUSING
		if (stored[4] == null)
		{
		System.out.println("How long should I pause between generations?");
		System.out.println("0 - 60000 milliseconds");
		genWait = keyboard.nextInt();
		while(inputValidate(genWait,0,60000) == false){
			if (tries == 2) errorExit(stored);genWait = keyboard.nextInt();tries += 1;}
		tries = 0;
		stored[4] = new Integer(genWait);
		}
		else genWait = (int)stored[4];
		
		//EVENT PAUSING
		if (stored[5] == null)
		{
		System.out.println("How long should I pause between events?");
		System.out.println("0 - 4000 milliseconds\n"
		+ "WARNING: Pausing between events drastically increases simulation time.");
		eventWait = keyboard.nextInt();
		while(inputValidate(eventWait,0,4000) == false){
			if (tries == 2) errorExit(stored);eventWait = keyboard.nextInt();tries += 1;}
		tries = 0;
		symbols.setWait(eventWait);
		stored[5] = new Integer(eventWait);
		}
		else eventWait = (int)stored[5];
		
		for(int i = 6; i < 9; i++) if (stored[i] != null) symbols.setAsActive((int)stored[i]);
		
		//SYMBOLS
		System.out.println("Time to select which events you would like to see.");
		System.out.println("During each generation, symbols will be printed to the console when certain events occur.");
		System.out.println("WARNING: Choosing more symbols will increase simulation time.");
		System.out.println("Input 1 to enable GROWTH symbols. This is usually the most common event."+
		"\nGROWTH will display as "+ symbols.getSymbolChar(1) + " and " +symbols.getSymbolChar(2));
		System.out.println("Input 2 to enable SPLIT symbols." +
		"\nSPLIT will display as "+ symbols.getSymbolChar(3));
		System.out.println("Input 3 to enable DEATH symbols." +
		"\nDEATH will display as "+ symbols.getSymbolChar(4));
		System.out.println("Input 0 when finished.");
		boolean choosing = true;
		int symbol = 0;
		while(choosing == true)
		{
			symbol = keyboard.nextInt();
			while(inputValidate(symbol,0,3) == false){
				if (tries == 2) errorExit(stored);symbol = keyboard.nextInt();tries += 1;}
			tries = 0;
			if (symbol == 0) choosing = false;
			else
			{
				symbols.setAsActive(symbol);
				stored[symbol+SYMBOL_OFFSET] = new Integer(symbol);
				keyboard.nextLine();
			}
			System.out.println("These symbols are active: " + symbols);
		}
		
		//HERE IS WHERE STUFF ACTUALLY HAPPENS!!!
		for(int i = 0; i < cycles; i++)
		{
			
			System.out.println("\n\t" + cubiverseName + " Generation: " + (i+1));
			System.out.println("\tPopulation:" + myCubiverse.getPop());
			System.out.println("\n");
			if (myCubiverse.getPop() == 0)
			{
				System.out.println(cubiverseName + " is empty...");
					errorExit();
			}
			if(genWait !=0){
			try{Thread.sleep(genWait);}
			//this should never happen!
			catch(InterruptedException e) {}}
			myCubiverse.runGeneration();
			
		}
		System.out.println("\n\tPopulation:" + myCubiverse.getPop());
		System.out.println("Simulation is finished.");
		//clear the file if the data was loaded
		if (loaded)
		{
			File file = new File("settings.txt");
			PrintWriter outputFile = new PrintWriter(file);
		}
	}
}
	