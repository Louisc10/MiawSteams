package view;

public class GetUserDataView extends AbstractView {

	private static GetUserDataView instance = null;

	private String username = "";
	private String password = "";
	private String repassword = "";
	private String name = "";

	public static GetUserDataView getInstance() {
		if (instance == null) {
			synchronized (GetUserDataView.class) {
				if (instance == null) {
					instance = new GetUserDataView();
				}
			}
		}
		return instance;
	}

	private GetUserDataView() {

	}

	@Override
	public void doShow() {
		cls();
		System.out.print(
				"Input your username\n[5 - 20 Character, Alphanumeric, Must be Unique | Exit]: ");
		username = scan.nextLine();
	}

	public void doAskPassword() {
		cls();
		System.out.print("Input your Password\n[At least 8 Character, Alphanumeric]: ");
		password = scan.nextLine();
	}

	public void doAskRepassword() {
		System.out.print("Reinput your Password: ");
		repassword = scan.nextLine();
	}

	public void doAskName() {
		cls();
		System.out.print("Input your Name\n[At least 4 Character, Alphabet only]: ");
		name = scan.nextLine();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Username")) {
			return username;
		} else if (key.equals("Password")) {
			return password;
		} else if (key.equals("Repassword")) {
			return repassword;
		} else if (key.equals("Name")) {
			return name;
		}
		return null;
	}
}
