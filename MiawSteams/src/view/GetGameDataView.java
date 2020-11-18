package view;

public class GetGameDataView extends AbstractView {

	private static GetGameDataView instance = null;

	private String name;
	private String description;
	private int size;
	private int id;

	public static GetGameDataView getInstance() {
		if (instance == null) {
			synchronized (GetGameDataView.class) {
				if (instance == null) {
					instance = new GetGameDataView();
				}
			}
		}
		return instance;
	}

	private GetGameDataView() {

	}

	@Override
	public void doShow() {
		cls();
		System.out.print("Input Game Name [At least 6 Character, Must be Unique | Exit]: ");
		name = scan.nextLine();
	}

	public void doAskDesc() {
		cls();
		System.out.print("Input Game Description [10 - 100 Character]: ");
		description = scan.nextLine();
	}

	public void doAskSize() {
		cls();
		System.out.print("Input Game Size [1 - 314572800 kB (kiloByte), without kB]: ");
		size = getInt();
	}

	public void doAskId() {
		System.out.print("Input Game Id [0 to exit]: ");
		id = getInt();
	}

	@Override
	public String requestData(String key) {
		if (key.equals("Name")) {
			return name;
		} else if (key.equals("Description")) {
			return description;
		} else if (key.equals("Size")) {
			return size + "";
		} else if (key.equals("Id")) {
			return id + "";
		}

		return null;
	}
}
