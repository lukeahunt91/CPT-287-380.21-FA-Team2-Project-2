package project_2;

import java.util.Scanner;
import java.util.Stack;

public class InfixParserMethods {
	
	public static int precedence(String operator) {
		
		if(operator.equals("^")) {return 7;}
		if(operator.equals("*") || operator.equals("/") || operator.equals("%")) {return 6;}
		if(operator.equals("+") || operator.equals("-")) {return 5;}
		if(operator.equals(">") || operator.equals(">=") || operator.equals("<") || operator.equals("<=")) {return 4;}
		if(operator.equals("==") || operator.equals("!=")) {return 3;}
		if(operator.equals("&&")) {return 2;}
		if(operator.equals("||")) {return 1;}
		return -1;
	
	}
	
	public static String addWhiteSpace(String input) {
		input += " "; // appends extra space to input as buffer for last character
		String output = ""; // initialize string for output
		String numStr = ""; // initialize string for storing numbers
		String multiChar = ""; // initialize string for storing multi-character operators
		
		for(int i = 0; i < input.length(); i++) { // for all characters in input string
			char c = input.charAt(i); // create character that will store that location i
			
			if (!multiChar.equals("") && Character.isDigit(c)) { // if encountering a number while multiChar string is not empty
				output += multiChar + " "; // add multiChar to output followed by a space
				multiChar = ""; // reset multiChar
				numStr += String.valueOf(c); // add to num string
				continue; // continue loop
			} else if (Character.isDigit(c)) { // if character is digit
				numStr += String.valueOf(c); // add to num string
				continue; // continue loop
			} else if (!numStr.equals("") && !Character.isDigit(c)) { // if character is not digit and numStr is not empty
				output += numStr + " "; // append number and a space to output
				numStr  = ""; // reset numStr to empty string
			} // end if-else if
			
			if(!multiChar.equals("")) { // if multiChar is not empty
				String testStr = multiChar + String.valueOf(c); // create string to compare to known cases
				if (testStr.equals(">=") || testStr.equals(">=") || testStr.equals("==") || testStr.equals("!=") || testStr.equals("&&") || testStr.equals("||")) { // if matches known cases 
					output += testStr + " "; // add testStr to output followed by a space
					multiChar = ""; // reset multiChar string
					continue; // continue loop
				} else {
					output += multiChar + " "; // add multiChar to output followed by a space
					multiChar = ""; // reset multiChar string
					continue; // continue loop
				} // end if-else
			} else if(c == '>' || c == '<' || c == '=' || c == '!' || c == '&' || c == '|' ) {
				multiChar += String.valueOf(c); // 
				continue; // continue loop
			} // end if-else if
			
			if(c == ' ') { continue;} // if c is a space, move on to next character
			
			output += String.valueOf(c) + " "; // append character directly to output
		}
	return output;
	}

	public static boolean isInt (String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static String infixToPostfix(String infixString) {
		infixString = addWhiteSpace(infixString); // clean up input
		Stack<String> stack = new Stack<>(); // initialize new stack
		Scanner scanner = new Scanner(infixString); // create scanner to parse string
		String postfixString = "";
		
		while(scanner.hasNext()) {
			String token = scanner.next();
			// if token is a number
			if(isInt(token)) {
				postfixString += token + " "; // add to postfix string
				continue; // move on to next item
			//else if the token is an opening parentheses
			} else if (token.equals("(")) {
				stack.push(token); // push token onto stack
				continue; // move on to next item
			//else if the token is an operator
			} else if (precedence(token) != -1) {
				// while the stack is not empty AND top of stack is not opening parentheses AND precedence of current token <= top of stack
				while(!stack.isEmpty() && !stack.peek().equals("(") && precedence(token)<=precedence(stack.peek())) {
					postfixString += stack.pop() + " ";
				} // end while
				stack.push(token); // push the current token onto the stack
			} else if (token.equals(")")){ // token is closing parentheses
				while(!stack.isEmpty() && !stack.peek().equals("(")) { // while top of stack is not opening parentheses
					postfixString += stack.pop() + " ";
				} // end while
				stack.pop(); // pop opening parentheses off stack
			} else {
				System.out.printf("Operator \"%s\" not recognized. Returned: ", token);
				break;
			} // end else
		} // end while
		// pop remaining items in stack
		while(!stack.isEmpty()) {postfixString += stack.pop() + " ";}
		scanner.close(); // close scanner
		return postfixString; // return string
	}

	public static int evaluatePostfix(String postfixString) {
		Stack<Integer> stack = new Stack<>();
		Scanner scanner = new Scanner(postfixString); // create scanner to parse string
		int result, rightOperand, leftOperand;
		
		// while there are more tokens
		while(scanner.hasNext()) {
			String token = scanner.next(); // get the next token
			// if token is an operand (number)
			if(isInt(token)) {stack.push(Integer.parseInt(token));}
			// else the token is an operator
			else {
				rightOperand = stack.pop(); // pop top of stack to right operand
				leftOperand = stack.pop(); // pop top of stack to left operand
				result = evaluate(leftOperand, rightOperand, token); // evaluate the result
				stack.push(result); // push result onto stack
			} // end if-else
		} // end while
		scanner.close();
		return stack.pop();
	}
	
	public static int evaluate(int left, int right, String operator){
		// Power
		if(operator.equals("^")) {return (int) Math.pow(left, right);}
		//Arithmetic
		if(operator.equals("*")) {return left * right;}
		if(operator.equals("/") && right == 0) {System.out.print("Error: division by zero. Returned: "); return 0;}
		if(operator.equals("/")) {return left / right;}
		if(operator.equals("%")) {return left % right;}
		if(operator.equals("+")) {return left + right;}
		if(operator.equals("-")) {return left - right;}
		// Comparisons
		if(operator.equals(">")) {
			if(left > right) {return 1;}
			else {return 0;}
		}
		if(operator.equals("<")) {
			if(left < right) {return 1;}
			else {return 0;}
		}
		if(operator.equals(">=")) {
			if(left >= right) {return 1;}
			else {return 0;}
		}
		if(operator.equals("<=")) {
			if(left <= right) {return 1;}
			else {return 0;}
		}
		// Equality Comparison
		if(operator.equals("==")) {
			if(left == right) {return 1;}
			else {return 0;}
		}
		if(operator.equals("!=")) {
			if(left != right) {return 1;}
			else {return 0;}
		}
		// Logical And
		if(operator.equals("&&")) {
			if(left>0 && right>0) {return 1;}
			else {return 0;}
		}
		// Logical Or
		if(operator.equals("||")) {
			if(left>0 || right>0) {return 1;}
			else {return 0;}
		}
		System.out.printf("Operator \"%s\" not recognized. Returned: ", operator);
		return 0;
	}
}
