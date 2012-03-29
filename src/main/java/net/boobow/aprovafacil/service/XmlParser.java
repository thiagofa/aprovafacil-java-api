package net.boobow.aprovafacil.service;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.boobow.aprovafacil.creditcard.Authorization;
import net.boobow.aprovafacil.creditcard.SettlementResult;

public class XmlParser {

	public Authorization parseAuthorization(String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Authorization.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Authorization result = (Authorization) unmarshaller.unmarshal(new StringReader(xml));
		
		return result;
	}
	
	public SettlementResult parseSettlementResult(String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(SettlementResult.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		SettlementResult result = (SettlementResult) unmarshaller.unmarshal(new StringReader(xml));
		
		return result;
	}
	
}