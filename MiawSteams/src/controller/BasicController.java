package controller;

import helper.MasterHelper;
import program.MainProgram;
import view.DownloadView;
import view.MainMenuView;
import view.SpeedConfigView;
import view.StartView;
import view.UpdateUserDataView;

public class BasicController {

	private static BasicController instance = null;

	public static BasicController getInstance() {
		if (instance == null) {
			synchronized (BasicController.class) {
				if (instance == null) {
					instance = new BasicController();
				}
			}
		}
		return instance;
	}

	private UserController uc = null;
	private ProductController pc = null;
	private DownloadController dc = null;

	private BasicController() {
		uc = new UserController();
		pc = new ProductController();
		dc = new DownloadController();
	}

	public void doStart() {
		MasterHelper.getInstance().seeding();

		while (MainProgram.isRunning == true) {
			if (MainProgram.currentUser != null) {
				doMainMenu();
			} else {
				StartView.getInstance().doShow();
				int menu = Integer.parseInt(StartView.getInstance().requestData("Menu"));
				if (menu == 1) {
					uc.login();
				} else if (menu == 2) {
					uc.register();
				} else if (menu == 0) {
					MainProgram.isRunning = false;
				} else {
					System.err.println("Wrong Input! Press enter to continue");
					StartView.getInstance().getEnter();
				}
			}
		}
	}

	private void doMainMenu() {
		while (MainProgram.currentUser != null) {
			MainMenuView.getInstance().doShow();
			int menu = Integer.parseInt(MainMenuView.getInstance().requestData("Menu"));
			if (menu == 1) {
				pc.addProduct();
			} else if (menu == 2) {
				MainMenuView.getInstance().cls();
				pc.updateProduct();
			} else if (menu == 3) {
				MainMenuView.getInstance().cls();
				pc.deleteProduct();
			} else if (menu == 4) {
				doDownloadGame();
			} else if (menu == 5) {
				doUpdateProfile();
			} else if (menu == 6) {
				doConfigureSpeed();
			} else if (menu == 0) {
				MainProgram.currentUser = null;
				return;
			} else {
				System.err.println("Wrong Input! Press enter to continue");
				MainMenuView.getInstance().getEnter();
			}
		}
	}

	private void doDownloadGame() {
		while (true) {
			DownloadView.getInstance().doShow();
			int menu = Integer.parseInt(DownloadView.getInstance().requestData("Menu"));
			if (menu == 1) {
				dc.viewProductByName();
			} else if (menu == 2) {
				dc.viewProductByDate();
			} else if (menu == 3) {
				dc.viewProductBySize();
			} else if (menu == 0) {
				return;
			} else {
				System.err.println("Wrong Input! Press enter to continue");
				UpdateUserDataView.getInstance().getEnter();
			}
		}
	}

	private void doUpdateProfile() {
		while (true) {
			UpdateUserDataView.getInstance().doShow();
			int menu = Integer.parseInt(UpdateUserDataView.getInstance().requestData("Menu"));
			if (menu == 1) {
				uc.updateUser(MainProgram.currentUser.getId());
			} else if (menu == 2) {
				uc.updatePassword(MainProgram.currentUser.getId());
			} else if (menu == 0) {
				return;
			} else {
				System.err.println("Wrong Input! Press enter to continue");
				UpdateUserDataView.getInstance().getEnter();
			}
		}
	}

	private void doConfigureSpeed() {
		while (true) {
			SpeedConfigView.getInstance().doShow();
			String menu = SpeedConfigView.getInstance().requestData("Menu");
			if (menu.equals("Yes")) {
				break;
			} else if (menu.equals("No")) {
				return;
			}
		}

		while (true) {
			SpeedConfigView.getInstance().doAskSpeed();
			int speed = Integer.parseInt(SpeedConfigView.getInstance().requestData("Speed"));
			if (speed < 20000) {
				System.err.println("Speed must higher than equals 20000");
			} else if (speed > 500000) {
				System.err.println("Speed must lower than equals 500000");
			} else {
				MainProgram.currentSpeed = speed;
				return;
			}
			SpeedConfigView.getInstance().getEnter();
		}

	}

}
