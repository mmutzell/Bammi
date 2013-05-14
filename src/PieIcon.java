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
	private boolean flash;
	
	public PieIcon(Pie p, int size){
		pie = p;
		bounds = new Rectangle(size-1, size-1);
		flash = true;
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
		g.fill(bounds);
		int space = pie.maxSlices();
		int painted = pie.currentSlices();
		float degree = (float) (360/space);
		for(int i=0; i<space; i++){
			Arc2D.Float slice = new Arc2D.Float(bounds, (float)(i*degree/*+Math.PI/2*/), (float)((i+1)*degree/*+Math.PI/2*/), Arc2D.PIE);
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
