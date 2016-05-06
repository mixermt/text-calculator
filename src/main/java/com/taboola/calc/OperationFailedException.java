package com.taboola.calc;

public class OperationFailedException extends RuntimeException {

	private static final long serialVersionUID = -5059287910194920189L;
	
	public OperationFailedException() {
        super();
    }

    public OperationFailedException(final String message) {
        super(message);
    }

    public OperationFailedException(final Throwable cause) {
        super(cause);
    }

    public OperationFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
