package net.boobow.aprovafacil.creditcard;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="ResultadoCAP")
public class Settlement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String receiptAcquirer;
	private String xml;
	
	@XmlElement(name="ResultadoSolicitacaoConfirmacao")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@XmlElement(name="ComprovanteAdministradora")
	public String getReceiptAcquirer() {
		return receiptAcquirer;
	}
	public void setReceiptAcquirer(String receiptAcquirer) {
		this.receiptAcquirer = receiptAcquirer;
	}
	public boolean isConfirmed() {
		return this.getMessage() != null 
				&& this.getMessage().trim().toLowerCase().startsWith("confirmado");
	}
	
	@XmlTransient
	public String getXml() {
		return this.xml;
	}
	
	public void setXml(String xml) {
		this.xml = xml;
	}
	
}