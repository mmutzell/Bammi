import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

import javax.swing.Icon;


public class PieIcon implements Icon {
	
	private Pie pie;
	private Rectangle bounds;
	
	public PieIcon(Pie p, int size){
		pie = p;
		bounds = new Rectangle(size-1, size-1);
	}

	@Override
	public int getIconHeight() {
		return bounds.height;
	}

	@Override
	public int getIconWidth() {
		return bounds.width;
	}

	@Override
	public void paintIcon(Component c, Graphics gs, int x, int y) {
		Graphics2D g = (Graphics2D) (gs);
		boolean hasOwner = pie.getOwner() != null;
		if(hasOwner){
			g.setColor(pie.getOwner().getColor());
		}
		else{
			g.setColor(Color.white);
		}
		
		if(pie.exploding()){
			g.setColor(new Color(222, 49, 99));
			g.fillOval(0, 0, bounds.height, bounds. width);
			return;
		}
		
		g.fill(bounds);
		g.setColor(Color.white);
		g.fillOval(0, 0, bounds.height, bounds.width);
		int space = pie.maxSlices();
		int painted = pie.currentSlices();
		float degree = (float) (360/space);
		for(int i=0; i<space; i++){
			int type;
			if(space==1){
				type = Arc2D.OPEN;
			}
			else{
				type = Arc2D.PIE;
			}
			Arc2D.Float slice = new Arc2D.Float(bounds, (float)(i*degree+90), degree, type);
			if(hasOwner && painted>0){
				g.setColor(pie.getOwner().getColor());
				g.fill(slice);
				painted--;
			}
			g.setColor(Color.black);
			g.draw(slice);
		}
	}

}
