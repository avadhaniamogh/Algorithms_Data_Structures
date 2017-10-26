package linked.list;

public class LinkedListNode {
	
	int data;
	LinkedListNode next = null;
	
	public LinkedListNode() {
		
	}
	
	public LinkedListNode(int d) {
		data = d;
	}
	
	void appendToTail(int d) {
		LinkedListNode node = new LinkedListNode(d);
		LinkedListNode n = this;
		while(n.next != null) {
			n = n.next;
		}
		n.next = node;
	}

}
