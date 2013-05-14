import java.awt.Color;

class User implements Player{
	
	Color color;
	int numberOfPies;
	boolean hasLost;

	public User(Color color){
		this.color = color;
		numberOfPies = 0;
		hasLost = false;
	}

	public int getNumberOfPies(){
		return numberOfPies;
	}

	public void addPie(){
		numberOfPies++;
	}

	public void removePie(){
		numberOfPies--;
	}

	public Color getColor(){
		return color;
	}

	public void act(){
		//wait for graphical interaction
	}
	
	public boolean hasLost(){
		return hasLost;
	}
	
	public void checkLoss(){
		if(Game.getUnownedPies() == 0 && this.getNumberOfPies() == 0) lose();
		Game.checkGameEnd();
	}
	
	public void unlose(){
		hasLost = false;
		numberOfPies = 0;
	}
	
	private void lose(){
		hasLost = true;
	}
}