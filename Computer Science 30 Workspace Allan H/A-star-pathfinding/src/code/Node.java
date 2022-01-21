package code;

public class Node {
	
	private boolean passable;
	private int gScore = 0;
	private int hScore = 0;
	private boolean start = false;
	private boolean end = false;
	private boolean path = false;
	private boolean open = false;
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isPath() {
		return path;
	}

	public void setPath(boolean path) {
		this.path = path;
	}

	private Node prev, up, down, left, right, topLeft, topRight, bottomLeft, bottomRight;

	public Node(Node other) {
		this.gScore = other.getgScore();
		this.hScore = other.gethScore();
		this.prev = other.getPrev();
		this.start = other.isStart();
		this.end = other.isEnd();
		this.passable = other.isPassable();
		this.topLeft = other.getTopLeft();
		this.topRight = other.getTopRight();
		this.bottomLeft = other.getBottomLeft();
		this.bottomRight = other.getBottomRight();
		this.up = other.getUp();
		this.down = other.getDown();
		this.right = other.getRight();
		this.left = other.getLeft();
	}
	
	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node() {
		int number = ((int)(Math.random()*6));
		if(number < 2) {
			passable = false;
		}else {
			passable = true;
		}
	}
	
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

	public Node getBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(Node bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public Node getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Node bottomRight) {
		this.bottomRight = bottomRight;
	}

	public int getfScore() {
		return gScore + hScore;
	}
	public int getgScore() {
		return gScore;
	}

	public void setgScore(int gScore) {
		this.gScore = gScore;
	}

	public int gethScore() {
		return hScore;
	}

	public void sethScore(int hScore) {
		this.hScore = hScore;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public Node(boolean passable) {
		this.passable = passable;
	}
	
	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
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
