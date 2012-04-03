package net.boobow.aprovafacil.creditcard.it;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import net.boobow.aprovafacil.creditcard.Acquirer;
import net.boobow.aprovafacil.creditcard.Authorization;
import net.boobow.aprovafacil.creditcard.CreditCard;
import net.boobow.aprovafacil.creditcard.CreditCardHolder;
import net.boobow.aprovafacil.creditcard.Currency;
import net.boobow.aprovafacil.creditcard.trx.AuthorizationTransaction;
import net.boobow.aprovafacil.service.AprovaFacilService;
import net.boobow.aprovafacil.util.TestUtil;

import org.junit.Ignore;
import org.junit.Test;

public class ITAuthorizationTransactionTest {

	@Test
	@Ignore
	public void testAuthorizeTransaction() throws ParseException, IOException, JAXBException {
		AprovaFacilService aprovaFacilService = new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
		
		CreditCard creditCard = new CreditCard();
		creditCard.setNumber("4551870000000183");
		creditCard.setExpirationMonth(9);
		creditCard.setExpirationYear(2014);
		creditCard.setSecurityCode(123);
		creditCard.setBrand(CreditCard.Brand.VISA);
		
		CreditCardHolder holder = new CreditCardHolder();
		holder.setName("Joaquim P Soares");
		holder.setFederalTaxId("12312312300");
		holder.setBirthDate(TestUtil.createDate("20/02/1990"));
		holder.setMotherName("Sebastiana das Couves");
		creditCard.setHolder(holder);
		
		AuthorizationTransaction transaction = new AuthorizationTransaction();
		transaction.setAprovaFacilService(aprovaFacilService);
		
		transaction.setOrderNumber("123");
		transaction.setTotalAmount(new BigDecimal(1.99));
		transaction.setCurrency(Currency.BRL);
		transaction.setInstallments(1);
		transaction.setInstallmentByAdmin(Boolean.FALSE);
		transaction.setPreAuthorization(Boolean.FALSE);
		transaction.setCreditCard(creditCard);
		transaction.setAcquirer(Acquirer.CIELO);
		transaction.setBuyerHost("127.0.0.1");
		transaction.setUtf8Output(Boolean.TRUE);
		
		Authorization authorization = transaction.authorizeFunds();
		
		assertTrue(authorization.isAuthorized());
	}
	
}