package controllers;

import entities.Visitor;

public class DataManager {
	private static DataManager instance = null;
	private Visitor currentUser = null;
	private boolean PreOrder;
	
	private DataManager() {
	}
	
	public static DataManager getDataManager() {
		if(instance == null) 
			instance = new DataManager();
		return instance;
	}

	public Visitor getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Visitor currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isPreOrder() {
		return PreOrder;
	}

	public void setPreOrder(boolean preOrder) {
		PreOrder = preOrder;
	}
	
}
