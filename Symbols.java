//  This class file contains: array(s), this, User-defined methods, Conditionals, Constant(s), and Iterations.

public class Symbols
{
	final int SYMBOL_COUNT = 5;
	boolean[] activeSymbols = new boolean[SYMBOL_COUNT];
	char[] symbolChar = {'-','%','#','@','_'};
	private int eventWait = 0;
	
	public void setAsActive(int symbol)
	{
		switch (symbol)
		{
			case 1:
				activeSymbols[1] = true;
				activeSymbols[2] = true;
				break;
			default:
				activeSymbols[symbol+1] = true;
				break;
		}
	}
	public void setWait(int eventWait)
	{
		this.eventWait = eventWait;
	}
	public char getSymbolChar(int symbol)
	{ 
		return symbolChar[symbol];
	}
	public void output(Event event)
	{
		if (activeSymbols[event.ordinal()] == true)
		{
			System.out.print(symbolChar[event.ordinal()]);
			if (eventWait != 0){
			try{Thread.sleep(eventWait);}
			catch(InterruptedException e) {}}
		}
	}
	public String toString()
	{ 
		String output = "";
		if (activeSymbols[1] == true) output += "GROWTH ";
		if (activeSymbols[3] == true) output += "SPLIT ";
		if (activeSymbols[4] == true) output += "DEATH ";
		return output;
	}
}
