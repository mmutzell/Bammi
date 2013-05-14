import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Handles the drawing and interaction with the gamefield
 * in Sean Wenströms and Martin Mützells implementation of
 * Bammi.
 * @author Martin Mützell
 */
public class GameField extends JPanel {

	private Pie[][] map;
	private JPanel[][] grid;
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
		setLayout(new GridBagLayout());
		setSize(new Dimension(size*pieSize, size*pieSize));
		setBackground(Color.white);
		this.map = map;
		this.size = size;
		this.pieSize = pieSize;
		this.grid = new JPanel[size][size];
		
		HashMap<Pie, ArrayList<coordinate>> pieAreas = new HashMap<Pie, ArrayList<coordinate>>();
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = i;
				c.gridy = j;
				//c.insets = new Insets(1, 1, 1, 1);
				Pie currentPie = map[i][j];
				grid[i][j] = new JPanel();
				grid[i][j].setPreferredSize(new Dimension(pieSize, pieSize));
				add(grid[i][j], c);
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
			int border = pieSize/10;
			graphicalPie gp = new graphicalPie(new JButton(new PieIcon(p, pieSize-2*border)), cd);
			gPies.put(p, gp);
			gPies.get(p).button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					p.addSlice(Game.getCurrentPlayer());
				}
			});
			gp.button.setFocusPainted(false);
			gp.button.setMargin(new Insets(0, 0, 0, 0));
			gp.button.setContentAreaFilled(false);
			gp.button.setBorderPainted(false);
			gp.button.setOpaque(false);
			gp.button.setPreferredSize(new Dimension(pieSize-2*border, pieSize-2*border));
			grid[gp.coordinate.x][gp.coordinate.y].add(gp.button);
		}
	}
	
	/**
	 * Updates the visuals of the gamefield.
	 */
	@Override
	protected void paintComponent(Graphics gr){
		Graphics2D g = (Graphics2D) (gr);
		super.paintComponent(g);
		//colors the field accordingly to the gamestate
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(map[i][j].getOwner() == null){
					grid[i][j].setBackground(Color.white);
				}
				else{
					grid[i][j].setBackground(map[i][j].getOwner().getColor());
				}
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
