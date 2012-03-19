package net.boobow.aprovafacil.creditcard;

import java.math.BigDecimal;

public class NormalTransaction {

	private CreditCard creditCard;
	
	public void setOrderNumber(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setTotalAmount(BigDecimal bigDecimal) {
		// TODO Auto-generated method stub
		
	}

	public void setCurrency(Currency brl) {
		// TODO Auto-generated method stub
		
	}

	public void setInstallments(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setPreAuthorization(Boolean false1) {
		// TODO Auto-generated method stub
		
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setInstallmentByAdmin(Boolean false1) {
		// TODO Auto-generated method stub
		
	}

	public void setAcquirer(Acquirer cielo) {
		// TODO Auto-generated method stub
		
	}

	public void setBuyerHost(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setUtf8Output(Boolean true1) {
		// TODO Auto-generated method stub
		
	}

	public Authorization authorizeFunds() {
		Authorization result = new Authorization();
		result.setAuthorized(this.creditCard.getSecurityCode() == 123 ? true : false);
		return result;
	}

}
