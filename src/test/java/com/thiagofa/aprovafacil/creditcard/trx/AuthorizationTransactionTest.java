package com.thiagofa.aprovafacil.creditcard.trx;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.thiagofa.aprovafacil.creditcard.Acquirer;
import com.thiagofa.aprovafacil.creditcard.CreditCard;
import com.thiagofa.aprovafacil.creditcard.CreditCardHolder;
import com.thiagofa.aprovafacil.creditcard.Currency;
import com.thiagofa.aprovafacil.service.AprovaFacilService;
import com.thiagofa.aprovafacil.service.XmlParser;
import com.thiagofa.aprovafacil.util.Util;

public class AuthorizationTransactionTest {

	private CreditCard creditCard;
	private AuthorizationTransaction transaction;
	
	@Spy
	private AprovaFacilService aprovaFacilService;
	
	@Mock
	private XmlParser xmlParser;
	
	@Before
	public void setUp() throws ParseException, IOException, JAXBException {
		aprovaFacilService = Util.createTestService();
		
		MockitoAnnotations.initMocks(this);
		
		this.creditCard = new CreditCard();
		this.creditCard.setNumber("4551870000000183");
		this.creditCard.setExpirationMonth(9);
		this.creditCard.setExpirationYear(2014);
		this.creditCard.setSecurityCode(123);
		this.creditCard.setBrand(CreditCard.Brand.VISA);
		
		CreditCardHolder holder = new CreditCardHolder();
		holder.setName("Joaquim P Soares");
		holder.setFederalTaxId("12312312300");
		holder.setBirthDate(Util.createDate("20/02/1990"));
		holder.setMotherName("Sebastiana das Couves");
		this.creditCard.setHolder(holder);
		
		this.transaction = new AuthorizationTransaction();
		this.transaction.setAprovaFacilService(aprovaFacilService);
		this.transaction.setXmlParser(this.xmlParser);
		
		this.transaction.setDocumentNumber("123");
		this.transaction.setAmount(new BigDecimal(1.99));
		this.transaction.setCurrency(Currency.BRL);
		this.transaction.setInstallments(1);
		this.transaction.setInstallmentByAdmin(Boolean.FALSE);
		this.transaction.setPreAuthorization(Boolean.FALSE);
		this.transaction.setCreditCard(creditCard);
		this.transaction.setAcquirer(Acquirer.CIELO);
		this.transaction.setBuyerHost("127.0.0.1");
		this.transaction.setUtf8Output(Boolean.TRUE);
		
		doAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return  "<samplexml/>";
			}
			
		}).when(this.aprovaFacilService).post(anyString());
		
	}
	
	@Test
	public void shouldPostTransactionForAPCService() throws IOException, JAXBException {
		this.transaction.authorizeFunds();

		verify(this.aprovaFacilService).post("APC");
	}
	
	public void shouldParseXmlResult() throws IOException, JAXBException {
		this.transaction.authorizeFunds();
		
		verify(this.xmlParser).parseAuthorization("<samplexml/>");
	}
	
	@Test
	public void shouldAddParametersToService() throws IOException, JAXBException {
		this.transaction.authorizeFunds();
		
		String[] tokens = new String[] {this.aprovaFacilService.getParameters(), 
				"NumeroDocumento=123", "ValorDocumento=1.99",
				"QuantidadeParcelas=1", "NumeroCartao=4551870000000183",
				"MesValidade=09", "AnoValidade=14", "CodigoSeguranca=123",
				"PreAutorizacao=N", "EnderecoIPComprador=127.0.0.1",
				"NomePortadorCartao=Joaquim+P+Soares",
				"Bandeira=VISA", "Adquirente=CIELO", "CPFPortadorCartao=12312312300",
				"DataNascimentoPortadorCartao=20%2F02%2F1990",
				"NomeMaePortadorCartao=Sebastiana+das+Couves",
				"ParcelamentoAdministradora=N", "Moeda=BRL",
	            "ResponderEmUTF8=S"};
		
		for (String token : tokens) {
			assertTrue(token + " does not exists in parameters", 
					this.aprovaFacilService.getParameters().indexOf(token) > -1);
		}
	}
	
}