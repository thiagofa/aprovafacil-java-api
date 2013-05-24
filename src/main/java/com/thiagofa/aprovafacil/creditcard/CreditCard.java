package com.thiagofa.aprovafacil.creditcard;

public class CreditCard {

	private Brand brand;
	private String number;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer securityCode;
	private CreditCardHolder holder;
	
	public enum Brand {
		VISA, MASTERCARD, DINERS, AMEX, HIPERCARD, JCB, SOROCRED, AURA
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	public Integer getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}

	public CreditCardHolder getHolder() {
		return holder;
	}

	public void setHolder(CreditCardHolder holder) {
		this.holder = holder;
	}

}