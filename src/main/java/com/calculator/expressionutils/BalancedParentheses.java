package com.calculator.expressionutils;

import java.util.Stack;

public class BalancedParentheses {

	public static boolean isBalanced(String expression) {
		Stack<Character> stack = new Stack<>();

		for (char ch : expression.toCharArray()) {
			if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}

		return stack.isEmpty();
	}

}