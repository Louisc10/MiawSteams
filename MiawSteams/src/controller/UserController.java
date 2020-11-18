package controller;

import helper.UserHelper;
import model.User;
import program.MainProgram;
import utility.Utility;
import view.LoginView;
import view.UpdateUserDataView;
import view.GetUserDataView;

public class UserController {

	public void login() {
		while (true) {
			LoginView.getInstance().doShow();
			String username = LoginView.getInstance().requestData("Username");
			String password = LoginView.getInstance().requestData("Password");
			if (username.equalsIgnoreCase("Exit")) {
				return;
			}
			if (username.isEmpty()) {
				System.err.println("Must fill username field");
			} else if (password.isEmpty()) {
				System.err.println("Must fill password field");
			} else {
				User u = UserHelper.getInstance().validateData(username, password);
				if (u == null) {
					System.err.println("Couldn't find Account with that username and password");
				} else {
					System.out.println("Success Login");
					LoginView.getInstance().getEnter();
					MainProgram.currentUser = u;
					return;
				}
			}
			LoginView.getInstance().getEnter();
		}
	}

	public void register() {
		User u = getData();
		if(u == null){
			return;
		}
		UserHelper.getInstance().newData(u);
		System.out.println("Register Successs");
		GetUserDataView.getInstance().getEnter();
	}

	public User getData() {
		String username = "";
		String password = "";
		String name = "";

		while (true) {
			GetUserDataView.getInstance().doShow();
			username = GetUserDataView.getInstance().requestData("Username");
			if (username.equalsIgnoreCase("Exit")) {
				return null;
			}
			if (username.isEmpty()) {
				System.err.println("Must fill username field");
			} else if (username.length() < 5 || username.length() > 20) {
				System.err.println("Username must between 5 and 20 Characters");
			} else if (!Utility.alphanumberic(username)) {
				System.err.println("Username must Alphanumeric (Contain Alphabet and Number)");
			} else if (UserHelper.getInstance().validateData(username) != null) {
				System.err.println("Username already exist");
			} else {
				break;
			}
			GetUserDataView.getInstance().getEnter();
		}

		while (true) {
			GetUserDataView.getInstance().doAskPassword();
			password = GetUserDataView.getInstance().requestData("Password");

			if (password.isEmpty()) {
				System.err.println("Must fill password field");
			} else if (password.length() < 8) {
				System.err.println("Password must at least 8 character");
			} else if (!Utility.alphanumberic(password)) {
				System.err.println("Password must Alphanumeric (Contain Alphabet and Number)");
			} else {
				GetUserDataView.getInstance().doAskRepassword();
				String repassword = GetUserDataView.getInstance().requestData("Repassword");
				if (!password.equals(repassword)) {
					System.err.println("Confirm password is different");
				} else {
					break;
				}
			}
			GetUserDataView.getInstance().getEnter();
		}

		while (true) {
			GetUserDataView.getInstance().doAskName();
			name = GetUserDataView.getInstance().requestData("Name");

			if (name.isEmpty()) {
				System.err.println("Must fill name field");
			} else if (!Utility.word(name)) {
				System.err.println("Name must only Alphabet");
			} else if (name.length() < 4) {
				System.err.println("Name must at least 4 character");
			} else {
				return new User(name, username, password);
			}
			GetUserDataView.getInstance().getEnter();
		}
	}

	public void updateUser(int id) {
		String name = "";
		while (true) {
			GetUserDataView.getInstance().doAskName();
			name = GetUserDataView.getInstance().requestData("Name");

			if (name.isEmpty()) {
				System.err.println("Must fill name field");
			} else if (!Utility.word(name)) {
				System.err.println("Name must only Alphabet");
			} else if (name.length() < 4) {
				System.err.println("Name must at least 4 character");
			} else {
				UserHelper.getInstance().updateData(id, name);
				System.out.println("User's Data updated");
				GetUserDataView.getInstance().getEnter();
				return;
			}
			GetUserDataView.getInstance().getEnter();
		}
	}

	public void updatePassword(int id) {
		String oldpassword = "";
		String newpassword = "";
		while (true) {
			UpdateUserDataView.getInstance().doAskOldpassword();
			oldpassword = UpdateUserDataView.getInstance().requestData("Old Password");
			if (oldpassword.equalsIgnoreCase("Exit")) {
				return;
			}
			if (oldpassword.isEmpty()) {
				System.err.println("Must fill name field");
			} else if (!oldpassword.equals(MainProgram.currentUser.getPassword())) {
				System.err.println("Invalid old Password");
			} else {
				break;
			}
			GetUserDataView.getInstance().getEnter();
		}

		while (true) {
			UpdateUserDataView.getInstance().doAskNewpassword();
			newpassword = UpdateUserDataView.getInstance().requestData("New Password");

			if (newpassword.isEmpty()) {
				System.err.println("Must fill password field");
			} else if (newpassword.length() < 8) {
				System.err.println("Password must at least 8 character");
			} else if (!Utility.alphanumberic(newpassword)) {
				System.err.println("Password must Alphanumberic (Contain Alphabet and Number)");
			} else {
				UpdateUserDataView.getInstance().doAskRepassword();
				String repassword = UpdateUserDataView.getInstance().requestData("Repassword");
				if (!newpassword.equals(repassword)) {
					System.err.println("Confirm password is different");
				} else {
					
					break;
				}
			}
			UpdateUserDataView.getInstance().getEnter();
		}

	}
}
