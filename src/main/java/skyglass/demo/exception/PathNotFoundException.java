package skyglass.demo.exception;

public class PathNotFoundException extends BusinessRuleValidationException {

	private static final long serialVersionUID = 122445109562614970L;

	public PathNotFoundException(String message) {
		super(message);
	}

}
