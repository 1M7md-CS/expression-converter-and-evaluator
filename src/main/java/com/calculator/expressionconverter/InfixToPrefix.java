package com.calculator.expressionconverter;


import com.calculator.expressionutils.ExpressionValidator;
import com.calculator.expressionutils.OperatorHandler;

public class InfixToPrefix {

	public static String convert(String expression) {

		expression = expression.replaceAll("\\s+", "");

		ExpressionValidator.validateNotNullOrEmpty(expression);
		ExpressionValidator.validateParentheses(expression);
		ExpressionValidator.validateCharacters(expression);

		boolean hasOperator = false;

		for (int i = 0; i < expression.length(); i++) {

			if (OperatorHandler.isOperator(expression.charAt(i))) {
				hasOperator = true;
				break;
			}

		}

		if (!hasOperator && expression.length() > 1) {
			throw new IllegalArgumentException("Expression must contain at least one operator.");
		}

		String reversedExpression = reverseExpression(expression);
		String postfix = InfixToPostfix.convert(reversedExpression);
		String result = new StringBuilder(postfix).reverse().toString();

		if (result.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid expression: conversion resulted in empty output.");
		}

		return result;
	}

	private static String reverseExpression(String expression) {

		return new StringBuilder(expression)
				.reverse()
				.toString()
				.replace('(', '#')
				.replace(')', '(')
				.replace('#', ')');

	}

}