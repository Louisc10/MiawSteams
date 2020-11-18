package model;

import java.util.Date;

public class Product {

	private int id;
	private int maker_id;
	private String name;
	private String description;
	private int size;
	private Date uploaded_date;

	public Product(int id, int maker_id, String name, String description, int size, Date uploaded_date) {
		super();
		this.id = id;
		this.maker_id = maker_id;
		this.name = name;
		this.description = description;
		this.size = size;
		this.uploaded_date = uploaded_date;
	}

	public Product(int maker_id, String name, String description, int size, Date uploaded_date) {
		super();
		this.maker_id = maker_id;
		this.name = name;
		this.description = description;
		this.size = size;
		this.uploaded_date = uploaded_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaker_id() {
		return maker_id;
	}

	public void setMaker_id(int maker_id) {
		this.maker_id = maker_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getUploaded_date() {
		return uploaded_date;
	}

	public void setUploaded_date(Date uploaded_date) {
		this.uploaded_date = uploaded_date;
	}

}
