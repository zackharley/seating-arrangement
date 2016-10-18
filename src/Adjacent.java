/**
 * This class is used to store the vertex number and its corresponding number of adjacent vertices 
 */

public class Adjacent {
	private int vertexNumber;
	private int numberOfAdjacentVerticies = 0;
	
	public Adjacent(int vertexNumber, boolean[] adjacents) {
		this.vertexNumber = vertexNumber;
		for(boolean isAdjacent : adjacents) {
			if(isAdjacent) {
				numberOfAdjacentVerticies++;
			}
		}
	}
	
	public int getVertexNumber() {
		return this.vertexNumber;
	}
	
	public int getNumberOfAdjacentVerticies() {
		return this.numberOfAdjacentVerticies;
	}
}
