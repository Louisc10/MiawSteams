package utility;

import program.MainProgram;

public class Utility {

	public static void printLogo() {
		printOneByOne(MainProgram.LOGO);
	}

	public static void printOneByOne(String word) {
		for (Character c : word.toCharArray()) {
			System.out.print(c);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean alphanumberic(String word) {
		int c = 0, i = 0;
		for (Character x : word.toCharArray()) {
			if (!Character.isLetterOrDigit(x)) {
				return false;
			} else if (Character.isLetter(x)) {
				c++;
			} else if (Character.isDigit(x)) {
				i++;
			}
		}
		if (c == 0 || i == 0) {
			return false;
		}
		return true;
	}

	public static boolean word(String word) {
		for (Character x : word.toCharArray()) {
			if (!Character.isLetter(x) && x != ' ') {
				return false;
			}
		}
		return true;
	}
}