import java.util.ArrayList;


public class Game {
	
	private static ArrayList<Player> players;
	private static int playerIndex = 0;
	private static GUI home;
//	private static Pie[][] map;
//	private static int size;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		//size = 5;
		//map = Initiate.generateMap(size);
		
		home = new GUI();

	}
	
//	public Game(Player[] p, )
	
	public static void newGame(ArrayList<Player> p){
		players = p;
		playerIndex = 0;
		
	}

	public static Player getCurrentPlayer(){
		return players.get(playerIndex);
	}
	
	public static void nextPlayer(){
		if(++playerIndex == players.size()) playerIndex = 0;
	}
	
	/**
	 * ALWAYS RUN AT START
	 */
	public static void setPlayers(ArrayList<Player> p){
		players = p;
	}
	
}
