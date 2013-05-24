package com.thiagofa.aprovafacil.service;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.thiagofa.aprovafacil.creditcard.Authorization;
import com.thiagofa.aprovafacil.creditcard.Settlement;


public class XmlParser {

	public Authorization parseAuthorization(String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Authorization.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Authorization result = (Authorization) unmarshaller.unmarshal(new StringReader(xml));
		result.setXml(xml);
		
		return result;
	}
	
	public Settlement parseSettlementResult(String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Settlement.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Settlement result = (Settlement) unmarshaller.unmarshal(new StringReader(xml));
		result.setXml(xml);
		
		return result;
	}
	
}