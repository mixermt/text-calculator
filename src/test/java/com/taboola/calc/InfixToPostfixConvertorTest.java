package com.taboola.calc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class InfixToPostfixConvertorTest {
	

	public InfixToPostfixConvertor getConvertor() {
		return new InfixToPostfixConvertor();
	}

	@Test
	public void convertorTest() {
		InfixToPostfixConvertor convertor = getConvertor();
		List<String> input = new ArrayList<>();
		
		input.add("2");
		input.add("+");
		input.add("3");
		input.add("*");
		input.add("4");
		input.add("-");
		input.add("5");
		
		Assert.assertEquals("[2, 3, 4, *, +, 5, -]", convertor.convertToPostfix(input).toString());
	}
	
	@Test (expected = OperationFailedException.class)
	public void illegalOperationTest() {
		InfixToPostfixConvertor convertor = getConvertor();
		List<String> input = new ArrayList<>();
		
		input.add("2");
		input.add("+");
		input.add("3");
		input.add("*");
		input.add("4");
		input.add("&");
		input.add("5");
		convertor.convertToPostfix(input);
	}
}
