package net.boobow.aprovafacil.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	
}