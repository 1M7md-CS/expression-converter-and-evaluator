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

	private static final String RESET = "\u001B[0m";
	private static final String BOLD = "\u001B[1m";

	private static final String SOFT_BLUE = "\u001B[38;5;75m";
	private static final String SOFT_GREEN = "\u001B[38;5;114m";
	private static final String SOFT_PURPLE = "\u001B[38;5;146m";
	private static final String SOFT_CYAN = "\u001B[38;5;80m";
	private static final String SOFT_YELLOW = "\u001B[38;5;222m";
	private static final String SOFT_RED = "\u001B[38;5;174m";
	private static final String SOFT_GRAY = "\u001B[38;5;250m";
	private static final String DARK_GRAY = "\u001B[38;5;240m";

	private static final String FRAME_COLOR = DARK_GRAY;
	private static final String TITLE_COLOR = SOFT_BLUE + BOLD;
	private static final String OPTION_COLOR = SOFT_PURPLE;
	private static final String INPUT_COLOR = SOFT_CYAN;
	private static final String OUTPUT_COLOR = SOFT_GREEN;
	private static final String ERROR_COLOR = SOFT_RED + BOLD;
	private static final String EXIT_COLOR = SOFT_YELLOW + BOLD;
	private static final String HELP_TEXT_COLOR = SOFT_GRAY;
	private static final String HELP_TITLE_COLOR = SOFT_BLUE + BOLD;

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

		int width = title.length();
		for (String opt : options) {
			width = Math.max(width, opt.length());
		}

		width = Math.max(width, 30);

		printFrame(title, options, width);
	}

	private static int getUserChoice() {

		while (true) {
			System.out.print(INPUT_COLOR + "Choose an option (1-6): " + RESET);

			try {
				String input = reader.readLine().trim();
				if (input.isEmpty()) {
					System.out.println(ERROR_COLOR + "Input cannot be empty. Please enter a number from 1 to 6." + RESET);
					continue;
				}

				int choice = Integer.parseInt(input);

				if (choice >= 1 && choice <= 6) {
					return choice;
				} else {
					System.out.println(ERROR_COLOR + "Invalid option. Please enter a number from 1 to 6." + RESET);
				}

			} catch (NumberFormatException | IOException e) {
				System.out.println(ERROR_COLOR + "Invalid input. Please enter a valid number." + RESET);
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
						System.out.println(ERROR_COLOR + "Invalid option. Please enter a number from 1 to 6." + RESET);
			}

		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_COLOR + e.getMessage() + RESET);
		}
	}

	private static void handleConvertInfixToPostfix() {

		try {

			System.out.print(INPUT_COLOR + "Enter Infix Notation: " + RESET);
			String infix = reader.readLine();
			String result = InfixToPostfix.convert(infix);
			System.out.println(OUTPUT_COLOR + "Postfix Notation: " + RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ERROR_COLOR + ioEx.getMessage() + RESET);
		}

	}

	private static void handleConvertInfixToPrefix() {

		try {

			System.out.print(INPUT_COLOR + "Enter Infix Notation: " + RESET);
			String infix = reader.readLine();
			String result = InfixToPrefix.convert(infix);
			System.out.println(OUTPUT_COLOR + "Prefix Notation: " + RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ERROR_COLOR + ioEx.getMessage() + RESET);
		}

	}

	private static void handleEvaluatePostfix() {

		try {

			System.out.print(INPUT_COLOR + "Enter Postfix To Evaluate: " + RESET);
			String Postfix = reader.readLine();
			System.out.println(Postfix);
			double result = PostfixExpressionEvaluator.evaluate(Postfix);
			System.out.println(OUTPUT_COLOR + "Result: " + RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ERROR_COLOR + ioEx.getMessage() + RESET);
		}

	}

	private static void handleEvaluatePrefix() {

		try {

			System.out.print(INPUT_COLOR + "Enter Prefix To Evaluate: " + RESET);
			String Prefix = reader.readLine();
			double result = PrefixExpressionEvaluator.evaluate(Prefix);
			System.out.println(OUTPUT_COLOR + "Result: " + RESET + result);

		} catch (IOException ioEx) {
			System.out.println(ERROR_COLOR + ioEx.getMessage() + RESET);
		}
	}

	private static void displayHelp() {
		String title = "Help: Understanding Infix, Postfix, and Prefix Notations";
		String[] lines = {
				HELP_TEXT_COLOR + BOLD + "Infix, Postfix, and Prefix are notations for writing mathematical expressions." + RESET,
				HELP_TEXT_COLOR + BOLD + "- Infix: Operators are written between operands (e.g., 2 + 3)." + RESET,
				HELP_TEXT_COLOR + BOLD + "- Postfix: Operators are written after operands (e.g., 2 3 +)." + RESET,
				HELP_TEXT_COLOR + BOLD + "- Prefix: Operators are written before operands (e.g., + 2 3)." + RESET,
				HELP_TEXT_COLOR + BOLD + "Use this tool to convert between notations or evaluate expressions." + RESET
		};


		int maxLen = title.length();
		for (String line : lines) {
			maxLen = Math.max(maxLen, line.replaceAll("\u001B\\[[;\\d]*m", "").length());
		}

		maxLen = Math.max(maxLen, 40);

		System.out.println(FRAME_COLOR + "┌" + "─".repeat(maxLen + 4) + "┐" + RESET);
		System.out.println(FRAME_COLOR + "│ " + HELP_TITLE_COLOR + centerText(title, maxLen + 2) + RESET + " " + FRAME_COLOR + "│" + RESET);
		System.out.println(FRAME_COLOR + "├" + "─".repeat(maxLen + 4) + "┤" + RESET);

		for (String line : lines) {
			String plainLine = line.replaceAll("\u001B\\[[;\\d]*m", "");
			System.out.println(FRAME_COLOR + "│  " + RESET + line + " ".repeat(maxLen - plainLine.length()) + "  " + FRAME_COLOR + "│" + RESET);
		}

		System.out.println(FRAME_COLOR + "└" + "─".repeat(maxLen + 4) + "┘" + RESET);
	}

	private static void displayExitMessage() {
		String message = "Thank you for using the Expression Converter And Evaluator.";
		int width = message.length();
		width = Math.max(width, 40);

		System.out.println(FRAME_COLOR + "╔" + "═".repeat(width + 4) + "╗" + RESET);
		System.out.println(FRAME_COLOR + "║  " + EXIT_COLOR + centerText(message, width) + FRAME_COLOR + "  ║" + RESET);
		System.out.println(FRAME_COLOR + "╚" + "═".repeat(width + 4) + "╝" + RESET);
	}

	private static void printFrame(String title, String[] options, int width) {

		System.out.println(FRAME_COLOR + "╔" + "═".repeat(width + 6) + "╗" + RESET);
		System.out.println(FRAME_COLOR + "║" + RESET + "   " + TITLE_COLOR + centerText(title, width) + RESET + "   " + FRAME_COLOR + "║" + RESET);
		System.out.println(FRAME_COLOR + "╠" + "═".repeat(width + 6) + "╣" + RESET);

		for (String opt : options) {
			System.out.println(FRAME_COLOR + "║" + RESET + "   " + OPTION_COLOR + opt + RESET + " ".repeat(width - opt.length()) + "   " + FRAME_COLOR + "║" + RESET);
		}

		System.out.println(FRAME_COLOR + "╚" + "═".repeat(width + 6) + "╝" + RESET);
	}

	private static String centerText(String text, int width) {
		if (text.length() >= width) {
			return text;
		}

		int padding = (width - text.length()) / 2;

		return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
	}

	private static void promptEnterKey() {

		try {

			System.out.print(INPUT_COLOR + "Press ENTER to continue..." + RESET);
			reader.readLine();

		} catch (IOException ioEx) {
			System.out.println(ERROR_COLOR + ioEx.getMessage() + RESET);
		}

	}

	private static void closeBufferedReader(){
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(ERROR_COLOR + e.getMessage() + RESET);
		}
	}

}