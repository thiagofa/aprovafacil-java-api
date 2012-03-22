package net.boobow.aprovafacil.creditcard;

public class Authorization {

	private boolean authorized;
	private String number;
	
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	
	public boolean isAuthorized() {
		return this.authorized;
	}

	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

}
