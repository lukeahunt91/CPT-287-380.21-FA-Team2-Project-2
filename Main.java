package project_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	
		
	public static void main(String[] args) throws FileNotFoundException {
		
		
		//FileInputStream inputFile = new FileInputStream("input.txt");
		//Scanner scanner = new Scanner(inputFile);

		
		String inFixExp = " 4 * (7 + 2)";
		String postFix = inFixExpression.turnToPostFix(inFixExp);
		System.out.println(postFix);
	    
		
	}

}
