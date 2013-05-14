import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import javax.swing.Timer;

class ExplosionManager{
	
	private static int flashes = 0;
	private static LinkedList<Pie> exploding = new LinkedList<Pie>();
	private static Timer t = new Timer(200, new ActionListener() {
		public void actionPerformed(ActionEvent e){
			flash();
		}
	});
	
	public void stopTimer(){
		t.stop();
	}

	public static void add(Pie p){
		exploding.add(p);
	}
	
	private static void flash(){
		if(flashes < 4){
			Game.home.repaint();
			flashes++;
		}
		else{
			t.stop();
			exploding.getFirst().explode(exploding.removeFirst().getOwner());
			flashes = 0;
		}
	}

	public static void go(){
		t.start();
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
		t.stop();
	}
}