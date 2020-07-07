/* 
 * AUTHOR: Dhanush Giriyan
 * DESCRIPTION: This class contains all the functions of the A* search algorithm 
 * CLASS: CSE 205 - Summer 2020
 * NAME: ASSIGNMENT #4
 * DATE OF COMPLETION: 05/07/2020
 * 
 */

public class PathFinder {

	private MinHeap openList;
	private LinkedList closedList;
	
	private int srcX;
	private int srcY;
	private int destX;
	private int destY;
	
	private int[][] grid;
	
	public PathFinder (int srcX, int srcY, int destX, int destY, int[][] grid) {
		
		this.srcX = srcX;
		this.srcY = srcY;
		this.destX = destX;
		this.destY = destY;
		// fix the source and destination
		
		this.grid = grid;
		
		openList = new MinHeap (1000);
		// lets just assume that the max size of the heap is the same as the number of cells in the grid
		closedList = new LinkedList ();
		
	}
	
	public int[][] brain () {
		CellNode solution = search(); // let the hunt begin!! MAY THE ODDS EVER BE IN YOUR FAVOR!
		if (solution != null) {
			int[][] finalGrid = getSolutionPath (solution);
			return finalGrid;
		}
		return null;
	}
	
	public CellNode initializeNode (CellNode node) {
		
		// calculate g(node)
		double g = Math.abs(node.getX() - node.getParent().getX())
					+ Math.abs(node.getY() - node.getParent().getY());
		// g = |x1 - x2| + |y1 - y2| => diagonal children get g value of 2 and adjecent children get 1
		g += node.getParent().getG();
		// add the gCost of the parent node
		
		// calculate h(node)
		double h = Math.abs(node.getX() - this.destX) + Math.abs(node.getY() - this.destY);
		// h = |x - destX| + |Y - destY|
		// MANHATTAN DISTANCE
		
		// calculate f(node)
		double f = g + h; 
		// total cost, f = g + h;
		
		// now we need to set these values in the CellNode 'node'
		node.setG(g);
		node.setH(h);
		node.setF(f);
		
		return node;
	}
	
	public CellNode search () {
		// as long as open list is not empty
		// check if currNode is a goal state
		// if not, only then proceed, otherwise terminate the process
		// for currNode, get all it's children
		// check if child is in closedList
		// add it to openList
		// add the parent to the closed list
		// after going through each of the children,
			// get the node at the top of the openList (highest priority)
		// make currNode = node of highest priority
		// run this while loop until the condition is broken
		
		// need to define the parent node
		// the first parent node must be source
		CellNode parentNode = new CellNode (this.srcX, this.srcY, null);
		// need to add parentNode to openList or the while loop will never begin
		openList.insertElement(parentNode);
		
		while (openList.getHeapSize() != 0) {
			// is parentNode a goal state? 
			if (isDestination(parentNode) == true) {
				System.out.println("Solution found!");
				System.out.println();
				return parentNode;
				// end the while loop
				// our job here is done
			}
			// otherwise the while loop may continue
			
			CellNode[] children = getAllChildren (parentNode);
			// we now have all the initialized children nodes
			
			// add parent node to closed list
			closedList.insertAtHead(parentNode);
			
			// check if any of the children are in the closedList
			// if not, add to openList
			for (int i = 0; i < children.length; i++) {
				// for each child in children
				if (children[i] == null) {
					continue;
					// skip this iteration
				}
				if (closedList.exists(children[i]) == true) {
					children[i] = null; // abandon this child. Hopefully it doesn't seek out revenge later!
				}
				else {
					openList.insertElement(children[i]);
					// add child to openList
				}
			}
			
			// get the node with the least f value in the openList
			// assign this node to parentNode so that it can be expanded next
			parentNode = openList.decreaseKey();
//			System.out.println(parentNode.getX() + ", " + parentNode.getY());
			
		}
		
		System.out.println("No solution. Returning Null.");
		return null;
		
	}
	
	public int[][] getSolutionPath (CellNode node) {
		int[][] finalPath = this.grid;
		for (int i = 0; i < finalPath.length; i++) {
			for (int j = 0; j < finalPath.length; j++) {
				if (this.grid[i][j] == 1) {
					finalPath[i][j] = -2;
				}
				else if (this.grid[i][j] == 0) {
					finalPath [i][j] = -1;
				}
				// -1 for every 0
				// -2 for every 1
			}
		}
		
		int filler = 0;
		while ((node.getX() != this.srcX) || (node.getY() != this.srcY)) {
			// use filler value to fill positions in the grid where the solution path should be
			finalPath[node.getX()][node.getY()] = filler;
			// increment the filler so that we can see the solution more clearly
			filler++;
			// substitute node with it's parent
			node = node.getParent();
			
			// continue this process until node becomes equal to the source
		}
		
		finalPath[node.getX()][node.getY()] = filler;
		// one last time for the source node
		
		return finalPath;
	}
	
	public boolean isDestination (CellNode node) {
		if ((node.getX() == this.destX) && (node.getY() == this.destY)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public CellNode[] getAllChildren (CellNode parent) {
		
		// each parent has at most 8 children
		// i, j-1
		// i, j+1
		// i+1, j
		// i+1, j-1
		// i+1, j+1
		// i-1, j-1
		// i-1, j
		// i-1, j+1
		
		CellNode[] children = new CellNode [8]; // create an array of size 8
		
		// get parent's coordinates
		int x = parent.getX();
		int y = parent.getY();
		
		// need to ensure that the child does appear outside the grid
		// i, j need to be less than grid.length
		
		int startPosX = Math.max(0,  x-1);
		int endPosX = Math.min(x+1, this.grid.length-1);
		int startPosY = Math.max(0,  y-1);
		int endPosY = Math.min(y+1, this.grid.length-1);
		
		int index = 0;
		for (int i = startPosX; i <= endPosX; i++) {
			for (int j = startPosY; j <= endPosY; j++) {
				if (((i != x) || (j != y)) && (this.grid[i][j] != 1)) {
					// need to ensure that the parent doesn't get created as a child of itself
					// also, the child must not be an obstacle/wall
					CellNode node = new CellNode (i, j, parent); // create new child
					children[index] = initializeNode(node); // initialize each child and add to array
					index++; // increment index
				}
			}
		}
		// children contains all possible children of parent
		
		return children;
		
	}
	
}
