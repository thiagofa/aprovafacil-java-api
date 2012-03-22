package net.boobow.aprovafacil.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import net.boobow.aprovafacil.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AprovaFacilServiceTest {

	@Mock
	private HttpPost httpPost;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
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
	
	@Test
	public void shouldPostAndReturnAuthorizationXml() throws IOException {
		AprovaFacilService service = createTestService();
		
		service.httpPost = this.httpPost;
		String xml = TestUtil.loadAuthorizedXml();
		when(this.httpPost.post(anyString(), anyString())).thenReturn(xml);
		
		assertEquals(xml, service.post());
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