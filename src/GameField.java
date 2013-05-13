import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GameField extends JPanel {

	private Pie[][] map;
	private graphicalPie[] gPies;
	private int size, pieSize;
	
	private class coordinate{
		int x, y;
		public coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	private class graphicalPie{
		Pie pie;
		JButton button;
		coordinate coordinate;
		public graphicalPie(Pie p, JButton b, coordinate c){
			pie = p;
			button = b;
			coordinate = c;
		}
	}
	
	public GameField(Pie[][] map, int size, int pieSize){
		super();
		setLayout(null);
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
		gPies = new graphicalPie[pieAreas.size()];
		int i = 0;
		for(ArrayList<coordinate> c : pieAreas.values()){
			coordinate cd = c.get((int) (Math.random()*c.size()));
			final Pie p = map[cd.x][cd.y];
			gPies[i] = new graphicalPie(p, new JButton(new PieIcon()), cd);
			gPies[i].button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					p.addSlice(Game.getCurrentPlayer());
				}
			});
			i++;
		}
	}
	
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
		
		//Places the Pies
		for(final graphicalPie gp : gPies){
			int border = pieSize/10;
			gp.button.setBounds(gp.coordinate.x+border, gp.coordinate.y+border, pieSize-2*border, pieSize-2*border);
			add(gp.button);
			gp.button.repaint();
		}
	}
}
