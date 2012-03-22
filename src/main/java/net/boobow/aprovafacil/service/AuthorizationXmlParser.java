package net.boobow.aprovafacil.service;

import net.boobow.aprovafacil.creditcard.Authorization;

public class AuthorizationXmlParser {

	public Authorization parse(String xml) {
		Authorization result = new Authorization();
		result.setAuthorized(true);
		return result;
	}
	
}