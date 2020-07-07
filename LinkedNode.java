/* 
 * AUTHOR: Dhanush Giriyan
 * DESCRIPTION: This file defines a standard node for the linked list
 * CLASS: CSE 205 - Summer 2020
 * NAME: ASSIGNMENT #4
 * DATE OF COMPLETION: 05/07/2020
 * 
 */

public class LinkedNode {
	
	private CellNode data;
	private LinkedNode next;
	
	// -------------------- CONSTRUCTORS -------------------- //
	
	public LinkedNode () {
		this.data = null;
		this.next = null;
	}
	
	public LinkedNode (CellNode data) {
		this.data = data; 
	}
	
	public LinkedNode (CellNode data, LinkedNode nextNode) {
		this.data = data; 
		this.next = nextNode;
	}
	
	// -------------------- GETTERS -------------------- //
	public CellNode getData () {
		return this.data;
	}
	
	public LinkedNode getNext () {
		return this.next;
	}
	
	// -------------------- SETTERS -------------------- //
	public void setData (CellNode data) { 
		this.data = data;
	}
	
	public void setNext (LinkedNode nextNode) {
		this.next = nextNode;
	}
	
}
