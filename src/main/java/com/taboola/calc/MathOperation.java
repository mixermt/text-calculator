package com.taboola.calc;

public enum MathOperation {
	
	SUBTRACTION("-"),
	ADDITION("+"),
	MULTIPLICATION("*"),
	INCREMENTATION("++"),
	DECREMENTATION("--");
	
	private String operationString;
	
	private MathOperation(String op) {
		this.operationString = op;
	}
	
	public String getOperationString () {
		return operationString;
	}
	
	public int performOperation (String operation, int left, int right) {
		switch (operation) {
			case "*":
				return left * right;
			case "+":
				return left + right;
			case "-":
				return left - right;
			default:
				throw new OperationFailedException("Illegal operation:" + operation);	
		}
	}
}
