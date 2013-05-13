import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Handles the drawing and interaction with the gamefield
 * in Sean Wenstr�ms and Martin M�tzells implementation of
 * Bammi.
 * @author Martin M�tzell
 *
 */
public class GameField extends JPanel {

	private Pie[][] map;
	private HashMap<Pie, graphicalPie> gPies;
	private int size, pieSize;
	
	/**
	 * Does not contain actual coordinates,
	 * but rather a position in the grid of map.
	 */
	private class coordinate{
		int x, y;
		public coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * The visual representation of a Pie can
	 * be accessed via this.
	 */
	private class graphicalPie{
		JButton button;
		coordinate coordinate;
		public graphicalPie(JButton b, coordinate c){
			button = b;
			coordinate = c;
		}
	}
	
	/**
	 * Creates a new GameField.
	 * @param map
	 * @param size
	 * @param pieSize
	 */
	public GameField(Pie[][] map, int size, int pieSize){
		super();
		setLayout(null);	//every component needs to be placed manually
		this.map = map;
		this.size = size;
		this.pieSize = pieSize;
		
		HashMap<Pie, ArrayList<coordinate>> pieAreas = new HashMap<Pie, ArrayList<coordinate>>();
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				Pie currentPie = map[i][j];
				if(!pieAreas.containsKey(currentPie)){
					pieAreas.put(currentPie, new ArrayList<coordinate>());
				}
				pieAreas.get(currentPie).add(new coordinate(i, j));
			}
		}
		gPies = new HashMap<Pie, graphicalPie>(pieAreas.size());
		for(ArrayList<coordinate> c : pieAreas.values()){
			coordinate cd = c.get((int) (Math.random()*c.size()));
			final Pie p = map[cd.x][cd.y];
			graphicalPie gp = new graphicalPie(new JButton(new PieIcon()), cd);
			gPies.put(p, gp);
			gPies.get(p).button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					p.addSlice(Game.getCurrentPlayer());
				}
			});
			int border = pieSize/10;
			gp.button.setBounds(gp.coordinate.x+border, gp.coordinate.y+border, pieSize-2*border, pieSize-2*border);
			add(gp.button);
		}
	}
	
	/**
	 * Updates the visuals of the gamefield.
	 */
	@Override
	protected void paintComponent(Graphics g){
		//colors the field accordingly to the gamestate
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(map[i][j].getOwner() == null){
					g.setColor(Color.white);
				}
				else{
					g.setColor(map[i][j].getOwner().getColor());
				}
				g.fillRect(i*pieSize, j*pieSize, pieSize, pieSize);
			}
		}
		
		//draws the borders differentiating the pie areas
		g.setColor(Color.black);
		for(int i=0; i<size-1; i++){
			for(int j=0; j>size-1; j++){
				Pie currentPie = map[i][j];
				if(currentPie != map[i+1][j]){
					g.drawLine((i+1)*pieSize, j*pieSize, (i+1)*pieSize, (j+1)*pieSize);
				}
				if(currentPie != map[i][j+1]){
					g.drawLine(i*pieSize, (j+1)*pieSize, (i+1)*pieSize, (j+1)*pieSize);
				}
			}
		}
		
		//Paints the Pies
		for(graphicalPie gp : gPies.values()){
			gp.button.repaint();
		}
	}
}