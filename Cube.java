//  This class file contains: array(s), this, Enum(s), User-defined methods, Conditionals, and Iterations.

public class Cube
{
	private int state = 0;
	private int nextState = 0;
	private int type = 0;
	private int x,y,z;
	private Cube[] neighbors = new Cube[6];
	private Cube[][][] myCubes;
	private Event myEvent = Event.NONE;
	private Cubiverse myVerse;
	
	public Cube(int x, int y, int z, Cube[][][] myCubes,Cubiverse myVerse)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.myCubes = myCubes;
		this.myVerse = myVerse;
	}
	public void initNeighbors()
	{
		Cube neighbor;
		if (y - 1 >= 0){
			neighbor = myCubes[x][y-1][z];
			setNeighbor(Position.ABOVE,neighbor);}
		if (y + 1 < myCubes.length){
			neighbor = myCubes[x][y+1][z];
			setNeighbor(Position.BELOW,neighbor);}
		if (x - 1 >= 0){
			neighbor = myCubes[x-1][y][z];
			setNeighbor(Position.LEFT,neighbor);}
		if (x + 1 < myCubes.length){
			neighbor = myCubes[x+1][y][z];
			setNeighbor(Position.RIGHT,neighbor);}
		if (z - 1 >= 0){
			neighbor = myCubes[x][y][z-1];
			setNeighbor(Position.LEFT,neighbor);}
		if (z + 1 < myCubes.length){
			neighbor = myCubes[x][y][z+1];
			setNeighbor(Position.RIGHT,neighbor);}
	}
	public void live()
	{
		//what happens?
		int split = 0;
		switch(state)
		{
		case 0:
			myEvent = Event.NONE;
			break;
		case 1:
			nextState++;
			myEvent = Event.GROW1;
			break;
		case 2:
			nextState++;
			myEvent = Event.GROW2;
			break;
		case 3:
			split = split();
			if (split == 2)
			{
				nextState = -1;
				myEvent = Event.DIE;
				myVerse.population--;
			}
			else
			{
				nextState = split + 1;
				myEvent = Event.SPLIT;
			}
			break;
		}
	}
	public int split()
	{
		int split = 2;
		double d = Math.floor(Math.random() * 5);
		int i = (int)d;
		int start = i;
		do
		{
			if (neighbors[i] != null)
			{
				if (neighbors[i].getState() == 0 && neighbors[i].getNextState() == 0)
				{
					neighbors[i].setNextState(1);
					split--;
					myVerse.population++;
				}
			}
			if (i == 5) i = 0;
			else i++;
		}
		while(i != start);
		return split;
	}
	public void setNeighbor(Position position, Cube neighbor)
	{
		neighbors[position.ordinal()] = neighbor;
	}
	public Cube getNeighbor(Position position)
	{
		return neighbors[position.ordinal()];
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public void initState(int state)
	{
		this.state = state;
		setNextState(state);
	}
	public void setNextState(int state)
	{
		nextState = state;
	}
	public void updateState()
	{
		if (nextState == -1) nextState = 0;
		if (nextState != state) setState(nextState);
	}
	public int getState()
	{
		return state;
	}
	public int getNextState()
	{
		return nextState;
	}
	public Event getEvent()
	{
		return myEvent;
	}
}