import java.awt.Color;

/**
 * An interface for a player in an implementation of
 * Bammi. Is implemented by User and should be implemented
 * by an AI if one would be coded.
 */ 
interface Player{

	public void addPie();

	public void removePie();

	public int getNumberOfPies();
	
	public Color getColor();
	
	public boolean hasLost();
	
	public void checkLoss();
	
	public void unlose();

	public void act();	
}