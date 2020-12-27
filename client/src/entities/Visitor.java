package entities;

public class Visitor extends User{
	private int ID;
	private String Email;
	private String phoneNum;
	private String FirstName;
	private String LastName;
	
	public Visitor() {
	}
	
	public Visitor(int iD, String email, String phoneNum, String firstName, String lastName) {
		super();
		ID = iD;
		Email = email;
		this.phoneNum = phoneNum;
		FirstName = firstName;
		LastName = lastName;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
}
