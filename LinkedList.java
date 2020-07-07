/* 
 * AUTHOR: Dhanush Giriyan
 * DESCRIPTION: This is the linked list file which provides the structure for the closed list
 * CLASS: CSE 205 - Summer 2020
 * NAME: ASSIGNMENT #4
 * DATE OF COMPLETION: 05/07/2020
 * 
 */

public class LinkedList{

	private LinkedNode head;
	private LinkedNode tail;
	private int count;
	
	// ---------------------- CONSTRUCTORS ---------------------- //
	public LinkedList () {
		this.head = null;
		this.tail = null;
		this.count = 0;
	}
	
	// ---------------------- MAIN FUNCTIONALITIES ---------------------- //
	
	// ---------------------- Insertion Alogorithms ---------------------- // 
	
	public void insertAtHead (CellNode data) {
		LinkedNode newNode = new LinkedNode (data, head); // new node now points to head
		head = newNode; // head is now newNode
		this.count++;
	}
	
	public void insertAtTail (CellNode data) {
		if (head == null) { // empty list
			insertAtHead(data);
		}
		else {
			// traverse all the way to the end of the list
			LinkedNode temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			// temp is now at the last node of the list
			
			// create new node
			LinkedNode newNode = new LinkedNode (data); // newNode contains data and points to null
			temp.setNext(newNode); // last node now points to newNode
			tail = newNode; // set tail to the the newNode
			this.count++;
		}
	}
	
	public void insertAtIndex (int index, CellNode data) {
		if (index < 0 || index > this.count) {
			throw new IllegalArgumentException ("Index " + index + " is not valid");
		}
		
		if (index == 0) { 
			insertAtHead (data);
		}
		else if (index == count) {
			insertAtTail (data);
		}
		else {
			// arbitrary insert
			
			// traverse to the index
			LinkedNode temp = head;
			for (int i = 0; i < index-1; i++) {
				temp = temp.getNext();
			}
			// we are at the node where we need to perform the insertion
			LinkedNode newNode = new LinkedNode (data, temp.getNext());
			// new node now points to element after index
			temp.setNext(newNode);
			// now temp also points to the new node being inserted at 'index'
			this.count++;
		}
	}
	
	// ---------------------- Deletion Algorithms ---------------------- //
	
	public void deleteAtHead () {
		head = head.getNext();
		this.count--;
		// the old head now no longer has any incoming references
		// so the garbage collector will come for it
	}
	
	public void deleteAtTail () {
		// first need to get to tail
		LinkedNode temp = head;
		while (temp.getNext().getNext() != null) {
			temp = temp.getNext();
		}
		// arrived at the last but one node
		
		temp.setNext(null); // last but one node now points to null
		tail = temp; // last but one node now becomes the tail
		this.count--;
	}
	
	public void deleteAtIndex (int index) {
		if (index < 0 || index > this.count) {
			throw new IllegalArgumentException ("Index " + index + " is not valid");
		}
		
		if (index == 0) {
			deleteAtHead();
		}
		else  if (index == this.count) {
			deleteAtTail();
		}
		else {
			// arbitrary delete
			
			LinkedNode temp = head;
			// traverse to the node before which we want to delete
			for (int i = 0; i < index-1; i++) {
				temp = temp.getNext();
			}
			
			temp.setNext(temp.getNext().getNext());
			// make temp point to the next to next node
			// now the middle node has no incoming references
			// so, the garbage collector will come for it
			this.count--;
		}
	}
	
	public boolean exists (CellNode lookedFor) {
		LinkedNode temp = head;
		
		while ((temp != null) && (temp.getData().compareTo(lookedFor) != 0)) {
			temp = temp.getNext();
		}
		
		if (temp == null) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	// ---------------------- SIDE FUNCTIONALITIES ---------------------- //
	public int findSize () {
		LinkedNode temp = head; 
		int size = 0;
		
		// traverse the list
		while (temp != null) {
			temp = temp.getNext();
			size++;
		}
		
		return size;
	}
	
	// ---------------------- GETTERS ---------------------- //
	public LinkedNode getHead () {
		return this.head;
	}
	
	public LinkedNode getTail () {
		return this.tail;
	}
	
	public int getSize () {
		return this.count;
	}
	
	// ------------------------- TO STRING ------------------------- //
	public String toString () {
		String output = ""; 
		
		LinkedNode temp = head;
		while (temp != null) {
			output += "[" + temp.getData() + "] ";
			temp = temp.getNext();
		}
		// temp is now at the last node
		
		return output;
	}
	
	// ------------------------- COMPARE TO ------------------------- //
	
}
