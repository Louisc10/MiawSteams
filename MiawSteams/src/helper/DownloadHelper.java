package helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import database.Database;
import model.DownloadedProduct;
import model.Product;
import program.MainProgram;

public class DownloadHelper {

	private static DownloadHelper instance = null;
	private Database database;

	public static DownloadHelper getInstance() {
		if (instance == null) {
			synchronized (DownloadHelper.class) {
				if (instance == null) {
					instance = new DownloadHelper();
				}
			}
		}
		return instance;
	}

	private DownloadHelper() {
		database = Database.getInstance();
	}

	@SuppressWarnings("deprecation")
	void seedingData() {
		newData(2, 3, Date.UTC(105, 3, 15, 0, 0, 0));
		newData(1, 4, Date.UTC(106, 0, 9, 0, 0, 0));
		newData(1, 1, Date.UTC(112, 5, 12, 0, 0, 0));
		newData(4, 2, Date.UTC(104, 3, 4, 0, 0, 0));
		newData(3, 5, Date.UTC(109, 2, 23, 0, 0, 0));
	}

	@SuppressWarnings("unchecked")
	public void newData(int user_id, int product_id) {
		((Vector<DownloadedProduct>) database.getVector(DownloadedProduct.class))
				.add(new DownloadedProduct(user_id, product_id, new Date()));
	}
	
	@SuppressWarnings("unchecked")
	public void newData(int user_id, int product_id, long date) {
		((Vector<DownloadedProduct>) database.getVector(DownloadedProduct.class))
		.add(new DownloadedProduct(user_id, product_id, new Date(date)));
	}

	@SuppressWarnings("unchecked")
	public DownloadedProduct getDownloaded(int user_id, int product_id) {
		for (DownloadedProduct d : (Vector<DownloadedProduct>) database.getVector(DownloadedProduct.class)) {
			if (d.getUser_id() == user_id && d.getProduct_id() == product_id) {
				return d;
			}
		}
		return null;
	}

	private String getStatus(DownloadedProduct d, Product p) {
		if (d == null) {
			return "Available";
		} else if (d.getLast_download().before(p.getUploaded_date())) {
			return "Update";
		} else if (d.getLast_download().after(p.getUploaded_date())) {
			return "Downloaded";
		}
		return "";

	}

	@SuppressWarnings("unchecked")
	public void displayData(int id, int paginate) {
		System.out.println(
				"=====================================================================================================================================");
		System.out.println(
				"| Id.  | Name                                                   | Size            | Creator Username     | Status     | Last Update |");
		System.out.println(
				"=====================================================================================================================================");

		int pagination = MainProgram.pagination;
		for (int i = paginate * pagination; i < (paginate + 1) * pagination
				&& i < ((Vector<Product>) database.getVector(Product.class)).size(); i++) {
			Product p = ((Vector<Product>) database.getVector(Product.class)).get(i);
			String creatorName = UserHelper.getInstance().getUserById(p.getMaker_id()).getUsername();
			String status = getStatus(getDownloaded(id, p.getId()), p);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String date = formatter.format(p.getUploaded_date());
			System.out.printf("| %4d | %-54s | %12d kB | %-20s | %-10s | %11s |\n", p.getId(), p.getName(), p.getSize(),
					creatorName, status, date);
		}
		System.out.println(
				"=====================================================================================================================================");
	}

}
