
public class Game {
	
	private static Player[] players;
	private static GUI home;
	private static Pie[][] map;
	private static int size;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		size = 5;
		//map = Initiate.generateMap(size);
		home = new GUI(map, size, players.length);

	}
	

	public static Player getCurrentPlayer(){
		return null;
	}
}
