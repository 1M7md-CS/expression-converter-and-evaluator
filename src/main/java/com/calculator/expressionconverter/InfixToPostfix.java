package com.calculator.expressionconverter;



import com.calculator.expressionutils.ExpressionValidator;
import com.calculator.expressionutils.OperatorHandler;

import java.util.Stack;

public class InfixToPostfix {

	public static String convert(String expression) {

		expression = expression.replaceAll("\\s+", "");

		ExpressionValidator.validateNotNullOrEmpty(expression);
		ExpressionValidator.validateParentheses(expression);
		ExpressionValidator.validateCharacters(expression);

		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();
		StringBuilder currentNumber = new StringBuilder();
		boolean hasOperator = false;

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isDigit(ch) || ch == '.') {
				handleDigit(ch, i, expression, currentNumber, postfix);

			} else if (ch == '(') {
				stack.push(ch);

			} else if (ch == ')') {
				handleClosingParenthesis(stack, postfix);

			} else if (OperatorHandler.isOperator(ch)) {
				hasOperator = true;
				handleOperator(ch, stack, postfix);

			} else {
				throw new IllegalArgumentException("Invalid character in expression: " + ch);
			}
		}

		if (!hasOperator && expression.length() > 1) {
			throw new IllegalArgumentException("Expression must contain at least one operator.");
		}

		appendRemainingOperators(stack, postfix);

		String result = postfix.toString().trim();
		if (result.isEmpty()) {
			throw new IllegalArgumentException("Invalid expression: conversion resulted in empty output.");
		}

		return result;
	}

	private static void handleDigit(char ch, int currentIndex, String expression, StringBuilder currentNumber, StringBuilder postfix) {
		currentNumber.append(ch);

		if (currentIndex == expression.length() - 1 || (!Character.isDigit(expression.charAt(currentIndex + 1)) && expression.charAt(currentIndex + 1) != '.')) {
			postfix.append(currentNumber).append(" ");
			currentNumber.setLength(0);
		}

	}

	private static void handleClosingParenthesis(Stack<Character> stack, StringBuilder postfix) {
		boolean foundOpeningParenthesis = false;

		while (!stack.isEmpty()) {
			char top = stack.pop();

			if (top == '(') {
				foundOpeningParenthesis = true;
				break;
			}

			postfix.append(top).append(" ");

		}

		if (!foundOpeningParenthesis) {
			throw new IllegalArgumentException("Mismatched closing parenthesis in the expression.");
		}
	}

	private static void handleOperator(char operator, Stack<Character> stack, StringBuilder postfix) {

		while (!stack.isEmpty() && stack.peek() != '(' && OperatorHandler.priority(stack.peek()) >= OperatorHandler.priority(operator)) {
			postfix.append(stack.pop()).append(" ");
		}

		stack.push(operator);
	}

	private static void appendRemainingOperators(Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty()) {
			char top = stack.pop();

			if (top == '(') {
				throw new IllegalArgumentException("Mismatched opening parenthesis in the expression.");
			}

			postfix.append(top).append(" ");
		}
	}

}