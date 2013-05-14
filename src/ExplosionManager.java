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
					Thread.sleep(500);
					Game.home.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			exploding.getFirst().explode(exploding.removeFirst().getOwner());
		}
	}
}