package controller;

import java.util.Date;
import helper.ProductHelper;
import model.Product;
import program.MainProgram;
import view.GetGameDataView;

public class ProductController {

	public void addProduct() {
		Product p = getData();
		if(p == null){
			return;
		}
		ProductHelper.getInstance().newData(p);
		System.out.println("Game has been added");
		GetGameDataView.getInstance().getEnter();
	}

	public void updateProduct() {
		while (true) {
			if(viewProductByUserId() == false){
				GetGameDataView.getInstance().getEnter();
				return;
			}
			GetGameDataView.getInstance().doAskId();
			int id = Integer.parseInt(GetGameDataView.getInstance().requestData("Id"));
			if (id == 0) {
				return;
			}
			Product p = ProductHelper.getInstance().getProductById(id, MainProgram.currentUser.getId());
			if (p != null) {
				Product p1 = getData();
				if(p1 == null){
					System.out.println("You have Cancelled to update game");
					GetGameDataView.getInstance().getEnter();
					return;
				}
				p1.setId(id);
				ProductHelper.getInstance().updateProductById(id, p1);
				System.out.println("Game with id " + id + " has been updated");
				GetGameDataView.getInstance().getEnter();
				break;
			}
		}

	}

	public void deleteProduct() {
		while (true) {
			if(viewProductByUserId() == false){
				GetGameDataView.getInstance().getEnter();
				return;
			}
			GetGameDataView.getInstance().doAskId();
			int id = Integer.parseInt(GetGameDataView.getInstance().requestData("Id"));
			if (id == 0) {
				return;
			}
			Product p = ProductHelper.getInstance().getProductById(id, MainProgram.currentUser.getId());
			if (p != null) {
				ProductHelper.getInstance().deleteProductById(id);
				System.out.println("Game with id " + id + " has been deleted");
				GetGameDataView.getInstance().getEnter();
				break;
			}
		}
	}

	private boolean viewProductByUserId() {
		GetGameDataView.getInstance().cls();
		return ProductHelper.getInstance().displayData(MainProgram.currentUser.getId());
	}

	private Product getData() {
		String name;
		String description;
		int size;
		while (true) {
			GetGameDataView.getInstance().doShow();
			name = GetGameDataView.getInstance().requestData("Name");
			if (name.equals("Exit")) {
				return null;
			}
			if (name.isEmpty()) {
				System.err.println("Must fill Game name Field");
			} else if (name.length() < 6) {
				System.err.println("Game name must at least 6 Character");
			} else if (ProductHelper.getInstance().validateData(name) != null) {
				System.err.println("Game name already exist");
			} else {
				break;
			}
			GetGameDataView.getInstance().getEnter();
		}

		while (true) {
			GetGameDataView.getInstance().doAskDesc();
			description = GetGameDataView.getInstance().requestData("Description");
			if (description.isEmpty()) {
				System.err.println("Must fill Game description Field");
			} else if (description.length() < 10 || description.length() > 100) {
				System.err.println("Game description must between 10 and 100 Character");
			} else {
				break;
			}
			GetGameDataView.getInstance().getEnter();
		}

		while (true) {
			GetGameDataView.getInstance().doAskSize();
			size = Integer.parseInt(GetGameDataView.getInstance().requestData("Size"));
			if (size < 1 || size > 314572800) {
				System.err.println("Game size must between 1 and 314572800 kB");
			} else {
				return new Product(MainProgram.currentUser.getId(), name, description, size, new Date());
			}
			GetGameDataView.getInstance().getEnter();
		}
	}
}
