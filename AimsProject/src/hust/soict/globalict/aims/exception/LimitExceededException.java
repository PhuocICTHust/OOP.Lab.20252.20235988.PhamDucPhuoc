package hust.soict.globalict.aims.exception;

/**
 * Thrown when an operation would exceed an allowed maximum,
 * e.g. adding more items to the cart than its capacity allows.
 */
public class LimitExceededException extends Exception {

    public LimitExceededException() {
        super();
    }

    public LimitExceededException(String message) {
        super(message);
    }
}
