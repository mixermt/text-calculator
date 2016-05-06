package com.taboola.calc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class InputValidator {

	public  static final int	MIN_SIZE_OF_ELEMENTS				= 3;
	public  static final int	INDEX_OF_EQUALS						= 1;
	public  static final int	INDEX_OF_ASSIGNMENT_VALUE			= 0;

	// private static final String VALID_ASSIGNMENT_VAR_PATTERN = "^[a-z]+$";
	private static final String	VALID_ASSIGNMENT_PATTERN			= "[\\+\\*\\-]?=";
	private static final String	VALID_ELEMENT_PATTERN				= "([0-9]+)|((\\+\\+|--)?[a-z])|([a-z](\\+\\+|--)?)";
	private static final String	VALID_OPERATION_PATTERN				= "[\\+\\*\\-]";
	private static final String	VALID_INCREMENT_DECREMENT_PATTERN	= "((\\+\\+|--)[a-z])|([a-z](\\+\\+|--))";
	private static final String	INCREMENT_DECREMENT_PATTERN			= ".*(\\+\\+|--).*";

	public void basicValidation(List<String> inputList) {
		if (inputList.size() < MIN_SIZE_OF_ELEMENTS)
			throw new OperationFailedException("Incorrect number of elements in input");
		if (!inputList.get(INDEX_OF_EQUALS).matches(VALID_ASSIGNMENT_PATTERN))
			throw new OperationFailedException("No equals sign before");
		if (!StringUtils.isAlpha(inputList.get(INDEX_OF_ASSIGNMENT_VALUE)))
			throw new OperationFailedException("Assignment value must be textual");
	}
	
	public void validateCalulationPart(List<String> inputList) {
		for (int i=0; i < inputList.size(); i++) {
			if (i % 2 == 0) {
				if (!inputList.get(i).matches(VALID_ELEMENT_PATTERN))
					throw new OperationFailedException("Incorrect format! Please type input in correct format:'operand operation operand ...'");
			}
			else if (!inputList.get(i).matches(VALID_OPERATION_PATTERN))
				throw new OperationFailedException("Supported operations are +/*");
		}
	}

	public void validateElementString(String element) {
		if (!element.matches(VALID_ELEMENT_PATTERN))
			throw new OperationFailedException("Incorrect input, please check again");
	}

	public boolean isValidIncrement(String element) {

		if (element.matches(VALID_INCREMENT_DECREMENT_PATTERN)) {
			return true;
		} else if (element.matches(INCREMENT_DECREMENT_PATTERN))
			throw new OperationFailedException("Illegal increment operation, element:" + element);

		return false;
	}

}
