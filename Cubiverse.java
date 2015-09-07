//  This class file contains: array(s), this, Enum(s), Aggregation, User-defined methods, Conditionals, and Iterations.

import java.util.Random;
public class Cubiverse
{
	private int size = 0;
	private int types = 0;
	private Cube[][][] myCubes;
	private Random rando = new Random();
	private Symbols symbols;
	public int population = 0;
	public Cubiverse(int size, int life, Symbols symbols)
	{
		if (life == 0) life = 1;
		this.size = size;
		this.symbols = symbols;
		int type;
		population = life;
		myCubes = new Cube[size][size][size];
		for (int x = 0; x < myCubes.length; x++){
			for(int y = 0; y < myCubes[x].length; y++){
				for(int z = 0; z < myCubes[x][y].length; z++){
					myCubes[x][y][z] = new Cube(x,y,z,myCubes,this);
		}}}
		for (int x = 0; x < myCubes.length; x++){
			for(int y = 0; y < myCubes[x].length; y++){
				for(int z = 0; z < myCubes[x][y].length; z++){
					myCubes[x][y][z].initNeighbors();
		}}}
		while(life > 0)
		{
			Cube randCube = randomCube();
			if (randCube.getState() == 0)
			{
				randCube.initState(2);
				life -= 1;
			}
		}
	}
	public Cube randomCube()
	{
		int x = rando.nextInt(myCubes.length);
		int y =  rando.nextInt(myCubes[0].length);
		int z = rando.nextInt(myCubes[0][0].length);
		return myCubes[x][y][z];
	}
	public void runGeneration()
	{
		//figure out the next states
		for (int x = 0; x < myCubes.length; x++){
			for(int y = 0; y < myCubes[x].length; y++){
				for(int z = 0; z < myCubes[x][y].length; z++){
					myCubes[x][y][z].live();
		}}}
		//change states to nextState
		for (int x = 0; x < myCubes.length; x++){
			for(int y = 0; y < myCubes[x].length; y++){
				for(int z = 0; z < myCubes[x][y].length; z++){
					myCubes[x][y][z].updateState();
					symbols.output(myCubes[x][y][z].getEvent());
		}}}
	}
	public int getPop()
	{
		return population;
	}
}
