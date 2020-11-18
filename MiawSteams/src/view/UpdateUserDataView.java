package view;

import model.User;
import program.MainProgram;

public class UpdateUserDataView extends AbstractView {

	private static UpdateUserDataView instance = null;

	private String oldpassword = "";
	private String newpassword = "";
	private String repassword = "";
	private String name = "";
	private int menu = 0;

	public static UpdateUserDataView getInstance() {
		if (instance == null) {
			synchronized (UpdateUserDataView.class) {
				if (instance == null) {
					instance = new UpdateUserDataView();
				}
			}
		}
		return instance;
	}

	private UpdateUserDataView() {

	}

	@Override
	public void doShow() {
		cls();
		User u = MainProgram.currentUser;
		System.out.println("Login as :");
		System.out.println("Name     : " + u.getName());
		System.out.println("Username : " + u.getUsername());
		System.out.println("");
		System.out.println("1. Update Data");
		System.out.println("2. Change Password");
		System.out.println("0. Exit");
		System.out.print(">> ");
		menu = getInt();
	}

	public void doAskOldpassword() {
		cls();
		System.out.print("Input your Password [Exit]: ");
		oldpassword = scan.nextLine();
	}

	public void doAskNewpassword() {
		cls();
		System.out.print("Input your New Password\n[At least 8 Character, Alphanumeric]: ");
		newpassword= scan.nextLine();
	}

	public void doAskRepassword() {
		System.out.print("Reinput your Password: ");
		repassword = scan.nextLine();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Old Password")) {
			return oldpassword;
		} else if (key.equals("New Password")) {
			return newpassword;
		} else if (key.equals("Repassword")) {
			return repassword;
		} else if (key.equals("Name")) {
			return name;
		} else if (key.equals("Menu")) {
			return menu + "";
		}
		return null;
	}
}
