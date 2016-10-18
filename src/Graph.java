/**
 * This class is used to hold all information about the seating graphs for the problem,
 * as well as expose the colouring algorithms used to create seating arrangements.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Graph {
	
	private final int numberOfVertices;
	private int numberOfEdges;
	private boolean[][] adjacentVertices;
	
	public Graph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = 0; // The graph is initialized with no edges
		this.adjacentVertices = new boolean[numberOfVertices][numberOfVertices];
	}
	
	public int getNumberOfVertices() {
		return numberOfVertices;
	}
	
	public int getNumberOfEdges() {
		return numberOfEdges;
	}
	
	/**
	 * This method is used to add a new undirected edge to the graph.
	 * 
	 * @param vertexOne - The first vertex of the edge
	 * @param vertexTwo - The second vertex of the edge
	 */
	public void addEdge(int vertexOne, int vertexTwo) {
		if(!adjacentVertices[vertexOne][vertexTwo]) {
			this.numberOfEdges++;
		}
		adjacentVertices[vertexOne][vertexTwo] = true;
		adjacentVertices[vertexTwo][vertexOne] = true;
	}
	
	/**
	 * This method is use to generate a new seating arrangement for the graph where
	 * we want all of the attendees to be seated at only two tables. This uses red 
	 * and blue as the possible vertex colours.
	 * 
	 * @return boolean result - True if there is a possible seating arrangement or false
	 * if there is no possible seating arrangement.
	 */
	public boolean twoColour() {
		final String RED = "RED";
		final String BLUE = "BLUE";
		Set<Integer> reds = new HashSet<Integer>();
		Set<Integer> blues = new HashSet<Integer>();
		String[] vertexColours = new String[numberOfVertices];
		Arrays.fill(vertexColours, null);
		
		vertexColours[0] = RED;
		reds.add(1); // Add first person to reds
		
		for(int i = 0; i < numberOfVertices; i++) {
			for(int j = 0; j < numberOfVertices; j++) {
				if(adjacentVertices[i][j]) {
					if(vertexColours[i] == RED) { // If the current vertex is red
						if(vertexColours[j] == RED) {
							System.err.println("Cannot find possible seating arrangement for graph.\n");
							return false;
						} else {
							vertexColours[j] = BLUE;
							blues.add(j + 1); // since the array is zero indexed we must add 1 to get the correct vertex number
						}
					} else if(vertexColours[i] == BLUE) { // if the current vertex is blue
						if(vertexColours[j] == BLUE) {
							System.err.println("Cannot find possible seating arrangement for graph.\n");
							return false;
						} else {
							vertexColours[j] = RED;
							reds.add(j + 1); // since the array is zero indexed we must add 1 to get the correct vertex number
						}
					}
				}
			}
		}
		
		// Create sortable lists of the red and blue tables
		ArrayList<Integer> redsList = new ArrayList<Integer>(reds);
		ArrayList<Integer> bluesList = new ArrayList<Integer>(blues);
		
		// Sort the tables
		Collections.sort(redsList);
		Collections.sort(bluesList);
		
		System.out.println("Solution found!");
		System.out.println("REDS:\n" + redsList);
		System.out.println("BLUES:\n" + bluesList + "\n");
		
		return true;
	}
	
	/**
	 * This method is used to generate a seating arrangement for the current graph using
	 * the minimal colouring algorithm. This function generates the seating arrangement in O(n^2).
	 * 
	 * @return int[] vertexColours - An array of integers whose values represent the colour
	 * of the node at the corresponding index + 1 (i.e. the int located at index 0 of vertexColours
	 * is the colour for vertex 1 in the graph).
	 */
	public int[] easyColour() {
		ArrayList<Integer> possibleColours = new ArrayList<Integer>();
		ArrayList<Adjacent> sortedAdjacentVertices = new ArrayList<Adjacent>();
		int[] vertexColours = new int[numberOfVertices];
		Arrays.fill(vertexColours, 0);
		int lowestColour;
		boolean end;
		int index;
		
		for(int i = 1; i <= numberOfVertices; i++) {
			possibleColours.add(i);
		}
		
		for(int i = 0; i < numberOfVertices; i++) {
			sortedAdjacentVertices.add(new Adjacent(i + 1, adjacentVertices[i]));
		}
		
		Collections.sort(sortedAdjacentVertices, new Comparator<Adjacent>() {
			@Override
			public int compare(Adjacent adj1, Adjacent adj2) {
				return adj2.getNumberOfAdjacentVerticies() - adj1.getNumberOfAdjacentVerticies();
			}
		});
		
		vertexColours[sortedAdjacentVertices.get(0).getVertexNumber() - 1] = 1;
		
		for(Adjacent adj : sortedAdjacentVertices) {
			end = false;
			lowestColour = 1;
			index = 0;
			while(!end) {
				if(vertexColours[adj.getVertexNumber() - 1] != 0) {
					end = true;
					break;
				}
				if(adjacentVertices[adj.getVertexNumber() - 1][index]) {
					if(vertexColours[index] == lowestColour) {
						lowestColour++;
						index = 0;
					}
				}
				if(index == numberOfVertices - 1) {
					end = true;
				}
				
				index++;
			}
			vertexColours[adj.getVertexNumber() - 1] = lowestColour;
		}
		
		ArrayList<Integer> vertexColoursList = new ArrayList<Integer>();
		for(int num : vertexColours) {
			vertexColoursList.add(num);
		}
		
		Collections.sort(vertexColoursList);
		System.out.println("Colours used = " + vertexColoursList.get(numberOfVertices - 1));
		
		return vertexColours;
		
	}
	
}
