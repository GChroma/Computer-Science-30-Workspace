package code;

public class LinkedGrid{
	
	private Node topLeft;
	private int dimension;
	private int count = 0;
	
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
				
				temp.setUp(temp.getLeft().getUp().getRight());//set up.
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
	
	public void knightConnect() throws Exception {
		Node temp = topLeft;
		Node rowMarker = topLeft;
		
		while(temp != null) {
			while(temp != null) {
				
				try {
					temp.setTopLeft(temp.getUp().getUp().getLeft());
				}catch(Exception e) {
					
				}
				try {
					temp.setTopRight(temp.getUp().getUp().getRight());
				}catch(Exception e) {
					
				}
				try {
					
					temp.setRightTop(temp.getRight().getRight().getUp());
				}catch(Exception e) {
					
				}
				try {
					temp.setRightBottom(temp.getRight().getRight().getDown());
				}catch(Exception e) {
					
				}
				try {
					temp.setBottomRight(temp.getDown().getDown().getRight());
				}catch(Exception e) {
					
				}
				try {
					temp.setBottomLeft(temp.getDown().getDown().getLeft());
				}catch(Exception e) {
					
				}
				try {
					temp.setLeftBottom(temp.getLeft().getLeft().getDown());
				}catch(Exception e) {
					
				}
				try {
					temp.setLeftTop(temp.getLeft().getLeft().getUp());
				}catch(Exception e) {
					
				}		
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
		System.out.println("Done");
		
	}
	public Node findCoords(int x, int y) {
		Node temp = topLeft;
		
		for(int i = 0; i < (y - 1); i++) {//move temp Down
			temp = temp.getDown();
		}
		
		for(int i = 0; i < (x - 1); i++) {//move temp right
				
			temp = temp.getRight();
		}
		return temp;
		
	}
	
	public void knightsTour(Node temp) {//search from temp
		//display();
		//System.out.println(temp.getBottomRight().getData());
		
		temp.setData(temp.getData() + 1);
		
		if(temp.getTopLeft() != null)
			if(temp.getTopLeft().getData() == 0) {
				temp.getTopLeft().setData((temp.getData()));
				knightsTour(temp.getTopLeft());
			}
		if(temp.getTopRight() != null)
			if(temp.getTopRight() != null && temp.getTopRight().getData() == 0) {
				temp.getTopRight().setData((temp.getData()));
				knightsTour(temp.getTopRight());
			}
		if(temp.getRightTop() != null)
			if(temp.getRightTop() != null && temp.getRightTop().getData() == 0) {
				temp.getRightTop().setData((temp.getData()));
				knightsTour(temp.getRightTop());
			}
		if(temp.getRightBottom() != null)
			if(temp.getRightBottom() != null && temp.getRightBottom().getData() == 0) {
				temp.getRightBottom().setData((temp.getData()));
				knightsTour(temp.getRightBottom());
			}
		if(temp.getBottomRight() != null)
			if(temp.getBottomRight() != null && temp.getBottomRight().getData() == 0) {
				temp.getBottomRight().setData((temp.getData()));
				knightsTour(temp.getBottomRight());
			}
		if(temp.getBottomLeft() != null)
			if(temp.getBottomLeft() != null && temp.getBottomLeft().getData() == 0) {
				temp.getBottomLeft().setData((temp.getData()));
				knightsTour(temp.getBottomLeft());
			}
		if(temp.getLeftBottom() != null)
			if(temp.getLeftBottom() != null && temp.getLeftBottom().getData() == 0) {
				temp.getLeftBottom().setData((temp.getData()));
				knightsTour(temp.getLeftBottom());
			}
		if(temp.getLeftTop() != null)
			if(temp.getLeftTop() != null && temp.getLeftTop().getData() == 0) {
				temp.getLeftTop().setData((temp.getData()));
				knightsTour(temp.getLeftTop());
			}
		
		if(temp.getData() == (dimension*dimension)) {
			display();
			System.out.println();
			setCount(getCount() + 1);
		}
		temp.setData(0);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
