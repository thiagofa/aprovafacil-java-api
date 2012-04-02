package net.boobow.aprovafacil.creditcard.trx;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.creditcard.trx.SettlementTransaction;
import net.boobow.aprovafacil.service.AprovaFacilService;
import net.boobow.aprovafacil.service.XmlParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class SettlementTransactionTest {

	private SettlementTransaction transaction;
	
	@Mock
	private XmlParser xmlParser;
	
	@Spy
	private AprovaFacilService aprovaFacilService;
	
	@Before
	public void setUp() throws IOException {
		aprovaFacilService = new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
		
		MockitoAnnotations.initMocks(this);
		
		this.transaction = new SettlementTransaction();
		this.transaction.setAprovaFacilService(aprovaFacilService);
		this.transaction.setXmlParser(this.xmlParser);
		
		this.transaction.setDocumentNumber("123");
		this.transaction.setTransactionNumber("XYZ");
		this.transaction.setAmount(new BigDecimal(1.87));
		this.transaction.setUtf8Output(true);
		
		doAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return  "<samplexml/>";
			}
			
		}).when(this.aprovaFacilService).post();
	}
	
	@Test
	public void shouldPostTransaction() throws IOException, JAXBException {
		this.transaction.settle();

		verify(this.aprovaFacilService).post();
	}
	
	@Test
	public void shouldParseXmlResult() throws IOException, JAXBException {
		this.transaction.settle();
		
		verify(this.xmlParser).parseSettlementResult("<samplexml/>");
	}
	
	@Test
	public void shouldAddParametersToService() throws IOException, JAXBException {
		this.transaction.settle();
		
		String[] tokens = new String[] {this.aprovaFacilService.getParameters(), 
				"NumeroDocumento=123", "Transacao=XYZ",
				"ValorDocumento=1.87", "ResponderEmUTF8=S"};
		
		for (String token : tokens) {
			assertTrue(token + " does not exists in parameters", 
					this.aprovaFacilService.getParameters().indexOf(token) > -1);
		}
	}
	
}