package code;
import java.io.File;
import java.util.Scanner;


public class Board {
	private Cell[][] board = new Cell[9][9];//cell array 2d.
	private String level;
	private Cell[][][] boardMem = new Cell[81][9][9];
	private int memSpot = -1;//start at negative 1 because ++ before each guess.
	
	public Board() {
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				//populate the array
				board[x][y] = new Cell();
				board[x][y].setBoxID(3*(x/3) + y/3+1);
			}
		}
	}
	
	public Board(Board another) {//duplicate
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				//copy another board.
				this.board[x][y] = another.board[x][y];
				
			}
		}
	}
	
	public void loadPuzzle(String level) throws Exception
	{
		this.level = level;
		String fileName = "easyPuzzle.txt";
		if(level.contentEquals("medium"))
			fileName = "mediumPuzzle.txt";
		else if(level.contentEquals("hard"))
			fileName = "hardPuzzle.txt";
		
		Scanner input = new Scanner (new File(fileName));
		
		for(int x = 0; x < 9; x++)
			for(int y = 0 ; y < 9; y++)
			{
				int number = input.nextInt();
				if(number != 0)
					solve(x, y, number);
			}
						
		input.close();
		
	}
	
	public void display() {

		for(int x = 0; x < 9; x++) {
			
			for(int y = 0; y < 9; y++) {
				System.out.print(board[x][y].getNumber() + " ");
				if(y == 2) {
					System.out.print("| ");
				}else if(y == 5) {
					System.out.print("| ");
				}
			}
			System.out.println("");
			if(x == 2) {
				System.out.println("---------------------");
			}else if(x == 5) {
				System.out.println("---------------------");
			}
		}
	}
	public boolean[] checkPossibility(int x, int y) {//for debugging.
		System.out.println(board[x][y].numberOfPotentials());
		//System.out.println(board[x][y].getBoxID());
		return board[x][y].getPossible();
	}
	
	public void solve(int x, int y, int number) {
		
		for(int i = 0; i < 9; i++) //turn off for column
			board[i][y].cantBe(number);
		
		for(int i = 0; i < 9; i++) //turn off for row
			board[x][i].cantBe(number);
		
		for(int i = 0; i < 9; i++) {//turn off for box
			for(int j = 0; j < 9; j++) {
				if(board[i][j].getBoxID() == board[x][y].getBoxID()){
					board[i][j].cantBe(number);
				}
			}
		}
		board[x][y].setNumber(number);//resets the possibility on the number itself.
	}
	
	public boolean isSolved() {
		for(int x = 0; x < 9; x++) 
			for(int y = 0; y < 9; y++) 
				if(board[x][y].numberOfPotentials() > 1 || board[x][y].getNumber() == 0) 
					return false;
		
		return true;
	}
	
	public void logicCycles() {
		
		int changesMade = 0;
		
		do {
			changesMade = 0;
			changesMade += logic1();
			changesMade += logic2();
			changesMade += logic3();
			changesMade += logic4();
			
			
		}while(changesMade > 0);
		
		if(!isSolved()) {
			if(errorFound()) {
				//System.out.println("error");
				revert();
			}else {
				guess();
			}	
			
		}
		
	}
	
	public int logic1() {
		int changesMade = 0;
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				if(board[x][y].numberOfPotentials() == 1 && board[x][y].getNumber() == 0) {
					solve(x, y, board[x][y].getFirstPotential());
					changesMade++;
				}
			}
		}
		return changesMade;
	}
	public int logic2() {
		int changesMade = 0;
		//do rows first.
		
		for(int x = 0; x < 9; x++) {//loop through columns (y axis)
			int[] possibles = new int[10];//using index 1 to 9
			for(int y = 0; y < 9; y++) {//loop through row to find if the row has a possible for only 1. (x axis)
				for(int i = 1; i < 10; i++) {//loop through possible array
					if(board[x][y].canBe(i)) {//if a number possible add one to possibles array in the corresponding index.
						possibles[i]++;//if there is only one in the row the possibles value will be 1.
						
						
					}
				}
			}
			for(int i = 1; i < 10; i++) {//loop through possibles to find 1s.			
				if(possibles[i] == 1) {
					
					for(int y = 0; y < 9; y++) {//loop through row and check for number and put it in.
						if(board[x][y].canBe(i) && board[x][y].getNumber() == 0) {//check correct possible number and solved or not.
							solve(x, y, i);
							changesMade++;
						}
					}
				}
			}
		}//end of checking rows.
		
		
		for(int y = 0; y < 9; y++) {//loop through rows (x axis)
			int[] possibles = new int[10];//using index 1 to 9
			for(int x = 0; x < 9; x++) {//loop through column to find if the column has a possible for only 1. (x axis)
				for(int i = 1; i < 10; i++) {//loop through possible array
					if(board[x][y].canBe(i)) {//if a number possible add one to possibles array in the corresponding index.
						possibles[i]++;//if there is only one in the row the possibles value will be 1.
						
						
					}
				}
			}
			for(int i = 1; i < 10; i++) {//loop through possibles to find 1s.			
				if(possibles[i] == 1) {
					
					for(int x = 0; x < 9; x++) {//loop through column and check for number and put it in.
						if(board[x][y].canBe(i) && board[x][y].getNumber() == 0) {//check correct possible number and solved or not.
							solve(x, y, i);
							changesMade++;
						}
					}
				}
			}
		}//end of checking columns
		
		
		
		return changesMade;
	}
	public int logic3() {
		int changesMade = 0;
		
		for(int boxNum = 1; boxNum < 10; boxNum++) {//repeat for each box.
			
			int[] possibles = new int[10];//using index 1 to 9
			for(int x = 0; x < 9; x++) {//loop through columns (y axis)	
				for(int y = 0; y < 9; y++) {//loop through rows (x axis)		
					for(int i = 1; i < 10; i++) {//loop through possible array and check with each address.
						if(board[x][y].getPossible()[i] && board[x][y].getBoxID() == boxNum) {//if a number is possible add one to possibles array in the corresponding index.
							possibles[i]++;//if there is only one in the box the possibles value will be 1.
						}
					}
				}
				
					
			}//end setting possibles
			
			for(int i = 1; i < 10; i++) {
				if(possibles[i] == 1) {//if there is only one then loop through the whole array
					for(int x = 0; x < 9; x++) {//loop through columns (y axis)	
						for(int y = 0; y < 9; y++) {//loop through rows (x axis)
							if(board[x][y].canBe(i) && board[x][y].getNumber() == 0 && board[x][y].getBoxID() == boxNum) {
								solve(x, y, i);
								changesMade++;
							}
						}
					}		
				}	
			}
		}
		
		return changesMade;
	}
	public int logic4() {

		int changesMade = 0;
		//only for rows:
		for(int x = 0; x < 9; x++) {//loop through column
			for(int y = 0; y < 9; y++) {//loop through row
				
				if(board[x][y].numberOfPotentials() == 2) {//found one to check with another
					for(int i = (y + 1); i < 9; i++) {//loop through row from y
						if(board[x][i].numberOfPotentials() == 2) {//if not equal to first one and only 2 potentials
							
							if(board[x][y].getFirstPotential() == board[x][i].getFirstPotential() && board[x][y].getSecondPotential() == board[x][i].getSecondPotential()) {
								//System.out.println("logic 4 brr");
								for(int k = 0; k < 9; k++) {//loop through row again to turn off possibles.
									if(k != y && k != i) {//if not the pair then turn possible off.
										if(board[x][k].canBe(board[x][y].getFirstPotential())) {
											board[x][k].cantBe(board[x][y].getFirstPotential());
											changesMade++;
										}
										if(board[x][k].canBe(board[x][y].getSecondPotential())) {
											board[x][k].cantBe(board[x][y].getSecondPotential());
											changesMade++;
	
										}
									}
								}
							}		
						}	
					}
				}
			}
		}	
		//only for columns:
		for(int y = 0; y < 9; y++) {//loop through row
			for(int x = 0; x < 9; x++) {//loop through column
				
				if(board[x][y].numberOfPotentials() == 2) {//found one to check with another
					for(int i = (x + 1); i < 9; i++) {//loop through column from x
						if(board[i][y].numberOfPotentials() == 2) {//if not equal to first one and only 2 potentials
							
							if(board[x][y].getFirstPotential() == board[i][y].getFirstPotential() && board[x][y].getSecondPotential() == board[i][y].getSecondPotential()) {
								//System.out.println("logic 4 brr");
								for(int k = 0; k < 9; k++) {//loop through column again to turn off possibles.
									if(k != x && k != i) {//if not the pair then turn possible off.
										if(board[k][y].canBe(board[x][y].getFirstPotential())) {
											board[k][y].cantBe(board[x][y].getFirstPotential());
											changesMade++;
										}
										if(board[k][y].canBe(board[x][y].getSecondPotential())) {
											board[k][y].cantBe(board[x][y].getSecondPotential());
											changesMade++;
	
										}
									}
								}
							}		
						}	
					}
				}
			}
		}	
		//for boxes
		for(int x = 0; x < 9; x++) {//loop through column
			for(int y = 0; y < 9; y++) {//loop through row
				
				if(board[x][y].numberOfPotentials() == 2) {//found one to check with another
					for(int i = 0; i < 9; i++) {//loop through rows again
						for(int j = 0; j < 9; j++) {//loop through columns again
							if(board[i][j].numberOfPotentials() == 2 && board[i][j].getBoxID() == board[x][y].getBoxID() && i != x && j != y) {//if not equal to first one and only 2 potentials
							
								if(board[x][y].getFirstPotential() == board[i][j].getFirstPotential() && board[x][y].getSecondPotential() == board[i][j].getSecondPotential()) {
									for(int a = 0; a < 9; a++) {//loop through row again to turn off possibles.
										for(int b = 0; b < 9; b++) {
											if(board[x][y].getBoxID() == board[a][b].getBoxID()) {
												if(a != x && b != y) {//check if it is not board[x][y]
													if(a != i && b != j) {//check it is not board[i][j]
														if(board[a][b].canBe(board[x][y].getFirstPotential())) {
															board[a][b].cantBe(board[x][y].getFirstPotential());
															changesMade++;
														}
														if(board[a][b].canBe(board[x][y].getSecondPotential())) {
															board[a][b].cantBe(board[x][y].getSecondPotential());
															changesMade++;
					
														}
													}
												}
											}	
										}
									}
								}		
							}
						}
							
					}
				}
			}
		}	
		
		
		return changesMade;
		
	}
	public void guess() {
		
		memSpot++;
		
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				boardMem[memSpot][x][y] = new Cell(board[x][y]);
			}
		}
			
		boolean guessed = false;
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				if(board[x][y].numberOfPotentials() > 1) {
					//System.out.println(x + " " + y + " GUESS");
					solve(x, y, board[x][y].getFirstPotential());
					logicCycles();
					if(!isSolved())
						board[x][y].cantBe(board[x][y].getFirstPotential());
					guessed = true;
					break;
				}
			}
			if(guessed) {
				break;
			}
		}
		logicCycles();
		
	}
	
	public void revert() {
		
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				board[x][y] = new Cell(boardMem[memSpot][x][y]);
			}
		}
		memSpot--;
		
	}
	public boolean errorFound() {
		boolean error = false;
		for(int x = 0; x < 9; x++) 
			for(int y = 0; y < 9; y++)
				if(board[x][y].numberOfPotentials() == 0) {
					error = true;
					//System.out.println(x + " " + y);
				}
					
		if(error)
			return true;
		return false;
	}
}
