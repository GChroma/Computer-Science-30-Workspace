package code;

public class Astar {
	
	private static int gridSize = 10;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LinkedGrid LG = new LinkedGrid(gridSize);
		LG.placeStartAndEnd();
		LG.diagonalConnect();
		//LG.sethScores();
		//LG.displayhScores();
		LG.displayColour();
		pathFinder(LG);
		LG.setPath();
		LG.displayColour();
		
		
		
	}
	
	static void pathFinder(LinkedGrid LG) {
		Node[] open = new Node[gridSize*gridSize];
		Node[] closed = new Node[gridSize*gridSize];
		open[0] = LG.findStart();
		open[0].setgScore(0);
		LG.sethScore(open[0]);
		int openCounter = 1;//next spot where one can be placed.
		int closeCounter = 0;
		boolean ended = false;
		
		while(!ended) {
			//System.out.println(open[0].getfScore());
			Node current = open[0];
			
			closed[closeCounter] = current;
			closeCounter++;
			openCounter--;
			remove(0, openCounter, open);
			
			
			if(current.isEnd()) {
				ended = true;
			}
		//	System.out.println("openCounter: " + openCounter + " Closed Counter: " + closeCounter);
			Node topLeft = current.getTopLeft();
			openCounter = openCounter + open(openCounter, 14, LG, open, closed, current, topLeft);
			Node topRight = current.getTopRight();
			openCounter = openCounter + open(openCounter, 14, LG, open, closed, current, topRight);
			Node bottomRight = current.getBottomRight();
			openCounter = openCounter + open(openCounter, 14, LG, open, closed, current, bottomRight);
			Node bottomLeft = current.getBottomLeft();
			openCounter = openCounter + open(openCounter, 14, LG, open, closed, current, bottomLeft);
			Node up = current.getUp();
			openCounter = openCounter + open(openCounter, 10, LG, open, closed, current, up);
			Node right = current.getRight();
			openCounter = openCounter + open(openCounter, 10, LG, open, closed, current, right);
			Node left = current.getLeft();
			openCounter = openCounter + open(openCounter, 10, LG, open, closed, current, left);
			Node down = current.getDown();
			openCounter = openCounter + open(openCounter, 10, LG, open, closed, current, down);
			//System.out.println("Nodes Added.");
			//LG.displayColour();
			/*for(int i = 0; i < openCounter; i++) {
				System.out.println(open[i].getfScore());
			}*/
		}
		System.out.println("Path Found");
	}	
	
	static int open(int openCounter, int distance, LinkedGrid LG, Node[] open, Node[] closed, Node current, Node target) {
		if(!inArray(closed, target) && target != null && target.isPassable()) {
			LG.sethScore(target);
			if(target.getfScore() >= (target.gethScore() + (current.getgScore() + distance)) || target.getgScore() == 0) {
				target.setgScore(current.getgScore() + distance);
				target.setPrev(current);
				if(!inArray(open, target)) {
					insertNode(open, target, openCounter);
					target.setOpen(true);
					return 1;
				}
			}
		}
		return 0;
	}
	
	static void remove(int index, int openCounter, Node[] open) {
		open[index] = null;
		for(int i = index; i < openCounter; i++) {
			open[i] = open[i + 1];
		}
	}
	
	static Node[] insertNode(Node[] array, Node temp, int openCounter) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				//System.out.println("array[" + i + "] is replaced. ");
				array[i] = temp;
				break;
			}else if(array[i].getfScore() > temp.getfScore()) {
				//System.out.println("array[" + i + "] is replaced. ");
				swapUp(array, i, openCounter);
				array[i] = temp;
				break;
			}else if(array[i].getfScore() == temp.getfScore()) {
				if(array[i].getgScore() > temp.getgScore()) {
					//System.out.println("array[" + i + "] is replaced. ");
					swapUp(array, i, openCounter);
					array[i] = temp;
					break;
				}
			}
		}
		return array;
	}
	static void swapUp(Node[] array, int bottom, int top) {//bottom is included in the swap up. Top is replaced
		
		for(int i = top; i > bottom; i--) {
			array[i] = array[i-1];
		}		
	}
	static boolean inArray(Node[] array, Node target) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == target) {
				return true;
			}
		}
		return false;
	}
	static boolean checkSorted(Node[] array, int length) {
		for(int i = 0; i < length; i++) {
			if(array[i].getfScore() > array[i + 1].getfScore()) {
				return false;
			}
		}
		return true;
	}
}
