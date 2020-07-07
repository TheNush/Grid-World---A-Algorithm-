/* 
 * AUTHOR: Dhanush Giriyan
 * DESCRIPTION: This is the main driver file for the whole search algorithm
 * CLASS: CSE 205 - Summer 2020
 * NAME: ASSIGNMENT #4
 * DATE OF COMPLETION: 05/07/2020
 * 
 */

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner reader =  new Scanner (System.in);
		
		// ---------------------- GET USER INPUT ---------------------- //
		
		int[][] grid = {{0,1,0,0,0,1,0},
						{0,1,0,0,0,0,0},
						{0,1,1,0,0,1,0},
						{0,0,0,1,0,1,0},
						{1,1,0,1,0,1,0},
						{0,1,0,1,1,1,0},
						{0,1,0,0,0,0,0}};
		
		System.out.println("You have entered a square grid of size: " + grid.length);
		
		System.out.println("Please enter X coordinate of source (0 indexed): ");
		int sourceX = Integer.parseInt(reader.nextLine());
		System.out.println("Please enter Y coordinate of source (0 indexed): ");
		int sourceY = Integer.parseInt(reader.nextLine());
		System.out.println("Please enter X coordinate of destination (0 indexed): ");
		int destinationX = Integer.parseInt(reader.nextLine());
		System.out.println("Please enter Y coordinate of destination (0 indexed): ");
		int destinationY = Integer.parseInt(reader.nextLine());
		
		// ---------------------- A* SEARCH ---------------------- //
		
		PathFinder finder = new PathFinder (sourceX, sourceY, destinationX, destinationY, grid);
		grid = finder.brain();
		if (grid != null) {
			System.out.println(grid[sourceX][sourceY] + " denotes the source and 0 represents the destination.");
			System.out.println("-2 denotes obstacles and -1 denotes cells that were rejected.");
			System.out.println("Follow the decreasing order of numbers to get the optimal path.");
			System.out.println("-------------------------------------------------------------------");
			
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					System.out.printf("%2d, ", grid[i][j]);
				}
				System.out.println();
			}
		}
		
		reader.close();
	}

}
