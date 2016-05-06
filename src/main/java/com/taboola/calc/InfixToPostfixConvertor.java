package com.taboola.calc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

public class InfixToPostfixConvertor {
	
	private static final int LOW_PRIORITY = 1;
	private static final int HiGH_PRIORITY = 2;

	private Deque<String>	operationStack	= new ArrayDeque<String>();

	public List<String> convertToPostfix(List<String> infixList) {

		List<String> postfixList = new ArrayList<>();
		for (String element : infixList) {
			if (NumberUtils.isNumber(element))
				postfixList.add(element);
			else
				inputToStack(element,postfixList);
		}
		flushStack(postfixList);

		return postfixList;
	}

	private void inputToStack(String operation, List<String> postfixList) {
		if (operationStack.isEmpty())
			operationStack.push(operation);
		else {

			while (!operationStack.isEmpty() && getPriority(operation) <= getPriority(operationStack.peek())) {
				postfixList.add(operationStack.pop());
			}
			operationStack.push(operation);
		}
	}

	private int getPriority(String operation) {
		if (operation.equals("+") || operation.equals("-"))
			return LOW_PRIORITY;
		else if (operation.equals("*") || operation.equals("/"))
			return HiGH_PRIORITY;
		else 
			throw new OperationFailedException("Illegal or not supported operation");
	}

	private void flushStack(List<String> postfixList) {
		while (!operationStack.isEmpty()) {
			postfixList.add(operationStack.pop());
		}
	}
}
