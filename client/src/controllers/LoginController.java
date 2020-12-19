package controllers;

import entities.Visitor;
import controllers.DataManager;

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
			
		}
		
	}
	
	@Override
	public Object RunQueryOnDB(Object id) {
		//TODO: adding query
	}

}
