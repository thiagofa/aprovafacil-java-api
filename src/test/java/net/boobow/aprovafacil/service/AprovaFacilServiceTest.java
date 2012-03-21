package net.boobow.aprovafacil.service;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class AprovaFacilServiceTest {

	@Test
	public void shouldAddOneParameter() throws UnsupportedEncodingException {
		AprovaFacilService service = createTestService();
		service.addParameter("p1", "a");
		assertEquals(service.getParameters(), "p1=a");
	}

	@Test
	public void shouldAddTwoParameters() throws UnsupportedEncodingException {
		AprovaFacilService service = createTestService();
		service.addParameter("p1", "a");
		service.addParameter("p2", "b");
		assertEquals(service.getParameters(), "p1=a&p2=b");
	}
	
	@Test
	public void shouldEncodeParameterNames() throws UnsupportedEncodingException {
		AprovaFacilService service = createTestService();
		service.addParameter("áéíóú", "a");
		assertEquals(service.getParameters(), "%C3%A1%C3%A9%C3%AD%C3%B3%C3%BA=a");
	}
	
	@Test
	public void shouldEncodeParameterValues() throws UnsupportedEncodingException {
		AprovaFacilService service = createTestService();
		service.addParameter("p1", "áéíóú");
		assertEquals(service.getParameters(), "p1=%C3%A1%C3%A9%C3%AD%C3%B3%C3%BA");
	}
	
	@Test
	public void shouldConfigureServiceUrlForTestEnvironment() {
		AprovaFacilService service = createTestService();
		assertEquals(service.getUrl(), "https://teste.aprovafacil.com/cgi-bin/APFW/boobow/APC");
	}
	
	@Test
	public void shouldConfigureServiceUrlForProductionEnvironment() {
		AprovaFacilService service = createProductionService();
		assertEquals(service.getUrl(), "https://www.aprovafacil.com/cgi-bin/APFW/boobow/APC");
	}
	
	private AprovaFacilService createTestService() {
		return new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.TEST);
	}
	
	private AprovaFacilService createProductionService() {
		return new AprovaFacilService("boobow", 
				AprovaFacilService.Environment.PRODUCTION);
	}
	
}