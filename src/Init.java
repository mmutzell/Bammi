import java.util.Random;

public class Init {
	
	//kontrollerar vilka siffror som hamnar i den genererade basemapen
	//större tal ger fler celler, decimaltal ökar sannolikheten för 
	//enstaka, mycket små celler.
	private static final double BASEMAP_NUMBER_RANGE = 4.5;
	
	/**
	 * Genererar ett rutnät med referenser till de pajer
	 * som bebor respektive ruta.
	 * 
	 * 
	 */
	public Pie[][] generate(int size, GUI home){
		Pie[][] finalGrid = new Pie[size][size];
		//assign random numbers to make the map from
		int[][] baseGrid = randomGrid(size);
		
		//interpret the random numbers 
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				if(x != 0 && baseGrid[x-1][y] == baseGrid[x][y]) 
					finalGrid[x][y] = finalGrid[x-1][y];
				else if(y != 0 && baseGrid[x][y-1] == baseGrid[x][y])
					finalGrid[x][y] = finalGrid[x][y-1];
				else
					finalGrid[x][y] = new Pie(home);
			}
		}
		
		//Tell them who their neighbours are
		//Assumes addNeighbor method will not add the same pie twice
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				if(y < size - 1) finalGrid[x][y].addNeighbor(finalGrid[x][y+1]);
				if(y > 0) finalGrid[x][y].addNeighbor(finalGrid[x][y-1]);
				if(x < size - 1) finalGrid[x][y].addNeighbor(finalGrid[x+1][y]);
				if(x > 0) finalGrid[x][y].addNeighbor(finalGrid[x-1][y]);
				finalGrid[x][y].removeNeighbor(finalGrid[x][y]);
			}
		}
		
		return finalGrid;
	}
	
	private int[][] randomGrid(int size){
		Random r = new Random();
		int[][] retval = new int[size][size];
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				retval[x][y] = (int) (r.nextDouble()*BASEMAP_NUMBER_RANGE);
			}
		}
		return retval;
	}
}