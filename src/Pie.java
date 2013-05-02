/**
 * A pie to be used in an implementation of the game Bammi.
 * By clicking on the graphical representation of a pie, the
 * game will call the addSlice-method of the corresponding pie.
 */
class Pie{

	Pie[] neighbors; //adjacent pies
	int maxSlices; //Neighbors.length
	int currentSlices;
	Player owner; //Pointer

	public Pie(Pie[] neighbors){
		this.neighbors = neighbors; //Pointer
		maxSlices = neighbors.length;
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

	private void explode(Player player){
		currentSlices = 1;
		for(Pie neighbor :neighbors){
			neighbor.addSlice(player);
		}
		//TODO: graphics
	}
}