package net.boobow.aprovafacil.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;

import net.boobow.aprovafacil.service.AprovaFacilService;

@Ignore
public class TestUtil {

	public static String loadAuthorizedXml() throws IOException {
		return loadXml("authorization.xml");
	}
	
	public static String loadConfirmedSettlementResultXml() throws IOException {
		return loadXml("settlement-confirmed.xml");
	}
	
	public static String loadSettlementResultXmlWithError() throws IOException {
		return loadXml("settlement-error.xml");
	}
	
	private static String loadXml(String name) throws IOException {
		InputStream is = TestUtil.class.getClassLoader().getResourceAsStream(name);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		return sb.toString();
	}
	
	public static Date createDate(String dateAsString) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = df.parse(dateAsString);
		return date;
	}
	
	public static AprovaFacilService createTestService() {
		return new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
	}
	
	public static AprovaFacilService createProductionService() {
		return new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.PRODUCTION);
	}
	
}