/**
 * This exception class is thrown when there is an error initializing a new instance
 * of the Graph class.
 */
public class GraphInitializationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	GraphInitializationException(String message) {
		super(message);
	}

}
