package com.thiagofa.aprovafacil.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.thiagofa.aprovafacil.service.AprovaFacilService;
import com.thiagofa.aprovafacil.service.HttpPost;
import com.thiagofa.aprovafacil.util.Util;

public class AprovaFacilServiceTest {

	@Mock
	private HttpPost httpPost;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddOneParameter() throws UnsupportedEncodingException {
		AprovaFacilService service = Util.createTestService();
		service.addParameter("p1", "a");
		assertEquals(service.getParameters(), "p1=a");
	}

	@Test
	public void shouldAddTwoParameters() throws UnsupportedEncodingException {
		AprovaFacilService service = Util.createTestService();
		service.addParameter("p1", "a");
		service.addParameter("p2", "b");
		assertEquals(service.getParameters(), "p1=a&p2=b");
	}
	
	@Test
	public void shouldEncodeParameterNames() throws UnsupportedEncodingException {
		AprovaFacilService service = Util.createTestService();
		service.addParameter("áéíóú", "a");
		assertEquals(service.getParameters(), "%C3%A1%C3%A9%C3%AD%C3%B3%C3%BA=a");
	}
	
	@Test
	public void shouldEncodeParameterValues() throws UnsupportedEncodingException {
		AprovaFacilService service = Util.createTestService();
		service.addParameter("p1", "áéíóú");
		assertEquals(service.getParameters(), "p1=%C3%A1%C3%A9%C3%AD%C3%B3%C3%BA");
	}
	
	@Test
	public void shouldConfigureServiceUrlForTestEnvironment() {
		AprovaFacilService service = Util.createTestService();
		assertEquals(service.getUrl(), "https://teste.aprovafacil.com/cgi-bin/APFW/boobow/");
	}
	
	@Test
	public void shouldConfigureServiceUrlForProductionEnvironment() {
		AprovaFacilService service = Util.createProductionService();
		assertEquals(service.getUrl(), "https://www.aprovafacil.com/cgi-bin/APFW/boobow/");
	}
	
	@Test
	public void shouldPostUrlWithService() throws IOException {
		AprovaFacilService service = Util.createTestService();
		
		service.httpPost = this.httpPost;
		service.post("APC");
		
		verify(this.httpPost, only()).post(eq("https://teste.aprovafacil.com/cgi-bin/APFW/boobow/APC"), 
				anyString());
	}
	
	@Test
	public void shouldPostAndReturnAuthorizationXml() throws IOException {
		AprovaFacilService service = Util.createTestService();
		
		service.httpPost = this.httpPost;
		String xml = Util.loadAuthorizedXml();
		when(this.httpPost.post(anyString(), anyString())).thenReturn(xml);
		
		assertEquals(xml, service.post("someservice"));
	}
	

	
}