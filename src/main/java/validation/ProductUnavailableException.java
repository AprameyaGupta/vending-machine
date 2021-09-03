package validation;

@SuppressWarnings("serial")
public class ProductUnavailableException extends Exception {
	public ProductUnavailableException(String message) {
		super(message);
	}
}
