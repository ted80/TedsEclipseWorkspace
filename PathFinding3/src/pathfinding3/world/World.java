package pathfinding3.world;

import java.util.ArrayList;
import java.util.Random;

import pathfinding3.ai.Path;

public class World 
{
	private Random rand = new Random();
	
	public static int[][] map = new int[40][40];
	
	public static ArrayList<Path> paths;
	public static boolean[][] used;
	public static boolean calculating;
	public static int calctimer;
	
	public static boolean calculated;
	public static ArrayList<int[]> path;
	
	public static int[] start;
	public static int[] target;
	
	public World()
	{
		createNewWorld();
	}
	
	public void setStartAndTarget()
	{
		start = new int[2];
		target = new int[2];
		
		boolean f1 = false;
		while(!f1)
		{
			int rx = rand.nextInt(40);
			int ry = rand.nextInt(40);
			
			if(map[rx][ry] == 0)
			{
				f1 = true;
				start[0] = rx;
				start[1] = ry;
				break;
			}
		}
		
		boolean f2 = false;
		while(!f2)
		{
			int rx = rand.nextInt(40);
			int ry = rand.nextInt(40);
			
			if(map[rx][ry] == 0 && rx != start[0] && rx != start[1])
			{
				f1 = true;
				target[0] = rx;
				target[1] = ry;
				break;
			}
		}
	}
	
	public void genRandomWorld()
	{
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				if(rand.nextInt(10) == 0)
				{
					map[i][j] = 1;
				}
				else
				{
					map[i][j] = 0;
				}
			}
		}
	}
	
	public void tick()
	{
		calctimer--;
		if(calctimer < 0)
		{
			if(calculating) { calculate(); }
			
			calctimer = 5;
		}
	}
	
	public void createNewWorld()
	{		
		paths = new ArrayList<Path>();
		used = new boolean[40][40];
		calctimer = 0;
		calculated = false;
		calculating = true;
		genRandomWorld();
		setStartAndTarget();
		paths.add(new Path(start[0],start[1]));
	}
	
	public void calculate()
	{
		int pathsSize = paths.size();
		for(int p = 0; p < pathsSize; p++) 
		{
			int count = 0;
			int x = paths.get(p).coordX;
			int y = paths.get(p).coordY;
			
			if(x == target[0] && y == target[1])
			{
				calculating = false;
				calculated = true;
				path = paths.get(p).pathlist;
				break;
			}
			if(x + 1 > -1 && y > -1 && x + 1 < 40 && y < 40)
			{
				if(!used[x + 1][y])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x + 1, y);
					used[x + 1][y] = true;
					paths.add(path);
					count++;
				}
			}
			if(x - 1 > -1 && y > -1 && x - 1 < 40 && y < 40)
			{
				if(!used[x - 1][y])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x - 1, y);
					used[x - 1][y] = true;
					paths.add(path);
					count++;
				}
			}
			if(x > -1 && y + 1 > -1 && x < 40 && y + 1 < 40)
			{
				if(!used[x][y + 1])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x, y + 1);
					used[x][y + 1] = true;
					paths.add(path);
					count++;
				}
			}
			if(x > -1 && y - 1 > -1 && x < 40 && y - 1 < 40)
			{
				if(!used[x][y - 1])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x, y - 1);
					used[x][y - 1] = true;
					paths.add(path);
					count++;
				}
			}
			paths.remove(p);
		}
	}
}
