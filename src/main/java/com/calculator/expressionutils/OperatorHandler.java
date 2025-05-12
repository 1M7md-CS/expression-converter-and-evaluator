package com.calculator.expressionutils;

public class OperatorHandler {

	public static boolean isNumber(String token) {

			try {
				Double.parseDouble(token);
				return true;

			} catch (NumberFormatException e) {
				return false;
			}

		}

	public static boolean isOperator(char element) {

		return element == '+' || element == '-' || element == '*' || element == '/' || element == '^';

	}

	public static double applyOperator(double first, char operator, double second) {

		return switch (operator) {
			case '+' -> first + second;
			case '-' -> first - second;
			case '*' -> first * second;

			case '/' -> {
				if (second == 0) {
					throw new ArithmeticException("Division by zero is not allowed.");
				}
				yield first / second;
			}

			case '^' -> {
				if (first < 0 && second % 1 != 0) {
					throw new IllegalArgumentException("Negative base with non-integer exponent is not allowed.");
				}
				yield Math.pow(first, second);
			}

			default -> throw new IllegalArgumentException("Unknown operator: " + operator);

		};
	}

	public static int priority(char ch) {

		return switch (ch) {
			case '+', '-' -> 1;
			case '*', '/' -> 2;
			case '^' -> 3;
			default -> 0;
		};

	}
}