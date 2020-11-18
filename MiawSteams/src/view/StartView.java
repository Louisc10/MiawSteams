package view;

import utility.Utility;

public class StartView extends AbstractView {

	private static StartView instance = null;

	private int menu = 0;

	public static StartView getInstance() {
		if (instance == null) {
			synchronized (StartView.class) {
				if (instance == null) {
					instance = new StartView();
				}
			}
		}
		return instance;
	}

	private StartView() {

	}

	@Override
	public void doShow() {
		cls();
		Utility.printLogo();
		System.out.println("Start Page");
		System.out.println("==============");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("0. Exit");
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
