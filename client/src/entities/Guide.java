package entities;

public class Guide extends Visitor {

	private int SubscriptorNum;
	private int VisitorNumers;
	private int CreditCardNum;
	
	public Guide() {
		// TODO Auto-generated constructor stub
	}

	public Guide(int subscriptorNum, int visitorNumers, int creditCardNum) {
		super();
		SubscriptorNum = subscriptorNum;
		VisitorNumers = visitorNumers;
		CreditCardNum = creditCardNum;
	}

	public int getSubscriptorNum() {
		return SubscriptorNum;
	}

	public void setSubscriptorNum(int subscriptorNum) {
		SubscriptorNum = subscriptorNum;
	}

	public int getVisitorNumers() {
		return VisitorNumers;
	}

	public void setVisitorNumers(int visitorNumers) {
		VisitorNumers = visitorNumers;
	}

	public int getCreditCardNum() {
		return CreditCardNum;
	}

	public void setCreditCardNum(int creditCardNum) {
		CreditCardNum = creditCardNum;
	}
	
	

}
