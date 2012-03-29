package net.boobow.aprovafacil.creditcard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;

public class SettlementRequest extends BaseRequest {

	private String documentNumber;
	private String transactionNumber;
	private BigDecimal amount;
	private Boolean utf8Output;
	
	public SettlementResult settle() throws IOException, JAXBException {
		this.addParametersToService();
		
		String xml = this.aprovaFacilService.post();
		
		return this.xmlParser.parseSettlementResult(xml);
	}
	
	private void addParametersToService() throws UnsupportedEncodingException {
		this.addParameterToService("NumeroDocumento", this.documentNumber, 0, null);
		this.addParameterToService("Transacao", this.transactionNumber, 0, null);
		this.addParameterToService("ValorDocumento", this.amount, 0, null);
		this.addParameterToService("ResponderEmUTF8", true, 0, null);
	}
	
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Boolean getUtf8Output() {
		return utf8Output;
	}
	public void setUtf8Output(Boolean utf8Output) {
		this.utf8Output = utf8Output;
	}
	
}