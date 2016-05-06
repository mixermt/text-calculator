package com.taboola.calc;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TextCalculatorTest {

	public TextCalculator getCalculator() {
		return new TextCalculator();
	}
	
	@Test
	public void homeWorkAssignmentTest() {
		TextCalculator calculator = getCalculator();

		calculator.processMultipuleLineInput("i = 0\nj = ++i\nx = i++ + 5\ny = 5 + 3 * 10\ni += y");
		Assert.assertTrue(37 == calculator.getVariableValue("i"));
		Assert.assertTrue(1 == calculator.getVariableValue("j"));
		Assert.assertTrue(6 == calculator.getVariableValue("x"));
		Assert.assertTrue(35 == calculator.getVariableValue("y"));
	}
	

	@Test
	public void basicCalculatorTest() {
		TextCalculator calculator = getCalculator();

		calculator.processMultipuleLineInput("x = 3 + 6 * 2\ny = ++x + 3 * 2");
		Assert.assertTrue(16 == calculator.getVariableValue("x"));
		Assert.assertTrue(22 == calculator.getVariableValue("y"));
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * Testing that exception thrown when no variable was created before
	 * augmented assignment
	 */
	@Test
	public void shouldThrowOperationFailedExceptionWhenXNotFound() throws Exception {
		expectedEx.expect(OperationFailedException.class);
		expectedEx.expectMessage("No variable 'x' was found in the memory");

		TextCalculator calculator = getCalculator();
		calculator.processMultipuleLineInput("x += 3 + 6 * 2");
	}
	
	@Test
	public void shouldThrowOperationFailedExceptionOnMinimumSize() throws Exception {
		expectedEx.expect(OperationFailedException.class);
		expectedEx.expectMessage("Incorrect number of elements in input");

		TextCalculator calculator = getCalculator();
		calculator.processMultipuleLineInput("x +=");
	}
	
	@Test
	public void shouldThrowOperationFailedExceptionOnNotSupportedOperation() throws Exception {
		expectedEx.expect(OperationFailedException.class);
		expectedEx.expectMessage("Supported operations are +/*");

		TextCalculator calculator = getCalculator();
		calculator.processMultipuleLineInput("x = 3 & 5");
	}

}
