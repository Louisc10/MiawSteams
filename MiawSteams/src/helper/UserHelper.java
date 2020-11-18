package helper;

import java.util.Vector;

import database.Database;
import model.User;

public class UserHelper {
	private static UserHelper instance = null;
	private Database database;
	private int user_id;

	public static UserHelper getInstance() {
		if (instance == null) {
			synchronized (UserHelper.class) {
				if (instance == null) {
					instance = new UserHelper();
				}
			}
		}
		return instance;
	}

	private UserHelper() {
		database = Database.getInstance();
		user_id = 1;
	}

	private int newId() {
		return user_id++;
	}

	void seedingData() {
		newData("Olaf", "Orchard123", "Snowman1234");
		newData("Moana", "Island954", "Boat1234");
		newData("Elsa", "LittlePrincess54", "Castle1234");
		newData("Anna", "IceQueen1", "Freeze1234");
		newData("Rudolf", "Reindeer687", "Chris1234");
	}

	@SuppressWarnings("unchecked")
	public void newData(String name, String username, String password) {
		((Vector<User>) database.getVector(User.class)).add(new User(newId(), name, username, password));
	}

	@SuppressWarnings("unchecked")
	public void newData(User u) {
		u.setId(newId());
		((Vector<User>) database.getVector(User.class)).add(u);
	}

	@SuppressWarnings("unchecked")
	public User validateData(String username, String password) {
		for (User u : (Vector<User>) database.getVector(User.class)) {
			if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public User validateData(String username) {
		for (User u : (Vector<User>) database.getVector(User.class)) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public User getUserById(int id) {
		for (User u : (Vector<User>) database.getVector(User.class)) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void updateData(int id, String name){
		for (User u : (Vector<User>) database.getVector(User.class)) {
			if (u.getId() == id) {
				u.setName(name);
				return;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updatePassword(int id, String password){
		for (User u : (Vector<User>) database.getVector(User.class)) {
			if (u.getId() == id) {
				u.setPassword(password);
				return;
			}
		}
	}
}
