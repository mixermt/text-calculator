package com.taboola.calc;

public class CalcMain {
	public static void main(String[] args) {
		
		String input = "y = 5"
				+ "\nx = 4 + 5 * y++";
		TextCalculator textCalculator = new TextCalculator();
		textCalculator.processMultipuleLineInput(input);		
	}
}
