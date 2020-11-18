package controller;

import java.util.Vector;

import database.Database;
import helper.DownloadHelper;
import helper.ProductHelper;
import helper.UserHelper;
import model.DownloadedProduct;
import model.Product;
import program.MainProgram;
import utility.Sorter;
import view.DownloadView;

public class DownloadController {

	@SuppressWarnings("unchecked")
	public void viewProductByName() {
		if (!((Vector<Product>) Database.getInstance().getVector(Product.class)).isEmpty()) {
			Sorter.getInstance().mergeSortBy("Name");
			foo();
		} else {
			System.out.println("No Data!!");
			DownloadView.getInstance().getEnter();
		}
	}

	@SuppressWarnings("unchecked")
	public void viewProductByDate() {
		if (!((Vector<Product>) Database.getInstance().getVector(Product.class)).isEmpty()) {
			Sorter.getInstance().mergeSortBy("Date");
			foo();
		} else {
			System.out.println("No Data!!");
			DownloadView.getInstance().getEnter();
		}
	}

	@SuppressWarnings("unchecked")
	public void viewProductBySize() {
		if (!((Vector<Product>) Database.getInstance().getVector(Product.class)).isEmpty()) {
			Sorter.getInstance().mergeSortBy("Size");
			foo();
		} else {
			System.out.println("No Data!!");
			DownloadView.getInstance().getEnter();
		}
	}

	public void foo() {
		int paginate = 0;
		while (true) {
			@SuppressWarnings("unchecked")
			int size = ((Vector<Product>) Database.getInstance().getVector(Product.class)).size();
			String word = "";
			if ((paginate + 1) * MainProgram.pagination < size) {
				word += "Next | ";
			}
			if (paginate > 0) {
				word += "Prev | ";
			}
			DownloadView.getInstance().cls();
			DownloadHelper.getInstance().displayData(MainProgram.currentUser.getId(), paginate);
			DownloadView.getInstance().doAskActionFirst(word);
			String action = DownloadView.getInstance().requestData("Action");
			if (action.equals("Next")) {
				if (paginate * MainProgram.pagination < size) {
					paginate++;
				}
			} else if (action.equals("Prev")) {
				if (paginate > 0) {
					paginate--;
				}
			} else if (action.equals("Exit")) {
				return;
			} else if (action.startsWith("Download (") && action.endsWith(")")) {
				String x = action.substring("Download (".length(), action.length() - 1);
				int id = -1;
				try {
					id = Integer.parseInt(x);
				} catch (Exception e) {
					continue;
				}
				Product p = ProductHelper.getInstance().getProductById(id);
				if (p != null) {
					DownloadedProduct d = DownloadHelper.getInstance().getDownloaded(MainProgram.currentUser.getId(),
							id);
					if (d != null) {
						if (d.getLast_download().after(p.getUploaded_date())) {
							System.out.println("Your Game is last updated");
							DownloadView.getInstance().getEnter();
							break;
						}
					}

					DownloadView.getInstance().cls();
					int productSize = p.getSize();
					int currProgress = 0;
					while (currProgress < productSize) {
						DownloadView.getInstance().cls();
						currProgress += MainProgram.currentSpeed * Math.random();
						if (currProgress > productSize)
							currProgress = productSize;
						double currPercentage = ((float) currProgress / productSize) * 100;
						System.out.printf("Downloading... %.2f %%\n", currPercentage);
						System.out.println("Progress: " + currProgress + "/" + productSize);
						try {
							Thread.sleep(500);
						} catch (Exception e) {
						}
					}
					System.out.println("Download " + p.getName() + " Complete");
					DownloadHelper.getInstance().newData(MainProgram.currentUser.getId(), id);
					DownloadView.getInstance().getEnter();
				}
			} else if (action.startsWith("View (") && action.endsWith(")")) {
				String x = action.substring("View (".length(), action.length() - 1);
				int id = -1;
				try {
					id = Integer.parseInt(x);
				} catch (Exception e) {
					System.out.println("Couldn't found that id");
					continue;
				}
				Product p = ProductHelper.getInstance().getProductById(id);
				if (p != null) {
					System.out.println("Name        : " + p.getName());
					System.out.println(
							"Creator     : " + UserHelper.getInstance().getUserById(p.getMaker_id()).getName());
					System.out.println("Description : " + p.getDescription());
					System.out.println("Size        : " + p.getSize() + "kB");
					System.out.println("Press enter to Continue");
				} else {
					System.out.println("Couldn't found that id " + id);
				}
				DownloadView.getInstance().getEnter();
			} else {
				System.out.println("Invalid Command!");
				DownloadView.getInstance().getEnter();
			}
		}

	}

}
