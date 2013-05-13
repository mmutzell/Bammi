/**
 * A pie to be used in an implementation of the game Bammi.
 * By clicking on the graphical representation of a pie, the
 * game will call the addSlice-method of the corresponding pie.
 */
class Pie{

	GUI home;
	java.util.HashSet<Pie> neighbors; //adjacent pies
	int maxSlices; //Neighbors.length
	int currentSlices;
	Player owner; //Pointer

	public Pie(GUI home){
		this.home = home;  //Pointer
		neighbors = new java.util.HashSet<Pie>();
		maxSlices = 0;
		currentSlices = 0;
		owner = null;
	}

	public void addSlice(Player player){ //unchecked, optimization possible
		if(currentSlices == maxSlices){
			explode(player);
		}
		else{
			currentSlices++;
			if(owner == null){
				player.addPie();
				owner = player;
			}
			else if(owner != player){ //triggered by explosion
				player.addPie();
				owner.removePie();
				owner = player;
			}
		}
		//TODO: graphics
	}
	
	public void addNeighbor(Pie p){
		if(neighbors.add(p)) maxSlices++;
	}
	
	public void removeNeighbor(Pie p){
		if(neighbors.remove(p)) maxSlices--;
	}
	
	public Player getOwner(){
		return owner;
	}

	private void explode(Player player){
		currentSlices = 1;
		for(Pie neighbor :neighbors){
			neighbor.addSlice(player);
		}
		//TODO: graphics
	}
}
