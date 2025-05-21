package com.calculator.cli;

public class ConsoleColors {


	public static final String RESET = "\u001B[0m";
	public static final String BOLD = "\u001B[1m";


	public static final String SOFT_BLUE = "\u001B[38;5;75m";
	public static final String SOFT_GREEN = "\u001B[38;5;114m";
	public static final String SOFT_PURPLE = "\u001B[38;5;146m";
	public static final String SOFT_CYAN = "\u001B[38;5;80m";
	public static final String SOFT_YELLOW = "\u001B[38;5;222m";
	public static final String SOFT_RED = "\u001B[38;5;174m";
	public static final String DARK_GRAY = "\u001B[38;5;240m";


	public static final String FRAME_COLOR = DARK_GRAY;
	public static final String TITLE_COLOR = SOFT_BLUE + BOLD;
	public static final String OPTION_COLOR = SOFT_PURPLE;
	public static final String INPUT_COLOR = SOFT_CYAN;
	public static final String OUTPUT_COLOR = SOFT_GREEN;
	public static final String ERROR_COLOR = SOFT_RED + BOLD;
	public static final String EXIT_COLOR = SOFT_YELLOW + BOLD;


}