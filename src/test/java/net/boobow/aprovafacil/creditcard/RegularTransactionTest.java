package net.boobow.aprovafacil.creditcard;

import java.math.BigDecimal;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegularTransactionTest {

	private CreditCard creditCard;
	private RegularTransaction transaction;
	
	@Before
	public void setUp() {
		this.creditCard = new CreditCard();
		this.creditCard.setNumber("4551870000000183");
		this.creditCard.setExpirationMonth(9);
		this.creditCard.setExpirationYear(2014);
		this.creditCard.setBrand(CreditCard.Brand.VISA);
		
		CreditCardHolder holder = new CreditCardHolder();
		holder.setName("Joaquim P Soares");
		holder.setFederalTaxId("12312312300");
		holder.setBirthDate(new Date());
		holder.setMotherName("Sebastiana das Couves");
		
		this.transaction = new RegularTransaction();
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
	}
	
	@Test
	public void shouldAuthorizeTransactionWithValidSecurityCode() {
		this.creditCard.setSecurityCode(123);
		Authorization authorization = this.transaction.authorizeFunds();

		assertTrue("Should authorize transaction.", authorization.isAuthorized());
	}
	
	@Test
	public void shoudNotAuthorizeTransactionWithInvalidSecurityCode() {
		this.creditCard.setSecurityCode(501);
		Authorization authorization = transaction.authorizeFunds();
		
		assertFalse("Should not authorize transaction.", authorization.isAuthorized());
	}
	
	@Test
	@Ignore
	public void shoudReturnAuthorizationNumber() {
		this.creditCard.setSecurityCode(123);
		Authorization authorization = this.transaction.authorizeFunds();

		assertEquals("Should return authorization number.", "440800", authorization.getNumber());
	}
	
}