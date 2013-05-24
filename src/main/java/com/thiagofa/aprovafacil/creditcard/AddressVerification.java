package com.thiagofa.aprovafacil.creditcard;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class AddressVerification implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String address;
	private String number;
	private String complement;
	private String zipCode;
	
	@XmlElement(name="Endereco")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@XmlElement(name="Numero")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@XmlElement(name="Complemento")
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	@XmlElement(name="Cep")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}