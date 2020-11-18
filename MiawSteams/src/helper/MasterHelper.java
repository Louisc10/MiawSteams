package helper;

public class MasterHelper {

	private static MasterHelper instance = null;
	private static UserHelper userH = null;
	private static ProductHelper productH = null;
	private static DownloadHelper downloadH = null;

	public static MasterHelper getInstance() {
		if (instance == null) {
			synchronized (MasterHelper.class) {
				if (instance == null) {
					instance = new MasterHelper();
				}
			}
		}
		return instance;
	}

	private MasterHelper() {
		userH = UserHelper.getInstance();
		productH = ProductHelper.getInstance();
		downloadH = DownloadHelper.getInstance();
	}

	public void seeding() {
		userH.seedingData();
		productH.seedingData();
		downloadH.seedingData();
	}
}
