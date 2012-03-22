package net.boobow.aprovafacil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtil {

	public static String loadAuthorizedXml() throws IOException {
		InputStream is = TestUtil.class.getClassLoader().getResourceAsStream("authorized.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		return sb.toString();
	}
	
}