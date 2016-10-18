/**
 * This class is used to test the output of the twoColour and easyColour methods exposed 
 * by the SeatingManager class. The outputs are explained at 
 * http://sites.cs.queensu.ca/courses/cisc365/Labs/Week%2004%20and%2005/Quick%20Colouring%202016.pdf
 */

import java.nio.file.Paths;
import java.util.Arrays;

public class Test {
	
	public static void main(String[] args) {
		
		// The paths to the graph initialization files
		String[] twoColourGraphs = {"Graph1.txt", "Graph2.txt"};
		String[] easyColourGraphs = {"Graph3.txt", "Graph4.txt", "Graph5.txt"};
		
		SeatingManager manager;
		
		// Two colour the twoColourGraphs
		for(String graphFile : twoColourGraphs) {
			manager = new SeatingManager();
			
			try {
				manager.initializeGraph(Paths.get(graphFile));
			} catch (GraphInitializationException e) {
				e.printStackTrace();
			}
			
			manager.twoColour();
		}
		
		// Easy colour the easyColourGraphs
		for(String graphFile : easyColourGraphs) {
			manager = new SeatingManager();
			
			try {
				manager.initializeGraph(Paths.get(graphFile));
			} catch (GraphInitializationException e) {
				e.printStackTrace();
			}
			
			System.out.println("Solution found:\n" + Arrays.toString(manager.easyColour()) + "\n");
		}
	}

}
