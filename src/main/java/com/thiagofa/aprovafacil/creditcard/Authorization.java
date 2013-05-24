package com.thiagofa.aprovafacil.creditcard;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="ResultadoAPC")
public class Authorization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String authorized;
	private String message;
	private String number;
	private String transactionNumber;
	private String maskedCardNumber;
	private String documentNumber;
	private String receiptAcquirer;
	private String nationalityIssuer;
	// address verification system
	private String avsResult;
	private AddressVerification addressVerification;
	private String uniqueSerialNumber;
	private String authorizationResultNumber;
	private String avsResultNumber;
	private String xml;
	
	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}
	
	@XmlElement(name="TransacaoAprovada")
	public String getAuthorized() {
		return this.authorized;
	}
	
	@XmlTransient
	public boolean isAuthorized() {
		return this.authorized != null 
				&& this.authorized.equalsIgnoreCase("true");
	}

	@XmlElement(name="CodigoAutorizacao")
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	@XmlElement(name="ResultadoSolicitacaoAprovacao")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement(name="Transacao")
	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@XmlElement(name="CartaoMascarado")
	public String getMaskedCardNumber() {
		return maskedCardNumber;
	}

	public void setMaskedCardNumber(String maskedCardNumber) {
		this.maskedCardNumber = maskedCardNumber;
	}

	@XmlElement(name="NumeroDocumento")
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	@XmlElement(name="ComprovanteAdministradora")
	public String getReceiptAcquirer() {
		return receiptAcquirer;
	}

	public void setReceiptAcquirer(String receiptAcquirer) {
		this.receiptAcquirer = receiptAcquirer;
	}

	@XmlElement(name="NacionalidadeEmissor")
	public String getNationalityIssuer() {
		return nationalityIssuer;
	}

	public void setNationalityIssuer(String nationalityIssuer) {
		this.nationalityIssuer = nationalityIssuer;
	}

	@XmlElement(name="ResultadoAVS")
	public String getAvsResult() {
		return avsResult;
	}

	public void setAvsResult(String avsResult) {
		this.avsResult = avsResult;
	}

	@XmlElement(name="EnderecoAVS")
	public AddressVerification getAddressVerification() {
		return addressVerification;
	}

	public void setAddressVerification(AddressVerification addressVerification) {
		this.addressVerification = addressVerification;
	}

	@XmlElement(name="NumeroSequencialUnico")
	public String getUniqueSerialNumber() {
		return uniqueSerialNumber;
	}

	public void setUniqueSerialNumber(String uniqueSerialNumber) {
		this.uniqueSerialNumber = uniqueSerialNumber;
	}

	@XmlElement(name="CodigoResultadoAprovacao")
	public String getAuthorizationResultNumber() {
		return authorizationResultNumber;
	}

	public void setAuthorizationResultNumber(String authorizationResultNumber) {
		this.authorizationResultNumber = authorizationResultNumber;
	}

	@XmlElement(name="CodigoResultadoAVS")
	public String getAvsResultNumber() {
		return avsResultNumber;
	}

	public void setAvsResultNumber(String avsResultNumber) {
		this.avsResultNumber = avsResultNumber;
	}

	@XmlTransient
	public String getXml() {
		return this.xml;
	}
	
	public void setXml(String xml) {
		this.xml = xml;
	}
}