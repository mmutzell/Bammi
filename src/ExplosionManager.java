import java.util.*;
import java.lang.*;

class ExplosionManager{
	
	
	private static LinkedList<Pie> exploding = new LinkedList<Pie>();

	public static void add(Pie p){
		exploding.add(p);
	}

	public static void go(){
		while(exploding.size() > 0){
			for(int i=0; i<4; i++){
				try {
					Thread.currentThread();
					Thread.sleep(100);
					Game.home.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			exploding.getFirst().explode(exploding.removeFirst().getOwner());
		}
	}
	
	/**
	 * Flushes the list to stop impending explosions.
	 */
	public static void flush(){
		//Stops any impending explosions by adding
		//a dummy list with a dummy pie.
		exploding = new LinkedList<Pie>(){
			@Override
			public Pie removeFirst(){
				return getFirst();
			}
		};
		Pie pie = new Pie(Game.home){
			@Override
			public void explode(Player p){
			}
		};
		
	}
	
	public static void reset(){
		exploding = new LinkedList<Pie>();
	}
}