package trackr;

/**
 * Represents an exception specific to the Trackr application.
 * Used to signal errors related to invalid user input or command execution.
 */
public class TrackrException extends Exception {

    /**
     * Creates a TrackrException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public TrackrException(String message) {
        super(message);
    }
}
