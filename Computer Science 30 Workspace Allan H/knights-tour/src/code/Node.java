package code;

public class Node {
	
	private int data;
	
	private Node up, down, left, right;
	private Node topLeft, topRight, rightTop, rightBottom, bottomRight, bottomLeft, leftBottom, leftTop;
	
	public Node getTopLeft() {
		return topLeft;
	}
	public void setTopLeft(Node topLeft) {
		this.topLeft = topLeft;
	}
	public Node getTopRight() {
		return topRight;
	}
	public void setTopRight(Node topRight) {
		this.topRight = topRight;
	}
	public Node getRightTop() {
		return rightTop;
	}
	public void setRightTop(Node rightTop) {
		this.rightTop = rightTop;
	}
	public Node getRightBottom() {
		return rightBottom;
	}
	public void setRightBottom(Node rightBottom) {
		this.rightBottom = rightBottom;
	}
	public Node getBottomRight() {
		return bottomRight;
	}
	public void setBottomRight(Node bottomRight) {
		this.bottomRight = bottomRight;
	}
	public Node getBottomLeft() {
		return bottomLeft;
	}
	public void setBottomLeft(Node bottomLeft) {
		this.bottomLeft = bottomLeft;
	}
	public Node getLeftBottom() {
		return leftBottom;
	}
	public void setLeftBottom(Node leftBottom) {
		this.leftBottom = leftBottom;
	}
	public Node getLeftTop() {
		return leftTop;
	}
	public void setLeftTop(Node leftTop) {
		this.leftTop = leftTop;
	}
	public Node() {
		this.data = 0;//start 0 for knights tour.
	}
	public Node(int data) {
		this.data = data;
	}
	public void flood(int data) {//change data of everything same colour (same number)
		//could potentially change data to protected to make this faster
		if(data != this.data) {//flooding the same number is pointless.
			int prevData = this.data;
			this.data = data;	
			if(up != null) {
				if(up.getData() == prevData) {
					up.flood(data);
				}
			}
			if(right != null) {
				if(right.getData() == prevData) {
					right.flood(data);
				}
			}
			if(down != null) {
				if(down.getData() == prevData) {
					down.flood(data);
				}
			}
			if(left != null) {
				if(left.getData() == prevData) {
					left.flood(data);
				}
			}
		}
		
		
		
		
	}
	
	
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	
	
	
	
}
