package model;

import java.util.Date;

public class DownloadedProduct {

	private int product_id;
	private int user_id;
	private Date last_download;

	public DownloadedProduct(int user_id, int product_id, Date last_download) {
		super();
		this.product_id = product_id;
		this.user_id = user_id;
		this.last_download = last_download;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getLast_download() {
		return last_download;
	}

	public void setLast_download(Date last_download) {
		this.last_download = last_download;
	}
}
