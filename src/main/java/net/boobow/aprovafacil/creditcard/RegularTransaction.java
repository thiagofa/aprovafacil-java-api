package net.boobow.aprovafacil.creditcard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.boobow.aprovafacil.service.AprovaFacilService;
import net.boobow.aprovafacil.service.AuthorizationXmlParser;

public class RegularTransaction {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
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
	
	private AprovaFacilService aprovaFacilService;
	private AuthorizationXmlParser authorizationXmlParser;

	public RegularTransaction() {
		this.authorizationXmlParser = new AuthorizationXmlParser();
	}
	
	public void setAprovaFacilService(AprovaFacilService aprovaFacilService) {
		this.aprovaFacilService = aprovaFacilService;
	}
	
	public Authorization authorizeFunds() throws IOException {
		this.addParametersToService();
		String xml = this.aprovaFacilService.post();
		
		return this.authorizationXmlParser.parse(xml);
	}
	
	private void addParametersToService() throws UnsupportedEncodingException {
		this.addParameterToService("NumeroDocumento", this.orderNumber, 0, null);
		this.addParameterToService("ValorDocumento", this.totalAmount, 0, null);
		this.addParameterToService("QuantidadeParcelas", this.installments, 0, null);
		this.addParameterToService("NumeroCartao", this.creditCard.getNumber(), 0, null);
		this.addParameterToService("MesValidade", this.creditCard.getExpirationMonth(), 2, "0");
		
		if (this.creditCard.getExpirationYear() != null) {
			String year = this.creditCard.getExpirationYear().toString();
			year = StringUtils.substring(year, 2);
			this.addParameterToService("AnoValidade", year, 0, null);
		}
		
		this.addParameterToService("CodigoSeguranca", this.creditCard.getSecurityCode(), 0, null);
		this.addParameterToService("PreAutorizacao", this.getPreAuthorization(), 0, null);
		this.addParameterToService("EnderecoIPComprador", this.getBuyerHost(), 0, null);
		this.addParameterToService("NomePortadorCartao", this.creditCard.getHolder().getName(), 0, null);
		this.addParameterToService("Bandeira", this.creditCard.getBrand(), 0, null);
		this.addParameterToService("Adquirente", this.getAcquirer(), 0, null);
		this.addParameterToService("CPFPortadorCartao", this.getCreditCard().getHolder()
				.getFederalTaxId(), 0, null);
		this.addParameterToService("DataNascimentoPortadorCartao", this.getCreditCard().getHolder()
				.getBirthDate(), 0, null);
		this.addParameterToService("NomeMaePortadorCartao", this.getCreditCard().getHolder()
				.getMotherName(), 0, null);
		this.addParameterToService("ParcelamentoAdministradora", this.getInstallmentByAdmin(), 0, null);
		this.addParameterToService("Moeda", this.getCurrency(), 0, null);
		this.addParameterToService("ResponderEmUTF8", true, 0, null);
	}
	
	private void addParameterToService(String name, Boolean value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, value.booleanValue() ? "S" : "N",
					size, fillWith);
		}
	}
	
	private void addParameterToService(String name, BigDecimal value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, Double.toString(value.doubleValue()),
					size, fillWith);
		}
	}
	
	private void addParameterToService(String name, Date value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, DATE_FORMAT.format(value),
					size, fillWith);
		}
	}
	
	private void addParameterToService(String name, Integer value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, value.toString(), size, fillWith);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void addParameterToService(String name, Enum value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, value.toString(), size, fillWith);
		}
	}
	
	private void addParameterToService(String name, String value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			if (size > 0) {
				value = StringUtils.leftPad(value, size, fillWith);
			}
			this.aprovaFacilService.addParameter(name, value);
		}
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