package net.boobow.aprovafacil.creditcard.trx;

import java.io.IOException;
import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.creditcard.Acquirer;
import net.boobow.aprovafacil.creditcard.CreditCard;
import net.boobow.aprovafacil.creditcard.CreditCardHolder;
import net.boobow.aprovafacil.creditcard.Currency;
import net.boobow.aprovafacil.creditcard.trx.AuthorizationTransaction;
import net.boobow.aprovafacil.service.AprovaFacilService;
import net.boobow.aprovafacil.service.XmlParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

public class AuthorizationTransactionTest {

	private CreditCard creditCard;
	private AuthorizationTransaction transaction;
	
	@Spy
	private AprovaFacilService aprovaFacilService;
	
	@Mock
	private XmlParser xmlParser;
	
	@Before
	public void setUp() throws ParseException, IOException, JAXBException {
		aprovaFacilService = new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
		
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
		holder.setBirthDate(this.createDate("20/02/1990"));
		holder.setMotherName("Sebastiana das Couves");
		this.creditCard.setHolder(holder);
		
		this.transaction = new AuthorizationTransaction();
		this.transaction.setAprovaFacilService(aprovaFacilService);
		this.transaction.setXmlParser(this.xmlParser);
		
		this.transaction.setOrderNumber("123");
		this.transaction.setTotalAmount(new BigDecimal(1.99));
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
	
	private Date createDate(String dateAsString) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = df.parse(dateAsString);
		return date;
	}
	
}