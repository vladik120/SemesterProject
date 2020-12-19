package entities;

public class DataManager {
	private static DataManager instance = null;
	private User currentUser = null;
	private boolean PreOrder;
	
	private DataManager() {
	}
	
	public static DataManager getDataManager() {
		if(instance == null) 
			instance = new DataManager();
		return instance;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isPreOrder() {
		return PreOrder;
	}

	public void setPreOrder(boolean preOrder) {
		PreOrder = preOrder;
	}
	
}
