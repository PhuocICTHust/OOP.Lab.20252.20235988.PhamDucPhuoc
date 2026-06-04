package hust.soict.globalict.aims.exception;

/**
 * Thrown when a media item cannot be played, e.g. its length is non-positive.
 */
public class PlayerException extends Exception {

    public PlayerException() {
        super();
    }

    public PlayerException(String message) {
        super(message);
    }
}
