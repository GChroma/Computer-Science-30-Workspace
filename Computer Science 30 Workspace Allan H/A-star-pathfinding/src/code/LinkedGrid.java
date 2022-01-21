package code;

public class LinkedGrid {
	
	private Node topLeft;
	private Node end;
	private int dimension;
	private int startx, starty, endx, endy;//less is higher(y)/lefter(x)
	
	public LinkedGrid(int dimension) {
		this.dimension = dimension;
		
		topLeft = new Node();
		
		
		
		Node columnMarker = topLeft;
		//TOP ROW
		for(int i = 0; i < dimension-1; i++) {//dimension - 1 because topLeft already exists
			Node temp = new Node();
			temp.setLeft(columnMarker);
			columnMarker.setRight(temp);
			
			columnMarker = temp;
		}
		
		//first node in row
		Node rowMarker = topLeft;
		
		
		//building the rest of the rows.
		for(int y = 0; y < dimension-1; y++) {
			
		Node temp1 = new Node();
		columnMarker = temp1;
		temp1.setUp(rowMarker);
		rowMarker.setDown(temp1);
		
		
		//other nodes in row
			for(int i = 0; i < dimension-1; i++) {//dimension - 1 because topLeft already exists
				Node temp = new Node();
				temp.setLeft(columnMarker);
				columnMarker.setRight(temp);
				temp.setUp(temp.getLeft().getUp().getRight());
				temp.getUp().setDown(temp);
				columnMarker = temp;
			}
		rowMarker = rowMarker.getDown();
		}
	}
	
	public void display() {
		Node temp = topLeft;
		Node rowMarker = topLeft;
		
		while(temp != null) {
			while(temp != null) {
				System.out.print(temp.isPassable() + " ");
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
			System.out.println();
		}
	}
	
	public void displayColour() {

		Node temp = topLeft;
		Node rowMarker = topLeft;
		
		while(temp != null) {
			while(temp != null) {
				
				if(temp.isStart()) {
					System.out.print("\u001b[1;43m  \u001b[0m");
				}else if(temp.isEnd()) {
					System.out.print("\u001b[1;44m  \u001b[0m");
				}else if(temp.isPath()) {
					System.out.print("\u001b[1;46m  \u001b[0m");
				}else if(temp.isOpen()) {
					System.out.print("\u001b[1;45m  \u001b[0m");
				}else if(temp.isPassable()) {
					System.out.print("\u001b[1;42m  \u001b[0m");
				}else{
					System.out.print("\u001b[1;41m  \u001b[0m");
				}
				/*
				System.out.print("\u001b[1;42m  \u001b[0m");
				}else if(temp.getData() == 2) {
					System.out.print("\u001b[1;43m  \u001b[0m");
				}else if(temp.getData() == 3) {
					System.out.print("\u001b[1;44m  \u001b[0m");
				}else if(temp.getData() == 4) {
					System.out.print("\u001b[1;45m  \u001b[0m");
				}else if(temp.getData() == 5) {
					System.out.print("\u001b[1;46m  \u001b[0m");
				}*/
			
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
			System.out.println();
		}
	}
	public void setPath() {
		Node temp = end;
		//System.out.println(end.getfScore());
		while(!temp.getPrev().isStart()) {
			temp.getPrev().setPath(true);
		//	System.out.println(temp.getfScore() + " path");
			temp = temp.getPrev();
		}
	}
	public void placeStartAndEnd() {
		int x = ((int)(Math.random()*dimension));
		int y = ((int)(Math.random()*dimension));
		Node temp = topLeft;
		int xCounter = 0;
		int yCounter = 0;
		//System.out.println(x + " " + y);
		while(xCounter != x) {
			xCounter++;
			temp = temp.getDown();
		}
		while(yCounter != y) {
			yCounter++;
			temp = temp.getRight();
		}
		temp.setStart(true);
		Node temp2;
		starty = x; startx = y;//y coordinate is x (starting from top left corner)
		do {
			x = ((int)(Math.random()*dimension));
			y = ((int)(Math.random()*dimension));
			temp2 = topLeft;
			xCounter = 0;
			yCounter = 0;
			while(xCounter != x) {
				xCounter++;
				temp2 = temp2.getDown();
			}
			while(yCounter != y) {
				yCounter++;
				temp2 = temp2.getRight();
			}
		}while(temp2 == temp || !temp2.isPassable());
		temp2.setEnd(true);
		end = temp2;
		endx = y; endy = x;
		
	}
	public void diagonalConnect() throws Exception {
		
		Node temp = topLeft;
		Node rowMarker = topLeft;
		
		while(temp != null) {
			while(temp != null) {
				
				try {
					temp.setTopLeft(temp.getUp().getLeft());
				}catch(Exception e) {
					
				}
				try {
					temp.setTopRight(temp.getUp().getRight());
				}catch(Exception e) {
					
				}
				try {
					temp.setBottomRight(temp.getDown().getRight());
				}catch(Exception e) {
					
				}
				try {
					temp.setBottomLeft(temp.getDown().getLeft());
				}catch(Exception e) {
					
				}
		
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
	}
	public void sethScore(Node target) {
		int xCounter = 0;
		int yCounter = 0;
		Node temp = topLeft;
		//System.out.println(x + " " + y);
		Node rowMarker = topLeft;
		
		while(temp != null) {
			xCounter = 0;
			while(temp != null) {
				if(temp == target) 
					break;
				xCounter++;
				temp = temp.getRight();
				
			}
			if(temp == target)
				break;
			yCounter++;
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
			
		}
		int hScore = 0;
		int xdistance = Math.abs(endx - xCounter);
		int ydistance = Math.abs(endy - yCounter);
		//startx and starty now have coordinates relative to the target. It doesn't matter positive or negative.
		if(xdistance >= ydistance) {
			hScore += 14*ydistance;
			hScore += 10*(xdistance - ydistance);
		}else if(xdistance < ydistance) {
			hScore += 14*xdistance;
			hScore += 10*(ydistance - xdistance);
		}
		
		target.sethScore(hScore);
		
		
	}
	public void sethScores() {
	
		Node temp = topLeft;
		Node rowMarker = topLeft;
		
		while(temp != null) {
			while(temp != null) {
				sethScore(temp);
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
	}
	public void displayhScores() {
		Node temp = topLeft;
		Node rowMarker = topLeft;
		
		while(temp != null) {
			while(temp != null) {
				System.out.print(temp.gethScore() + " ");
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
			System.out.println();
		}
	}
	public Node findStart() {
		Node temp = topLeft;
		
		int xCounter = 0;
		int yCounter = 0;
		//System.out.println(x + " " + y);
		while(xCounter != starty) {
			xCounter++;
			temp = temp.getDown();
		}
		while(yCounter != startx) {
			yCounter++;
			temp = temp.getRight();
		}
		if(temp.isStart())
			return temp;
		else {
			return null;
		}
	}
}
