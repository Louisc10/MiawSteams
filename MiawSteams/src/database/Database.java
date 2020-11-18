package database;

import java.util.Vector;

import model.*;

public class Database {

	public static Database instance = null;
	private Vector<User> v_user;
	private Vector<Product> v_product;
	private Vector<DownloadedProduct> v_DownloadedProducts;

	public static Database getInstance() {
		if (instance == null) {
			synchronized (Database.class) {
				if (instance == null) {
					instance = new Database();
				}
			}
		}

		return instance;
	}

	private Database() {
		v_user = new Vector<>();
		v_product = new Vector<>();
		v_DownloadedProducts = new Vector<>();
	}

	public Vector<?> getVector(Class<?> keyword) {
		if (keyword == User.class) {
			return v_user;
		} else if (keyword == Product.class) {
			return v_product;
		} else if (keyword == DownloadedProduct.class) {
			return v_DownloadedProducts;
		} else {
			return null;
		}
	}

}
