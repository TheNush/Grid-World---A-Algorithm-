/* 
 * AUTHOR: Dhanush Giriyan
 * DESCRIPTION: This is the min-heap used to track the open list in the search algorithm
 * CLASS: CSE 205 - Summer 2020
 * NAME: ASSIGNMENT #4
 * DATE OF COMPLETION: 05/07/2020
 * 
 */

public class MinHeap {
	
	private CellNode[] arr; // contains the heap data, which is cell nodes in this case
	private int heapSize; // current size of the heap
	private int maxSize; // maximum size of the heap
	
	// ------------------------ CONSTRUCTOR ------------------------ //
	
	public MinHeap (int size) {
		this.heapSize = 0;
		this.maxSize = size;
		this.arr = new CellNode[this.maxSize]; // initialize an array of max size
	}
	
	// ---------------- FUNCTIONALITIES ---------------- //
	
	public void insertElement (CellNode node) {
		if (this.heapSize == this.maxSize) {
			System.out.println("Sorry. You cannot add any "
					+ "more elements to this heap.");
			return; // terminate the insertion process - there is nothing more to do here
		}
		
		// heap has space to add another element
		this.arr[this.heapSize] = node; // fill val in the next free slot
		heapSize++;
		toString();
		bubbleUp (this.heapSize-1);
	}
	
	public CellNode decreaseKey () {
		// return the current lowest element
		// bring last element to the first position
		// bubble down
		CellNode toReturn = this.arr[0]; // gets the lowest element
		
		// now it's safe to bring the last element to the top
		this.arr[0] = this.arr[this.heapSize-1]; // gets the last element
		this.heapSize--; // decrease heap size
		
		// begin bubble down process
		bubbleDown (0); // begin the process at root node
		
		return toReturn;
	}
	
	public boolean exists (CellNode node) {
		// linear search
		// don't know if there is a better way to do it...
		for (int i = 0; i < this.heapSize; i++) {
			if (this.arr[i].compareTo(node) == 0) {
				return true;
			}
		}
		return false;
	}
	
	// might not need this one if I code the algo smartly...
	public void deleteValue (int val) {
		// if the value is found in the heap, delete it.
	}
	
	// ---------------- HELPERS ---------------- //
	public void bubbleUp (int index) {
		
		if (index == 0) {
			// do nothing as the element is already at the root node
			// so, it can't be bubbled up any more
			return;
		}
		
		int parentIndex = (index-1)/2;
		// check if parent has higher priority than child
		// meaning lower value since this is a minHeap
		
		if (this.arr[parentIndex].getF() > this.arr[index].getF()) {
			// parent has lower priority than child
			// => SWAP
			CellNode temp = this.arr[index];
			this.arr[index] = this.arr[parentIndex]; 
			this.arr[parentIndex] = temp;
			// swap is complete
			bubbleUp (parentIndex); // check if the new value of the parent is lower than it's parent
		}
		// else - do nothing and leave everything where they are
	}
	
	public void bubbleDown (int index) {
		
		// get the index of the child with higher priority
		int chosenOne = getImportantChild (index);
		
		if (chosenOne == -1) {
			// this parent has no children
			// so, can't bubble down
			// terminate process
			return;
		}
		
		// check if parent has higher priority than the chosen one
		// if not... SWAP! 
		if (this.arr[chosenOne].getF() < this.arr[index].getF()) {
			// swap parent with the higher priority kid
			CellNode temp = this.arr[index];
			this.arr[index] = this.arr[chosenOne];
			this.arr[chosenOne] = temp; 
			// swap is complete
		}
		else {
			// process ends here as the parent has bubbled down to the appropriate position
			return;
		}
		
		bubbleDown (chosenOne); // perform the same task using the chosenOne as the parent node
		
	}
	
	// ---------------- GETTERS ---------------- // 
	
	public CellNode getMin () {
		return arr[0]; // Min heap always carries the least value in the first array element (root node)
	}
	
	public int getImportantChild (int index) {
		// get left and right children of node
		int leftChildIndex = (2*index) + 1;
		int rightChildIndex = (2*index) + 2;
		
		if (leftChildIndex > this.heapSize-1) {
			// if left child is out of scope, then so is the right child
			// so, no children for this node
//			System.out.println("This parent has no children: " + index);
			return -1;
		}
		else if (rightChildIndex > this.heapSize-1) {
			// left child is the only child
			// so, the left child can be the only potential "chosen one"
			return leftChildIndex;
		}	
		// crazy cases out of the way
		
		if (this.arr[leftChildIndex].getF() < this.arr[rightChildIndex].getF()) {
			// left child has higher priority
			return leftChildIndex;
		}
		else if (this.arr[leftChildIndex].getF() > this.arr[rightChildIndex].getF()) {
			// right child has higher priority
			return rightChildIndex;
		}
		else {
//			System.out.println("Children are equally important. Index of parent: " + index);
//			System.out.println("Returning random child.");
			// fate decides which child is more important
			if (Math.random() > 0.5) {
				return rightChildIndex;
			}
			else {
				return leftChildIndex;
			}
		}
	}
	
	public CellNode getParentNode (int index) {
		return arr[(index-1)/2]; // index - 1 because the array is 0-indexed
	}
	
	public CellNode getLeftNode (int index) {
		return arr[(2*index) + 1]; // again, +1 to deal with the 0-index
	}
	
	public CellNode getRightNode (int index) {
		return arr[(2*index) + 2]; // similarly, +2 as well
	}
	
	public int getHeapSize () {
		return this.heapSize;
	}
	
	// ---------------- TO STRING ---------------- //
	public String toString () {
		String output = "";
		for (int i = 0; i < this.heapSize; i++) {
			output += this.arr[i] + " ";
		}
		
		return output;
	}
}
