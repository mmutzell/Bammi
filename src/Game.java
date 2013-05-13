
public class Game {
	
	private static Player[] players;
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
	
	public static void newGame(Player[] players){
		this.players = players;
		playerIndex = 0;
	}

	public static Player getCurrentPlayer(){
		return null;
	}
	
	public static void nextPlayer(){
		if(++playerIndex == players.length) playerIndex = 0;
	}
	
	/**
	 * ALWAYS RUN AT START
	 */
	public static void setPlayers(Player[] players){
		this.players = players;
	}
	
}
