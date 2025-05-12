package com.calculator.expressionevaluator;

import com.calculator.expressionutils.ExpressionValidator;
import com.calculator.expressionutils.OperatorHandler;

import java.util.Stack;



public class PostfixExpressionEvaluator {

	public static double evaluate(String expression) {
		expression = expression.trim();

		ExpressionValidator.validateNotNullOrEmpty(expression);
		ExpressionValidator.validatePostfix(expression);

		Stack<Double> stack = new Stack<>();
		String[] tokens = expression.split("\\s+");

		if (tokens.length < 3) {
			throw new IllegalArgumentException("Expression must have at least one operator and two operands.");
		}

		boolean hasOperator = false;

		for (String token : tokens) {
			if (token.isEmpty()) continue;

			if (OperatorHandler.isNumber(token)) {

				try {
					stack.push(Double.parseDouble(token));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Invalid number format: " + token);
				}

			} else if (OperatorHandler.isOperator(token.charAt(0))) {

				hasOperator = true;
				if (stack.size() < 2) {
					throw new IllegalArgumentException("Insufficient operands for operator: " + token);
				}

				double secondOperand = stack.pop();
				double firstOperand = stack.pop();

				try {
					double result = OperatorHandler.applyOperator(firstOperand, token.charAt(0), secondOperand);
					stack.push(result);
				} catch (ArithmeticException e) {
					throw new IllegalArgumentException("Arithmetic error: " + e.getMessage());
				}

			} else {
				throw new IllegalArgumentException("Invalid token in expression: " + token);
			}
		}

		if (!hasOperator) {
			throw new IllegalArgumentException("Expression must contain at least one operator.");
		}

		if (stack.size() != 1) {
			throw new IllegalArgumentException("Invalid postfix expression: too many operands.");
		}

		return stack.pop();
	}

}