package com.calculator.cli;



import com.calculator.expressionconverter.InfixToPostfix;
import com.calculator.expressionconverter.InfixToPrefix;
import com.calculator.expressionevaluator.PostfixExpressionEvaluator;
import com.calculator.expressionevaluator.PrefixExpressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CLIHandler {

	private static final int OPTION_CONVERT_INFIX_TO_POSTFIX = 1;
	private static final int OPTION_CONVERT_INFIX_TO_PREFIX = 2;
	private static final int OPTION_EVALUATE_POSTFIX = 3;
	private static final int OPTION_EVALUATE_PREFIX = 4;
	private static final int OPTION_HELP = 5;
	private static final int OPTION_EXIT = 6;

	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static boolean isRunning = true;


	public static void run() {

		while (isRunning) {

			displayMenu();
			int choice = getUserChoice();
			handleUserChoice(choice);

			if (isRunning) {
				System.out.println();
				promptEnterKey();
			}
		}

		displayExitMessage();
		closeBufferedReader();
	}

	private static void displayMenu() {
		String title = "Welcome to the Expression Converter And Evaluator!";

		String[] options = {
				"1. Convert Infix to Postfix",
				"2. Convert Infix to Prefix",
				"3. Evaluate Postfix Expression",
				"4. Evaluate Prefix Expression",
				"5. Help",
				"6. Exit"
		};

		int longestLineLength = Frame.getLongestLineLength(title, options);
		Frame.printMenuFrame(title,options,longestLineLength,6);
	}

	private static int getUserChoice() {

		while (true) {
			System.out.print(ConsoleColors.INPUT_COLOR + "Choose an option (1-6): " + ConsoleColors.RESET);

			try {
				String input = reader.readLine().trim();
				if (input.isEmpty()) {
					System.out.println(ConsoleColors.ERROR_COLOR + "Input cannot be empty. Please enter a number from 1 to 6." + ConsoleColors.RESET);
					continue;
				}

				int choice = Integer.parseInt(input);

				if (choice >= 1 && choice <= 6) {
					return choice;
				} else {
					System.out.println(ConsoleColors.ERROR_COLOR + "Invalid option. Please enter a number from 1 to 6." + ConsoleColors.RESET);
				}

			} catch (NumberFormatException | IOException e) {
				System.out.println(ConsoleColors.ERROR_COLOR + "Invalid input. Please enter a valid number." + ConsoleColors.RESET);
			}
		}
	}

	private static void handleUserChoice(int choice) {
		try {
			switch (choice) {

				case OPTION_CONVERT_INFIX_TO_POSTFIX -> handleConvertInfixToPostfix();
				case OPTION_CONVERT_INFIX_TO_PREFIX -> handleConvertInfixToPrefix();
				case OPTION_EVALUATE_POSTFIX -> handleEvaluatePostfix();
				case OPTION_EVALUATE_PREFIX -> handleEvaluatePrefix();
				case OPTION_HELP -> displayHelp();
				case OPTION_EXIT -> isRunning = false;
				default ->
						System.out.println(ConsoleColors.ERROR_COLOR + "Invalid option. Please enter a number from 1 to 6." + ConsoleColors.RESET);
			}

		} catch (IllegalArgumentException e) {
			System.out.println(ConsoleColors.ERROR_COLOR + e.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void handleConvertInfixToPostfix() {

		try {

			System.out.print(ConsoleColors.INPUT_COLOR + "Enter Infix Notation: " + ConsoleColors.RESET);
			String infix = reader.readLine();
			String result = InfixToPostfix.convert(infix);
			System.out.println(ConsoleColors.OUTPUT_COLOR + "Postfix Notation: " + ConsoleColors.RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ConsoleColors.ERROR_COLOR + ioEx.getMessage() + ConsoleColors.RESET);
		}

	}

	private static void handleConvertInfixToPrefix() {

		try {

			System.out.print(ConsoleColors.INPUT_COLOR + "Enter Infix Notation: " + ConsoleColors.RESET);
			String infix = reader.readLine();
			String result = InfixToPrefix.convert(infix);
			System.out.println(ConsoleColors.OUTPUT_COLOR + "Prefix Notation: " + ConsoleColors.RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ConsoleColors.ERROR_COLOR + ioEx.getMessage() + ConsoleColors.RESET);
		}

	}

	private static void handleEvaluatePostfix() {

		try {

			System.out.print(ConsoleColors.INPUT_COLOR + "Enter Postfix To Evaluate: " + ConsoleColors.RESET);
			String Postfix = reader.readLine();
			System.out.println(Postfix);
			double result = PostfixExpressionEvaluator.evaluate(Postfix);
			System.out.println(ConsoleColors.OUTPUT_COLOR + "Result: " + ConsoleColors.RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ConsoleColors.ERROR_COLOR + ioEx.getMessage() + ConsoleColors.RESET);
		}

	}

	private static void handleEvaluatePrefix() {

		try {

			System.out.print(ConsoleColors.INPUT_COLOR + "Enter Prefix To Evaluate: " + ConsoleColors.RESET);
			String Prefix = reader.readLine();
			double result = PrefixExpressionEvaluator.evaluate(Prefix);
			System.out.println(ConsoleColors.OUTPUT_COLOR + "Result: " + ConsoleColors.RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ConsoleColors.ERROR_COLOR + ioEx.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void displayHelp() {
		String title = "Help: Understanding Infix, Postfix, and Prefix Notations";
		String[] lines = {
				"Infix, Postfix, and Prefix are notations for writing mathematical expressions.",
				"- Infix: Operators are written between operands (e.g., 2 + 3).",
				"- Postfix: Operators are written after operands (e.g., 2 3 +).",
				"- Prefix: Operators are written before operands (e.g., + 2 3).",
				"Use this tool to convert between notations or evaluate expressions."
		};


		int longestLineLength = Frame.getLongestLineLength(title, lines);
		Frame.printMenuFrame(title,lines,longestLineLength,6);
	}

	private static void displayExitMessage() {
		String message = "Thank you for using the Expression Converter And Evaluator.";
		Frame.printExitFrame(message,6);
	}

	private static void promptEnterKey() {

		try {

			System.out.print(ConsoleColors.INPUT_COLOR + "Press ENTER to continue..." + ConsoleColors.RESET);
			reader.readLine();

		} catch (IOException ioEx) {
			System.out.println(ConsoleColors.ERROR_COLOR + ioEx.getMessage() + ConsoleColors.RESET);
		}

	}

	private static void closeBufferedReader(){
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(ConsoleColors.ERROR_COLOR + e.getMessage() + ConsoleColors.RESET);
		}
	}

}