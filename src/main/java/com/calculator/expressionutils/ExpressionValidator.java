package com.calculator.expressionutils;

public class ExpressionValidator {

	public static void validateNotNullOrEmpty(String expression) {

		if (expression == null || expression.trim().isEmpty()) {
			throw new IllegalArgumentException("Expression cannot be null or empty.");
		}

	}

	public static void validateParentheses(String expression) {

		if (!BalancedParentheses.isBalanced(expression)) {
			throw new IllegalArgumentException("Error: Unbalanced parentheses in expression!");
		}

	}

	public static void validateCharacters(String expression) {

		for (char ch : expression.toCharArray()) {

			if (!Character.isDigit(ch)
					&& !OperatorHandler.isOperator(ch)
						&& ch != '(' && ch != ')'
							&& !Character.isWhitespace(ch)) {
				throw new IllegalArgumentException("Error: Unsupported character '" + ch + "' in expression. Only numbers and operators are allowed.");
			}
		}

	}

	public static void validatePrefix(String expression) {
		int operandCount = 0;
		String[] tokens = expression.split("\\s+");

		for (int i = tokens.length - 1; i >= 0; i--) {
			String token = tokens[i];
			if (token.isEmpty()) continue;

			if (OperatorHandler.isNumber(token)) {
				operandCount++;
			} else if (OperatorHandler.isOperator(token.charAt(0))) {
				if (operandCount < 2) {
					throw new IllegalArgumentException("Invalid prefix expression: Not enough operands for operator at token '" + token + "'.");
				}
				operandCount--;
			}
		}

		if (operandCount != 1) {
			throw new IllegalArgumentException("Invalid prefix expression: Incorrect number of operands. Expected 1, found " + operandCount + ".");
		}
	}

	public static void validatePostfix(String expression) {
		int operandCount = 0;
		String[] tokens = expression.split("\\s+");

		for (String token : tokens) {
			if (token.isEmpty()) continue;

			if (OperatorHandler.isNumber(token)) {
				operandCount++;
			} else if (OperatorHandler.isOperator(token.charAt(0))) {
				if (operandCount < 2) {
					throw new IllegalArgumentException("Invalid postfix expression: Not enough operands for operator at token '" + token + "'.");
				}
				operandCount--;
			}
		}

		if (operandCount != 1) {
			throw new IllegalArgumentException("Invalid postfix expression: Incorrect number of operands. Expected 1, found " + operandCount + ".");
		}
	}
}