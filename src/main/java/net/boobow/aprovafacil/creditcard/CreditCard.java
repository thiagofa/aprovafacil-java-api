package net.boobow.aprovafacil.creditcard;

public class CreditCard {

	private int securityCode;
	
	public enum Brand {
		VISA, MASTERCARD, DINERS, AMEX, HIPERCARD, JCB, SOROCRED, AURA
	}
	
	public int getSecurityCode() {
		return this.securityCode;
	}
	

	public void setNumber(String string) {
		
	}

	public void setExpirationMonth(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setExpirationYear(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public void setHolderName(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setBrand(Brand visa) {
		// TODO Auto-generated method stub
		
	}

}
