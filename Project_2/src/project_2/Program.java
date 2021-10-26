package project_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws IOException {
		FileInputStream inputFile = new FileInputStream("Expressions.txt");
		Scanner scanner = new Scanner(inputFile);	// Input file scanner
		
		while(scanner.hasNext()) {
			String input = scanner.nextLine();
			String postfix = InfixParserMethods.infixToPostfix(input); // convert string to postfix
			System.out.println(InfixParserMethods.evaluatePostfix(postfix)); // evaluate converted string
		}
		
		scanner.close(); // close scanner
		inputFile.close(); // close input file
	}

}
