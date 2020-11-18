package view;

public class DownloadView extends AbstractView {

	private static DownloadView instance = null;

	private int menu = 0;
	private String action = "";

	public static DownloadView getInstance() {
		if (instance == null) {
			synchronized (DownloadView.class) {
				if (instance == null) {
					instance = new DownloadView();
				}
			}
		}
		return instance;
	}

	private DownloadView() {

	}

	@Override
	public void doShow() {
		cls();
		System.out.println("1. Sort by Name");
		System.out.println("2. Sort by The Newest");
		System.out.println("3. Sort by Size");
		System.out.println("0. Exit ");
		System.out.print(">> ");
		menu = getInt();
	}

	public void doAskActionFirst(String word) {
		System.out.print("Input your action [View (id) | Download (id) | " + word + "Exit]: ");
		action = scan.nextLine();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Menu")) {
			return menu + "";
		} else if (key.equals("Action")) {
			return action;
		}

		return null;
	}

}
