package net.boobow.aprovafacil.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.TestUtil;
import net.boobow.aprovafacil.creditcard.Authorization;

import static org.junit.Assert.*;

import org.junit.Test;

public class XmlParserTest {

	@Test
	public void shouldParseExternalXmlCorrectly() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		Authorization authorization = parser.parseAuthorization(TestUtil.loadAuthorizedXml());
		
		System.out.println(authorization.getMessage());
		
		assertTrue(authorization.isAuthorized());
		assertEquals("Autorizacao - 008771", authorization.getMessage());
		assertEquals("008771", authorization.getNumber());
		assertEquals("73458446250265", authorization.getTransactionNumber());
		assertEquals("**** **** **** 8076", authorization.getMaskedCardNumber());
		assertEquals("123", authorization.getDocumentNumber());
		assertEquals("Teste comprovante", authorization.getReceiptAcquirer());
		assertEquals("Brasileiro", authorization.getNationalityIssuer());
		assertEquals("X", authorization.getAvsResult());
		assertEquals("Rua das Cajamandas Azuis", authorization.getAddressVerification().getAddress());
		assertEquals("987", authorization.getAddressVerification().getNumber());
		assertEquals("AP 101", authorization.getAddressVerification().getComplement());
		assertEquals("38400-123", authorization.getAddressVerification().getZipCode());
		assertEquals("12345", authorization.getUniqueSerialNumber());
		assertEquals("00", authorization.getAuthorizationResultNumber());
		assertEquals("11", authorization.getAvsResultNumber());
	}
	
}