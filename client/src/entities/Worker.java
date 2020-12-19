package entities;

public class Worker {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String role;
	private String workerIdumber;
	
	
	public Worker(String firstName, String lastName, String emailAddress, String role, String workerIdumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.role = role;
		this.workerIdumber = workerIdumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getWorkerIdumber() {
		return workerIdumber;
	}
	public void setWorkerIdumber(String workerIdumber) {
		this.workerIdumber = workerIdumber;
	}
}
