package net.boobow.aprovafacil.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpPost {

	public String post(String url, String parameters) throws IOException {
		StringBuilder sb = new StringBuilder();
		URL resource = null;
		URLConnection connection = null;
		OutputStreamWriter wr = null;
		BufferedReader reader = null;
		
		try {
			resource = new URL(url);
	        connection = resource.openConnection();
	        connection.setDoOutput(true);
	        
	        wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.write(parameters);
	        wr.flush();
	    
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
		} finally {
			wr.close();
	        reader.close();			
		}
    
		return sb.toString();
	}
	
}