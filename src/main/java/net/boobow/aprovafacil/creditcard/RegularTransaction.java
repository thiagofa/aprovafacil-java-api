package net.boobow.aprovafacil.creditcard;

import java.math.BigDecimal;

public class RegularTransaction {

	private String orderNumber;
	private BigDecimal totalAmount;
	private Currency currency;
	private Integer installments;
	private Boolean preAuthorization;
	private CreditCard creditCard;
	private Boolean installmentByAdmin;
	private Acquirer acquirer;
	private String buyerHost;
	private Boolean utf8Output;
	
	public Authorization authorizeFunds() {
		Authorization result = new Authorization();
		result.setAuthorized(this.creditCard.getSecurityCode() == 123);
		return result;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public Boolean getPreAuthorization() {
		return preAuthorization;
	}

	public void setPreAuthorization(Boolean preAuthorization) {
		this.preAuthorization = preAuthorization;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Boolean getInstallmentByAdmin() {
		return installmentByAdmin;
	}

	public void setInstallmentByAdmin(Boolean installmentByAdmin) {
		this.installmentByAdmin = installmentByAdmin;
	}

	public Acquirer getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(Acquirer acquirer) {
		this.acquirer = acquirer;
	}

	public String getBuyerHost() {
		return buyerHost;
	}

	public void setBuyerHost(String buyerHost) {
		this.buyerHost = buyerHost;
	}

	public Boolean getUtf8Output() {
		return utf8Output;
	}

	public void setUtf8Output(Boolean utf8Output) {
		this.utf8Output = utf8Output;
	}

}