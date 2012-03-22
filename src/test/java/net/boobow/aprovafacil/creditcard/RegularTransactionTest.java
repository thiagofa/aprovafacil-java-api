package net.boobow.aprovafacil.creditcard;

import java.io.IOException;
import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.boobow.aprovafacil.TestUtil;
import net.boobow.aprovafacil.service.AprovaFacilService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

public class RegularTransactionTest {

	private static final String AUTHORIZATION_NUMBER = "008771";
	private CreditCard creditCard;
	private RegularTransaction transaction;
	
	@Spy
	private AprovaFacilService aprovaFacilService;
	
	@Before
	public void setUp() throws ParseException, IOException {
		aprovaFacilService = new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
		
		MockitoAnnotations.initMocks(this);
		
		this.creditCard = new CreditCard();
		this.creditCard.setNumber("4551870000000183");
		this.creditCard.setExpirationMonth(9);
		this.creditCard.setExpirationYear(2014);
		this.creditCard.setBrand(CreditCard.Brand.VISA);
		
		CreditCardHolder holder = new CreditCardHolder();
		holder.setName("Joaquim P Soares");
		holder.setFederalTaxId("12312312300");
		holder.setBirthDate(this.createDate("20/02/1990"));
		holder.setMotherName("Sebastiana das Couves");
		this.creditCard.setHolder(holder);
		
		this.transaction = new RegularTransaction();
		this.transaction.setAprovaFacilService(aprovaFacilService);
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
				return TestUtil.loadAuthorizedXml();
			}
			
		}).when(this.aprovaFacilService).post();
		
	}
	
	@Test
	public void shouldAuthorizeTransactionWithSecurityCode123() throws IOException {
		this.creditCard.setSecurityCode(123);
		Authorization authorization = this.transaction.authorizeFunds();

		assertTrue("Should authorize transaction.", authorization.isAuthorized());
	}
	
	@Test
	public void shoudNotAuthorizeTransactionWithSecurityCode501() throws IOException {
		this.creditCard.setSecurityCode(501);
		Authorization authorization = transaction.authorizeFunds();
		
		assertFalse("Should not authorize transaction.", authorization.isAuthorized());
	}
	
	@Test
	public void shouldAddParametersToService() throws IOException {
		this.creditCard.setSecurityCode(123);
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
	
	@Test
	@Ignore
	public void shoudReturnAuthorizationNumber() throws IOException {
		this.creditCard.setSecurityCode(123);
		Authorization authorization = this.transaction.authorizeFunds();

		assertEquals("Should return authorization number.", AUTHORIZATION_NUMBER, 
				authorization.getNumber());
	}
	
	private Date createDate(String dateAsString) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = df.parse(dateAsString);
		return date;
	}
	
}