/**
 * An interface for a player in an implementation of
 * Bammi. Is implemented by User and should be implemented
 * by an AI if one would be coded.
 */ 
interface Player{

	public void addPie();

	public void RemovePie();

	public int getNumberOfPies();
	
	public Color getColor();

	public void act();	
}