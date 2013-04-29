class User implements Player{
	
	Color color;
	int numberOfPies;

	public User(Color color){
		this.color = color;
		numberOfPies = 0;
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
}