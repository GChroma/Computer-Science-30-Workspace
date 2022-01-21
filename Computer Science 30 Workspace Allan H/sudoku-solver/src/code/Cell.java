package code;

public class Cell {
	
	public Cell() {
		
	}
	
	public Cell(Cell another) {//clone constructor
		for(int x = 0; x < 10; x++) {
			this.possible[x] = another.canBe(x);
		}
		setBoxID(another.getBoxID());
		this.number = another.getNumber();
		
	}
	private int number;//solved number of cell
	private boolean[] possible = {false, true, true, true, true, true, true, true, true, true};//just use 1 to 9
	
	private int boxID;
	
	public int getBoxID() {
		return boxID;
	}
	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {//set all possibilities other than the number it is to false and the one that it is to be true
		this.number = number;
		for(int i = 1; i < 10; i++) {
			possible[i] = false;
		}
			
		possible[number] = true;
	}
	public boolean[] getPossible() {
		return possible;
	}
	public void setPossible(boolean[] possible) {
		this.possible = possible;
	}
	
	public boolean canBe(int number) {
		return possible[number];
	}
	
	public void cantBe(int number) {
		possible[number] = false;
	}
	
	public int numberOfPotentials() {
		int count = 0;
		for(int i = 1; i < 10; i++)
			if(possible[i])
				count++;
		return count;
	}
	
	public int getFirstPotential() {
		for(int i = 1; i < 10; i++) 
			if(possible[i])
				return i;
		return -1;//return -1 for easier debugging.
		
	}
	
	public int getSecondPotential() {
		int counter = 0;
		for(int i = 1; i < 10; i++) {
			if(possible[i]) {
				if(counter == 1) {
					return i;
				}else {
					counter++;
				}
			}
		}
		return -1;//return -1 for easier debugging.
		
	}
	
	
	//canBe
	//cantBe
	//possibilityNumber
	//getFirstPotential
}
