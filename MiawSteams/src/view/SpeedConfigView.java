package view;

import program.MainProgram;

public class SpeedConfigView extends AbstractView {
	private static SpeedConfigView instance = null;

	private int speed = 0;
	private String menu = "";

	public static SpeedConfigView getInstance() {
		if (instance == null) {
			synchronized (StartView.class) {
				if (instance == null) {
					instance = new SpeedConfigView();
				}
			}
		}
		return instance;
	}

	private SpeedConfigView() {

	}

	@Override
	public void doShow() {
		System.out.println("Current Speed : " + MainProgram.currentSpeed + "kBps");
		System.out.println("Did you want to change it [Yes | No]?");
		System.out.print(">> ");
		menu = scan.nextLine();
	}

	public void doAskSpeed() {
		System.out.println("How fast did you want it [Between 20000 and 500000]?");
		System.out.print(">> ");
		speed = getInt();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Menu")) {
			return menu;
		} else if (key.equals("Speed")) {
			return speed + "";
		}
		return null;
	}

}
