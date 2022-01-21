package code;

public class LinkedList {

		//pointers - references are the same. Technically different though. Java uses references.
	private Node first;
	private Node last;
	
	public void push(int data) {
		Node temp = new Node(data);//temp name is destroyed but its not an orphan because of first and last variables.
		
		if(first == null) {
			first = temp;
		
		}else {
			last.setNext(temp);
			temp.setPrev(last);
			
		}
		last = temp;
	}
	public void pop() {//removes last item from the list (you need to kill the orphan yourself in c++. (delete))
		if(last != null) {
			if(first != last) {
				last = last.getPrev();
				last.setNext(null);
			}else {
				first = null;
				last = null;
			}	
		}
	}
	
	public void pop(int data) {
		Node temp = find(data);
		if(temp == last) {
			pop();//use pop in case it is a one item list.
		}else if(temp == first) {
			first = first.getNext();
			first.setPrev(null);
		}else {
			temp.getNext().setPrev(temp.getPrev());
			temp.getPrev().setNext(temp.getNext());
		}
		
	}
	public void swap(Node node1, Node node2) {
		
		if(node1 != null && node2 != null){//if a node doesn't exist cannot be swapped.
			
			//swap names so node1 is always less than node 2
			Node temp = first;
			while(temp!=null) {
				if(temp == node1) {
					break;
				}else if(temp == node2) {
					//swap nodes. It's ok to use temp as it breaks after.
					temp = node1;
					node1 = node2;
					node2 = temp;
					break;
				}
				temp = temp.getNext();
			}
			//System.out.println(node1.getData() + " " + node2.getData());
			//cases: Adjacent, one between, more between, edges.
			//detect cases:
			
			boolean adjacent = false;
			boolean topEdge = false;
			boolean bottomEdge = false;
		
			if(node1.getNext() == node2) {
				adjacent = true;
			}
			if(node1 == first) {
				bottomEdge = true;
			}
			if(node2 == last) {
				topEdge = true;
			}
		
			/*cases:
			 *  Adjacent
			 * 		only bottom edge
			 * 		only top edge
			 * 		both
			 *  Any other distance
			 *		only bottom edge
			 * 		only top edge
			 * 		both
			 */
				
			if(adjacent) {//all cases when adjacent
				//this block works for all outer connections. (node2 previous and node1 next and connections from there. Also last && first.)
				if(bottomEdge) {//node1 is first
					first = node2;
					node2.setPrev(null);
				}else {
					node2.setPrev(node1.getPrev());
					node2.getPrev().setNext(node2);
				}
					
				if(topEdge) {//node2 is last
					last = node1;
					node1.setNext(null);
				}else {
					node1.setNext(node2.getNext());
					node1.getNext().setPrev(node1);
				}
				
			
			
				node2.setNext(node1);
				node1.setPrev(node2);
				
			}else {//all other cases.
				Node temp1 = node1.getNext();
				Node temp2 = node2.getPrev();
				
				if(bottomEdge) {//node1 is first
					first = node2;
					node2.setPrev(null);
				}else {
					node2.setPrev(node1.getPrev());
					node2.getPrev().setNext(node2);
				}
					
				if(topEdge) {//node2 is last
					last = node1;
					node1.setNext(null);
				}else {
					node1.setNext(node2.getNext());
					node1.getNext().setPrev(node1);
				}
				
				node2.setNext(temp1);
				node2.getNext().setPrev(node2);
				
				node1.setPrev(temp2);
				node1.getPrev().setNext(node1);
				
			}
			
			
			
		}
	}
	
	public Node find(int data) {
		Node temp = first;
		while(temp!=null) {
			if(temp.getData() == data) {
				return temp;
			}
			temp = temp.getNext();
		}
		
		return temp;//null
	}
	
	public void display() {
		Node temp = first;
		while(temp!=null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
		System.out.println();
		temp = last;
		while(temp!=null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getPrev();
		}
	System.out.println();	
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

}
