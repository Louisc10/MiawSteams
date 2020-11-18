package view;

public class LoginView extends AbstractView {

	private static LoginView instance = null;

	private String username = "";
	private String password = "";

	public static LoginView getInstance() {
		if (instance == null) {
			synchronized (LoginView.class) {
				if (instance == null) {
					instance = new LoginView();
				}
			}
		}
		return instance;
	}

	private LoginView() {

	}

	@Override
	public void doShow() {
		cls();
		System.out.println("Login Page");
		System.out.println("==============");
		System.out.print("Input your username (type Exit to Close): ");
		username = scan.nextLine();
		if(username.equals("Exit")) return;
		System.out.print("Input your Password: ");
		password = scan.nextLine();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Username")) {
			return username;
		} else if (key.equals("Password")) {
			return password;
		}
		return null;
	}

}
