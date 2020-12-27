package controllers;

import entities.DataManager;
import entities.Visitor;
import entities.Worker;

public class LoginController extends AbstractController{
	private int id;
	private String username = "";
	private String password = "";
	private DataManager DM = DataManager.getDataManager();
	private String type;
	
	public void changeScreen() {
		//TODO
	}
	
	public void loginValidation() {
		if(type.equals("Visitor")) {
			//TODO: validate if Guide/Family/Solo
			Visitor current = new Visitor();
			DM.setCurrentUser(current);
		}
		else {
			//TODO: validate if Park Manager/Worker/Department Manager
			Worker current = new Worker(password, password, password, password, password);
			DM.setCurrentUser(current);
		}
		
	}
	
	@Override
	public Object RunQueryOnDB(Object id) {
		//TODO: adding query
	}

}
