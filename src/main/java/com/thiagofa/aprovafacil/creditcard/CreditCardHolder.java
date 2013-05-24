package com.thiagofa.aprovafacil.creditcard;

import java.util.Date;

public class CreditCardHolder {

	private String federalTaxId;
	private String name;
	private Date birthDate;
	private String motherName;
	
	public String getFederalTaxId() {
		return federalTaxId;
	}
	public void setFederalTaxId(String federalTaxId) {
		this.federalTaxId = federalTaxId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
}