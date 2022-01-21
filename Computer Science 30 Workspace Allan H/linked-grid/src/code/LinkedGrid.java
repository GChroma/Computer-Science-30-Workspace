package code;

public class LinkedGrid {
	
	private Node topLeft;
	private int dimension;
	
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
				System.out.print(temp.getData() + " ");
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
				
				if(temp.getData() == 0) {
					System.out.print("\u001b[1;41m  \u001b[0m");
				}else if(temp.getData() == 1) {
					System.out.print("\u001b[1;42m  \u001b[0m");
				}else if(temp.getData() == 2) {
					System.out.print("\u001b[1;43m  \u001b[0m");
				}else if(temp.getData() == 3) {
					System.out.print("\u001b[1;44m  \u001b[0m");
				}else if(temp.getData() == 4) {
					System.out.print("\u001b[1;45m  \u001b[0m");
				}else if(temp.getData() == 5) {
					System.out.print("\u001b[1;46m  \u001b[0m");
				}
			
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
			System.out.println();
		}
	}
	
	public void flood(int data) {
		topLeft.flood(data);
	}
	public boolean isFlooded() {
		Node temp = topLeft;
		Node rowMarker = topLeft;
		int data = topLeft.getData();
		
		while(temp != null) {
			while(temp != null) {
				
				if(temp.getData() != data) {
					return false;
				}
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
		
		return true;

		
	}
	
	
}
