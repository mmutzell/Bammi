import java.awt.Color;
/**
 * Colour class for a playable colour.
 * Main purpose is to give acceptable string output;
 * also provides secondary benefits.
 * 
 * @author Raindirve
 */
public class Colour extends Color{
	private static final String[] names = new String[] {"Red","Blue","Green","Purple","Cyan","Brown"};
	private int index;
	public Colour(int i){
		super(i == 0 || i == 3 || i == 5 ? 255 : 0, i == 1 || i == 3 || i == 4 ? 255 : 0, i == 2 || i == 4 || i == 5 ? 255 : 0);
		index = i;
	}
	public String toString(){
		return names[index];
	}
	public int getIndex(){
		return index;
	}
}
