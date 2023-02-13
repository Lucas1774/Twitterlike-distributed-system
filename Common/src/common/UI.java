package common;

import java.util.Scanner;

public class UI {
	private static  Scanner input = new Scanner (System.in);
	
	public static int askforInt (String question) {
		int answer;
		System.out.println(question);
		answer = input.nextInt();
		return answer;
	}
	public static String askforString(String question){
		String answer;
		System.out.println(question);
		answer = input.next();
		return answer;
	}
	public static String askforSentence(String question){
		String answer;
		System.out.println(question);
		input.nextLine();
		answer = input.nextLine();
		return answer;
	}
}