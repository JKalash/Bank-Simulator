
/** Custom exception thrown
 * when user inputs an invalid account ID
 * @author JosephKalash
 */

public class IllegalIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Constructor with message string
	 * 
	 * @param message
	 */
	IllegalIDException(String message) {
		super(message);
	}
}
