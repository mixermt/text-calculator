package com.taboola.calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

public class PostfixCalculator {
	private Deque<Integer>	stack		= new ArrayDeque<Integer>();

	public int calculate(List<String> postfixList) {
		for (String element : postfixList) {
			if (NumberUtils.isNumber(element)) {
				stack.push(Integer.parseInt(element));
			} else {
				int topElement = stack.pop();
				int secondTopElement = stack.pop();
				int result = 0;

				switch (element) {
					case "+":
						result = secondTopElement + topElement;
						break;
	
					case "-":
						result = secondTopElement - topElement;
						break;
	
					case "*":
						result = secondTopElement * topElement;
						break;
					}
				
				stack.addLast(result);
			}
		}
		return stack.pop();
	}
}
