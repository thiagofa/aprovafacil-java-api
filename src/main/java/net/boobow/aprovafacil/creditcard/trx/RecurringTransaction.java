package net.boobow.aprovafacil.creditcard.trx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.creditcard.Authorization;

public class RecurringTransaction extends Transaction {

	private String lastTransactionNumber;
	private BigDecimal amount;
	private Integer installments;
	private Boolean installmentByAdmin;
	private Boolean utf8Output;
	
	public Authorization authorizeFunds() throws IOException, JAXBException {
		this.addParametersToService();
		
		String xml = this.aprovaFacilService.post();
		
		return this.xmlParser.parseAuthorization(xml);
	}
	
	private void addParametersToService() throws UnsupportedEncodingException {
		this.addParameterToService("TransacaoAnterior", this.lastTransactionNumber, 0, null);
		this.addParameterToService("ValorDocumento", this.amount, 0, null);
		this.addParameterToService("QuantidadeParcelas", this.installments, 0, null);
		this.addParameterToService("ParcelamentoAdministradora", this.getInstallmentByAdmin(), 0, null);
		this.addParameterToService("ResponderEmUTF8", true, 0, null);
	}
	
	public String getLastTransactionNumber() {
		return lastTransactionNumber;
	}

	public void setLastTransactionNumber(String lastTransactionNumber) {
		this.lastTransactionNumber = lastTransactionNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public Boolean getInstallmentByAdmin() {
		return installmentByAdmin;
	}

	public void setInstallmentByAdmin(Boolean installmentByAdmin) {
		this.installmentByAdmin = installmentByAdmin;
	}

	public Boolean getUtf8Output() {
		return utf8Output;
	}
	
	public void setUtf8Output(Boolean utf8Output) {
		this.utf8Output = utf8Output;
	}
	
}