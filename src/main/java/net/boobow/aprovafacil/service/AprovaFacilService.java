package net.boobow.aprovafacil.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AprovaFacilService {

	public enum Environment {
		TEST {
			@Override
			String getUrl(String username) {
				return "https://teste.aprovafacil.com/cgi-bin/APFW/" 
						+ username + "/APC";
			}
		},
		PRODUCTION {
			@Override
			String getUrl(String username) {
				return "https://www.aprovafacil.com/cgi-bin/APFW/" 
						+ username + "/APC";
			}
		};
		
		abstract String getUrl(String username);
	}

	private String parameters;
	private final String username;
	private final Environment environment;
	
	public AprovaFacilService(String username, Environment environment) {
		this.username = username;
		this.environment = environment;
		this.parameters = "";
	}

	public String getParameters() {
		return parameters;
	}

	public void addParameter(String name, String value) throws UnsupportedEncodingException {
		if (parameters.length() > 0) {
			parameters += "&";
		}
		parameters += URLEncoder.encode(name, "UTF-8") + "="
				+ URLEncoder.encode(value, "UTF-8");
	}

	public String getUrl() {
		return this.environment.getUrl(this.username);
	}

}