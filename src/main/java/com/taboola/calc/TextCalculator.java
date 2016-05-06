package com.taboola.calc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TextCalculator {
	private static final String			SPACE_DELIMITER			= " ";
	private static final int			START_CALCULATION_INDEX	= 2;
	private HashMap<String, Integer>	variableMap				= new HashMap<>();

	private InputValidator				inputValidator			= new InputValidator();
	private PostfixCalculator			postfixCalculator		= new PostfixCalculator();
	private InfixToPostfixConvertor		infixToPostfixConvertor	= new InfixToPostfixConvertor();

	public void printVariables() {
		System.out.println(variableMap.toString());
	}
	
	public void calculate(String input) {
		List<String> inputElements = createElementList(input);
		inputValidator.basicValidation(inputElements);
		
		List<String> assignmentElements  = inputElements.subList(0, START_CALCULATION_INDEX);
		List<String> calculationElements = inputElements.subList(START_CALCULATION_INDEX, inputElements.size());
		inputValidator.validateCalulationPart(calculationElements);
		calculationElements = replaceVariablesToNumbers(calculationElements);
		int calculationResult = performCalculation(calculationElements);
		
		assingnFinalValue(assignmentElements, calculationResult);
	}
	
	private void assingnFinalValue(List<String> assignmentElements, int calculationResult) {
		String assignmentVariable = assignmentElements.get(InputValidator.INDEX_OF_ASSIGNMENT_VALUE);
		String assignmentElement = assignmentElements.get(InputValidator.INDEX_OF_EQUALS);
		
		if (isAugmentedAssignment(assignmentElement)) 
			handleAugmentedAssignment(assignmentVariable,assignmentElement,calculationResult);
		else 
			variableMap.put(assignmentVariable, calculationResult);	
	}

	private void handleAugmentedAssignment(String assignmentVariable, String assignmentElement, int calculationResult) {
		
		int currentValue = getVariableValue(assignmentVariable);
		String operation = getAugmentedAssignmentOperation(assignmentElement);
		
		switch (operation) {
		case "*":
			currentValue = currentValue * calculationResult;
			break;
		case "+":
			currentValue = currentValue + calculationResult;
			break;
		default:
			throw new OperationFailedException("Not supported operation applied:" + operation);
		}
		
		variableMap.put(assignmentVariable, currentValue);	
	}

	private String getAugmentedAssignmentOperation(String assignmentElement) {
		return assignmentElement.split("=")[0];
	}

	private int performCalculation(List<String> inputElements) {
		List<String> postfixList = infixToPostfixConvertor.convertToPostfix(inputElements);
		return postfixCalculator.calculate(postfixList);
	}

	public int getVariableValue (String variableName) {
		Integer value = variableMap.get(variableName);
		if (value == null)
			throw new OperationFailedException("No variable '" + variableName + "' was found in the memory");
		
		return value.intValue();
	}
	
	private List<String> createElementList (String line) {
		return Arrays.asList(line.split(SPACE_DELIMITER));
	}
	
	private List<String> replaceVariablesToNumbers (List<String> inputElements) {
		
		for (int i=0; i<inputElements.size(); i++) {
			String element = inputElements.get(i);
			if (inputValidator.isValidIncrement(element)) {
				element = handleIncrementDecrement(element);
				inputElements.set(i, element);
			} 
			else if (StringUtils.isAlpha(element)) {
				element = String.valueOf(getVariableValue(element));
				inputElements.set(i, element);
			}
		}
		
		return inputElements;
	}
	
	private String handleIncrementDecrement(String element) {		
		if (element.contains("++")) {
			String [] incVarArray = element.split("\\+\\+");
			if (element.startsWith("++")) {
				String variable = incVarArray[1];
				int value = getVariableValue(variable);
				variableMap.put(variable, ++value);
				return String.valueOf(value);
			}
			else {
				String variable = incVarArray[0];
				int value = getVariableValue(variable);
				variableMap.put(variable, value + 1);
				return String.valueOf(value);
			}
		} 
		else {
			String [] decVarArray = element.split("--");
			if (element.startsWith("--")) {
				String variable = decVarArray[1];
				int value = getVariableValue(variable);
				variableMap.put(variable, ++value);
				return String.valueOf(value);
			}
			else {
				String variable = decVarArray[0];
				int value = getVariableValue(variable);
				variableMap.put(variable, value + 1);
				return String.valueOf(value);
			}
		}
	}

	private boolean isAugmentedAssignment (String element) { 
		if (element.matches("^[\\+\\-\\/\\*]=$"))
			return true;
		
		return false;
	}
	
	public void processMultipuleLineInput (String input) {
		String[] lines = input.split(System.getProperty("line.separator"));
		for (String line : lines) {
			calculate(line);
		}
		printVariables();
	}
}
