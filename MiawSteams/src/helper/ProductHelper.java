package helper;

import java.util.Calendar;
import java.util.Vector;

import database.Database;
import model.Product;

public class ProductHelper {

	private static ProductHelper instance = null;
	private Database database;
	private int product_id;

	public static ProductHelper getInstance() {
		if (instance == null) {
			synchronized (ProductHelper.class) {
				if (instance == null) {
					instance = new ProductHelper();
				}
			}
		}
		return instance;
	}

	private ProductHelper() {
		database = Database.getInstance();
		product_id = 1;
	}

	private int newId() {
		return product_id++;
	}

	void seedingData() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2001, 0, 9, 12, 51);
		newData(2, "Fly Guyz", "Have fun with fly guyz here, Remember to try not to Fly out from the arena.", 154062,
				calendar);
		calendar.set(2003, 6, 14, 1, 12);
		newData(3, "Popper Tower 6", "Beware of the Ultimate number of Balloons. Build your tower and POP the balloon.",
				278648, calendar);
		calendar.set(2009, 6, 14, 5, 59);
		newData(2, "Lavorant", "Number one best FPS Game for PC. Each Character has a unique Technique.",
				8354715, calendar);
		calendar.set(2006, 8, 18, 14, 31);
		newData(1, "Symetry Dash", "Jump and avoid all the obstacles to reach the finish line.", 654813, calendar);
		calendar.set(2007, 10, 16, 15, 12);
		newData(1, "Welcome to World", "Try to enter the Dark Web? Beware watch your surrounding!", 1354721,
				calendar);
		calendar.set(2008, 10, 24, 20, 17);
		newData(3, "Monster Hunting Ground", "World Wide MMORPG. A world with many monsters.", 803648148, calendar);
		calendar.set(2010, 11, 13, 12, 51);
		newData(2, "Pocket Rancher", "Raise and Breed our Magical Creature.", 486484, calendar);
		calendar.set(2010, 11, 23, 12, 51);
		newData(3, "Dame Dame Shop", "Simple and Relax Game. Build your Shop.", 972456, calendar);
		calendar.set(2011, 0, 26, 12, 51);
		newData(1, "Pekamon", "Have your own Pekamon and reach Four Elite.", 384615, calendar);
	}

	@SuppressWarnings("unchecked")
	public void newData(int maker_id, String name, String description, int size, Calendar calendar) {
		((Vector<Product>) database.getVector(Product.class))
				.add(new Product(newId(), maker_id, name, description, size, calendar.getTime()));
	}

	@SuppressWarnings("unchecked")
	public void newData(Product p) {
		p.setId(newId());
		((Vector<Product>) database.getVector(Product.class)).add(p);
	}

	@SuppressWarnings("unchecked")
	public Product validateData(String name) {
		for (Product p : (Vector<Product>) database.getVector(Product.class)) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Product getProductById(int id, int maker_id) {
		for (Product p : (Vector<Product>) database.getVector(Product.class)) {
			if (p.getId() == id && p.getMaker_id() == maker_id) {
				return p;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Product getProductById(int id) {
		for (Product p : (Vector<Product>) database.getVector(Product.class)) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void updateProductById(int id, Product newProduct) {
		for (int i = 0; i < ((Vector<Product>) database.getVector(Product.class)).size(); i++) {
			if (((Vector<Product>) database.getVector(Product.class)).get(i).getId() == id) {
				((Vector<Product>) database.getVector(Product.class)).set(i, newProduct);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteProductById(int id) {
		for (int i = 0; i < ((Vector<Product>) database.getVector(Product.class)).size(); i++) {
			if (((Vector<Product>) database.getVector(Product.class)).get(i).getId() == id) {
				((Vector<Product>) database.getVector(Product.class)).remove(i);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean displayData(int id) {
		boolean any = false;
		for (Product p : (Vector<Product>) database.getVector(Product.class)) {
			if (p.getMaker_id() == id) {
				if(any == false){
					System.out.println("===================================================================================");
					System.out.println("| Id.  | Name                                                   | Size            |");
					System.out.println("===================================================================================");
					any = true;
				}
				System.out.printf("| %4d | %-54s | %12d kB |\n", p.getId(), p.getName(), p.getSize());
			}
		}
		if(any == true){
			System.out.println("===================================================================================");
		}
		else{
			System.out.println("No Data!!");
		}
		
		return any;
	}
}
