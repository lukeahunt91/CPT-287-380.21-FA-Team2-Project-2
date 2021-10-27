package project_2;

import java.util.Stack;

public class postFixExpression extends inFixExpression{
	
	public static String solvePostFix(String postFix) {
		
		
		
		String answerString = "";
		int rightOperand = 0;
		int leftOperand = 0;
		
		Stack<String> operatorStack = new Stack<>();
		
		
		for (int i = 0; i < noSpaceExpression.length(); i++) {
			
			String token = Character.toString(noSpaceExpression.charAt(i));
			
			if (isDigit(token)) {
				operatorStack.add(token);
			}
			
			else {   //Token is an operator
				rightOperand = operatorStack.pop();
			}
		}
		
		
		
		
		
		
		
		
		return postFix;
		
	}
	
	
	
	

}
