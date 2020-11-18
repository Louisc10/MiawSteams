package utility;

import java.util.Date;
import java.util.Vector;

import database.Database;
import model.Product;

public class Sorter {

	private static Sorter instance = null;
	private Database database;

	public static Sorter getInstance() {
		if (instance == null) {
			synchronized (Sorter.class) {
				if (instance == null) {
					instance = new Sorter();
				}
			}
		}
		return instance;
	}
	
	private Sorter(){
		database = Database.getInstance();
	}

	private void mergeBy(Vector<Product> p, int l, int m, int r, String key) {
		int i = l, j = m + 1;
		@SuppressWarnings("unchecked")
		Vector<Product> temp = (Vector<Product>) p.clone();

		while (i <= m && j <= r) {
			if (key.equals("Name")) {
				String leftName = temp.get(i).getName();
				String rightName = temp.get(j).getName();
				if (leftName.compareToIgnoreCase(rightName) <= 0) {
					p.set(l, temp.get(i));
					i++;
				} else {
					p.set(l, temp.get(j));
					j++;
				}
			} else if (key.equals("Date")) {
				Date leftDate = temp.get(i).getUploaded_date();
				Date rightDate = temp.get(j).getUploaded_date();
				if (leftDate.after(rightDate)) {
					p.set(l, temp.get(i));
					i++;
				} else {
					p.set(l, temp.get(j));
					j++;
				}
			} else if (key.equals("Size")) {
				int leftId = temp.get(i).getSize();
				int rightId = temp.get(j).getSize();
				if (leftId < rightId) {
					p.set(l, temp.get(i));
					i++;
				} else {
					p.set(l, temp.get(j));
					j++;
				}
			}
			l++;
		}

		while (i <= m) {
			p.set(l, temp.get(i));
			i++;
			l++;
		}

		while (j <= r) {
			p.set(l, temp.get(j));
			j++;
			l++;
		}
	}

	private void sortBy(Vector<Product> p, int l, int r, String key) {
		if (l < r) {
			int m = (l + r) / 2;
			sortBy(p, l, m, key);
			sortBy(p, m + 1, r, key);
			mergeBy(p, l, m, r, key);
		}
	}

	@SuppressWarnings("unchecked")
	public void mergeSortBy(String key) {
		if (!((Vector<Product>) database.getVector(Product.class)).isEmpty()) {
			sortBy((Vector<Product>) database.getVector(Product.class), 0,
					((Vector<Product>) database.getVector(Product.class)).size() - 1, key);
		} else {
			System.out.println("No Data!!");
		}
	}
}
