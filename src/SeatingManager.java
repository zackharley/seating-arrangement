/**
 * This class is used to manage the seating arrangements for the QUEEN'S Ultimately Exhausting Endlessly
 * Nested Society (in which “QUEEN'S” stands for “QUEEN'S Ultimately Exhausting Endlessly Nested Society”).
 * This class exposes three functions: 
 *  - initializeGraph()
 *  - twoColour()
 *  - easyColour()
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;

public class SeatingManager {
	
	int numberOfVertices;
	Graph graph;
	
	SeatingManager() {
		this.numberOfVertices = 0;
	}
	
	/**
	 * This method is used to initialize a new seating graph given a file path as an input.
	 * The file is read line by line, with the first line giving the total numberOfVertices,
	 * while the remaining lines give a vertex number followed by its neighbours.
	 * 
	 * @param filePath - The path to the graph initialization file
	 * @throws GraphInitializationException
	 */
	public void initializeGraph(Path filePath) throws GraphInitializationException {
		try(BufferedReader reader = Files.newBufferedReader(filePath)) {
			int lineNumber = 1;
			String line = null;
			int firstSpaceIndex;
			int firstTabIndex = -1;
			int vertexNumber = 0;
			StringTokenizer adjacentVerticesTokenizer;
			int adjacentVertex;
			
		    while ((line = reader.readLine()) != null) {
		    	if(lineNumber == 1) {
		    		numberOfVertices = Integer.parseInt(line);
		    		graph = new Graph(numberOfVertices);
		    	} else {
		    		firstSpaceIndex = line.indexOf(" ");
		    		firstTabIndex = line.indexOf("\t");
		    		
		    		vertexNumber = Integer.parseInt(line.substring(0, firstSpaceIndex));
		    		adjacentVerticesTokenizer = new StringTokenizer(line.substring(firstTabIndex + 1), "\t");
		    		
		    		while(adjacentVerticesTokenizer.hasMoreElements()) {
		    			adjacentVertex = Integer.parseInt(adjacentVerticesTokenizer.nextElement().toString());
		    			graph.addEdge(vertexNumber - 1, adjacentVertex - 1);
		    		}
		    	}
		        
		        lineNumber++;
		    }
		    
		} catch(IOException e) {
			throw new GraphInitializationException("Unable to initialize the graph using file path: " + filePath);
		}
	}
	
	/**
	 * This method exposes the twoColour function of the Graph class. This function must
	 * be called after the initializeGraph function.
	 * 
	 * @return boolean result - True if there is a possible seating arrangement or false
	 * if there is no possible seating arrangement.
	 */
	public boolean twoColour() {
		return graph.twoColour();
	}
	
	/**
	 * This method exposes the easyColour function of the Graph class. This function must
	 * be called after the initializeGraph function.
	 * 
	 * @return int[] vertexColours - An array of integers whose values represent the colour
	 * of the node at the corresponding index + 1 (i.e. the int located at index 0 of vertexColours
	 * is the colour for vertex 1 in the graph).
	 */
	public int[] easyColour() {
		return graph.easyColour();
	}
	
}
