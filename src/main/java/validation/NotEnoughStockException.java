package validation;

@SuppressWarnings("serial")
public class NotEnoughStockException extends Exception {
	public NotEnoughStockException(String message) {
        super(message);
    }
}
