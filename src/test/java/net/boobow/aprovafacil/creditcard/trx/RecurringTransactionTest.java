package net.boobow.aprovafacil.creditcard.trx;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.service.AprovaFacilService;
import net.boobow.aprovafacil.service.XmlParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class RecurringTransactionTest {

	private RecurringTransaction transaction;
	
	@Mock
	private XmlParser xmlParser;
	
	@Spy
	private AprovaFacilService aprovaFacilService;
	
	@Before
	public void setUp() throws IOException {
		aprovaFacilService = new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
		
		MockitoAnnotations.initMocks(this);
		
		this.transaction = new RecurringTransaction();
		this.transaction.setAprovaFacilService(aprovaFacilService);
		this.transaction.setXmlParser(this.xmlParser);
		
		this.transaction.setLastTransactionNumber("123456");
		this.transaction.setAmount(new BigDecimal(1.82));
		this.transaction.setInstallments(1);
		this.transaction.setInstallmentByAdmin(Boolean.FALSE);
		this.transaction.setUtf8Output(true);
		
		doAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return  "<samplexml/>";
			}
			
		}).when(this.aprovaFacilService).post();
	}
	
	@Test
	public void shouldPostTransaction() throws IOException, JAXBException {
		this.transaction.authorizeFunds();

		verify(this.aprovaFacilService).post();
	}
	
	public void shouldParseXmlResult() throws IOException, JAXBException {
		this.transaction.authorizeFunds();
		
		verify(this.xmlParser).parseAuthorization("<samplexml/>");
	}
	
	@Test
	public void shouldAddParametersToService() throws IOException, JAXBException {
		this.transaction.authorizeFunds();
		
		String[] tokens = new String[] {this.aprovaFacilService.getParameters(), 
				"TransacaoAnterior=123456", "ValorDocumento=1.82",
				"QuantidadeParcelas=1", "ParcelamentoAdministradora=N",
	            "ResponderEmUTF8=S"};
		
		for (String token : tokens) {
			assertTrue(token + " does not exists in parameters", 
					this.aprovaFacilService.getParameters().indexOf(token) > -1);
		}
	}

}