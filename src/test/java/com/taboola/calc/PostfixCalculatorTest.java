package com.taboola.calc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PostfixCalculatorTest {

	public PostfixCalculator getCalculator() {
		return new PostfixCalculator();
	}

	@Test
	public void postfixCalculationTest() {
		PostfixCalculator calculator = getCalculator();
		List<String> input = new ArrayList<>();

		input.add("2");
		input.add("3");
		input.add("4");
		input.add("*");
		input.add("+");
		input.add("5");
		input.add("-");
		System.out.println("Output for following postfix:" + input.toString() + ", is:" + calculator.calculate(input));
		Assert.assertEquals(9.0, calculator.calculate(input),0);

	}

}
