package view;

import java.util.Scanner;

public abstract class AbstractView {

	Scanner scan = new Scanner(System.in);

	public abstract void doShow();

	public abstract String requestData(String key);

	public int getInt() {
		int x = Integer.MIN_VALUE;
		try {
			x = scan.nextInt();
		} catch (Exception e) {
		}
		scan.nextLine();
		return x;
	}

	public void getEnter(){
		scan.nextLine();
	}
	
	public void cls(){
		for(int i = 0; i < 30; i++){
			System.out.println();
		}
	}
}
