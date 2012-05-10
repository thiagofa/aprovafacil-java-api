package net.boobow.aprovafacil.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.creditcard.Authorization;
import net.boobow.aprovafacil.creditcard.Settlement;
import net.boobow.aprovafacil.util.Util;

import static org.junit.Assert.*;

import org.junit.Test;

public class XmlParserTest {

	@Test
	public void shouldParseAuthorizationXmlCorrectly() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		Authorization authorization = parser.parseAuthorization(Util.loadAuthorizedXml());
		
		assertTrue(authorization.isAuthorized());
		assertEquals("Autorizacao - 008771", authorization.getMessage());
		assertEquals("008771", authorization.getNumber());
		assertEquals("73458446250265", authorization.getTransactionNumber());
		assertEquals("**** **** **** 8076", authorization.getMaskedCardNumber());
		assertEquals("123", authorization.getDocumentNumber());
		assertEquals("Teste comprovante", authorization.getReceiptAcquirer());
		assertEquals("Brasileiro", authorization.getNationalityIssuer());
		assertEquals("X", authorization.getAvsResult());
		assertEquals("Rua das Cajamangas Azuis", authorization.getAddressVerification().getAddress());
		assertEquals("987", authorization.getAddressVerification().getNumber());
		assertEquals("AP 101", authorization.getAddressVerification().getComplement());
		assertEquals("38400-123", authorization.getAddressVerification().getZipCode());
		assertEquals("12345", authorization.getUniqueSerialNumber());
		assertEquals("00", authorization.getAuthorizationResultNumber());
		assertEquals("11", authorization.getAvsResultNumber());
	}
	
	@Test
	public void shouldParseSettlementXmlCorrectly() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		Settlement result = parser.parseSettlementResult(Util.loadConfirmedSettlementResultXml());
		
		assertEquals("Confirmado%2073263500055432", result.getMessage().trim());
		assertEquals("Comprovante captura", result.getReceiptAcquirer());
	}
	
	@Test
	public void shouldConfirmSuccessfulSettlement() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		Settlement result = parser.parseSettlementResult(Util.loadConfirmedSettlementResultXml());
		
		assertTrue(result.isConfirmed());
	}
	
	@Test
	public void shouldNotConfirmSettlementWithError() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		Settlement result = parser.parseSettlementResult(Util.loadSettlementResultXmlWithError());
		
		assertTrue(!result.isConfirmed());
	}
	
	@Test
	public void shouldSetAuthorizationXml() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		String xml = Util.loadAuthorizedXml();
		Authorization result = parser.parseAuthorization(xml);
		
		assertEquals(result.getXml(), xml);
	}
	
	@Test
	public void shouldSetConfirmedSettlementXml() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		String xml = Util.loadConfirmedSettlementResultXml();
		Settlement result = parser.parseSettlementResult(xml);
		
		assertEquals(result.getXml(), xml);
	}
	
	@Test
	public void shouldSetSettlementXmlWithError() throws JAXBException, IOException {
		XmlParser parser = new XmlParser();
		String xml = Util.loadSettlementResultXmlWithError();
		Settlement result = parser.parseSettlementResult(xml);
		
		assertEquals(result.getXml(), xml);
	}
	
}