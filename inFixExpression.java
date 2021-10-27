package project_2;

import java.util.Stack;


//from infix to postfix
public class inFixExpression {

	public static String turnToPostFix(String expString) {
		
		String noSpaceExpression = expString.replaceAll("\\s", "");    //Remove all spaces from string
		String postFix = "";
		
		//Create new stack
		Stack<String> operatorStack = new Stack<>();
		
		
		for (int i = 0; i < noSpaceExpression.length(); i++) {
			String token = Character.toString(noSpaceExpression.charAt(i));  //Create token to hold each character
			
			if (isDigit(token)) {   //If isDigit = true, append token to postFix.
				postFix += token;
				
				
			}
			
			else if (token.equals("-") && isNegative(i, noSpaceExpression)) { //If token is "-" or isNegative = true, append to postFix.
				postFix += token;
				
			}
			
			else if (isParenthesis(token)) {   //If token is a opening parenthesis, add token to stack.
				if (token.equals("(")) {
					operatorStack.add(token);
				}
				else {
					while (!operatorStack.peek().equals("(")) {  //If token is not an opening parenthesis, pop it and add a space in postFix
						postFix += " " + operatorStack.pop();    //to separate operands.
						
						
					}
					operatorStack.pop();
				}
				
				
			}
			
			
			//Token is operator
			else { 						
				postFix += " ";  //Add a space to separate operator from operand.
				
				if (isMultiOperator(i, noSpaceExpression)) {
					token += Character.toString(noSpaceExpression.charAt(i + 1));  //Grab the first and second characters if isMultiOperator = true.
					i++;  //Increment i to skip the second multiOperator char in the for loop.
					
				}
				
				if (operatorStack.isEmpty()) {
					operatorStack.add(token);
				}
				
				else {
					int currentPrecedence = precedence(token);  //Current token precedence.
					int topStackPrecedence;                     //Top of stack precedence.
					
					if (!operatorStack.peek().equals("(")) {
						topStackPrecedence = precedence(operatorStack.peek());
					}
					else {
						topStackPrecedence = -1;
					}
					
					if (currentPrecedence <= topStackPrecedence) {
						
						while (!operatorStack.isEmpty() && currentPrecedence <= topStackPrecedence && !operatorStack.peek().equals("(") ) {
							postFix += operatorStack.pop();
							postFix += " ";
							
							if (!operatorStack.isEmpty()) {
								if (!operatorStack.peek().equals("(")) {
									topStackPrecedence = precedence(operatorStack.peek());
								}
								else {
									topStackPrecedence = -1;
								}
			
							}
							else {
								
								topStackPrecedence = -1;
							}
						}
						operatorStack.add(token);
						
						
					}
					
					else {
						operatorStack.add(token);
					}
					
					
				}
				
			}
		}
		while (!operatorStack.isEmpty()) {
			postFix += " " + operatorStack.pop();	
				
		}
		return postFix;
	}
	
	public static boolean isDigit(String token) { 

		char letter = token.charAt(0);
		char zero = '0';
		char nine = '9';
		
		if (letter >= zero && letter <= nine) {
			return true;
		}

		else {
			return false;
		}
		
	}
	
	public static boolean isNegative(int index, String exp) {
		
		if (index == 0) {
			return true;
		}
		
		String token = Character.toString(exp.charAt(index - 1)); 
		
		if (isDigit(token)) { 
			return false;
		}
		
		else {
			return true;
		}
	}
	
	public static boolean isParenthesis(String letter) {
		if (letter.equals("(") || letter.equals(")")){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isMultiOperator(int index, String exp) {
		String token = Character.toString(exp.charAt(index + 1));
		
		if (token.equals("=") || token.equals("&") || token.equals("|")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public static int precedence(String letter) {
		
		
		switch (letter) {
		
			case "^":
			return 7; 
		
			case "*":
			case "/":
			case "%":
			return 6;
		
			case "+":
			case "-":
			return 5;
			
			case ">":
			case ">=":
			case "<":
			case "<=":
			return 4;
		
			case "==":
			case "!=":
			return 3;
		
			case "&&":
			return 2;
		
			case "||":
			return 1;
		
			default: 
			System.out.println("This is an unknown symbol."); 
			return -1;
		}
	}
	


	


	
	


	
	
	
	
	
	
	
}
