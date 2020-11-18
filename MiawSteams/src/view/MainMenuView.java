package view;

import program.MainProgram;

public class MainMenuView extends AbstractView {

	private static MainMenuView instance = null;

	private int menu = 0;

	public static MainMenuView getInstance() {
		if (instance == null) {
			synchronized (MainMenuView.class) {
				if (instance == null) {
					instance = new MainMenuView();
				}
			}
		}
		return instance;
	}

	private MainMenuView() {

	}

	@Override
	public void doShow() {
		cls();
		System.out.println(
				"Welcome, " + MainProgram.currentUser.getName() + " [" + MainProgram.currentUser.getUsername() + "]");
		System.out.println("==============");
		System.out.println("1. Add Game");
		System.out.println("2. Update Game");
		System.out.println("3. Delete Game");
		System.out.println("4. Download Game");
		System.out.println("5. Update Profile");
		System.out.println("6. Change Speed Download");
		System.out.println("0. Logout");
		System.out.print(">> ");
		menu = getInt();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Menu")) {
			return menu + "";
		}

		return null;
	}
}
