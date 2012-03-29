package net.boobow.aprovafacil.creditcard;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.boobow.aprovafacil.service.AprovaFacilService;
import net.boobow.aprovafacil.service.XmlParser;

import org.apache.commons.lang3.StringUtils;

public class BaseRequest {

	protected static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	protected AprovaFacilService aprovaFacilService;
	protected XmlParser xmlParser;
	
	public BaseRequest() {
		this.xmlParser = new XmlParser();
	}
	
	public void setAprovaFacilService(AprovaFacilService aprovaFacilService) {
		this.aprovaFacilService = aprovaFacilService;
	}
	
	public void setXmlParser(XmlParser xmlParser) {
		this.xmlParser = xmlParser;
	}
	
	protected void addParameterToService(String name, Boolean value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, value.booleanValue() ? "S" : "N",
					size, fillWith);
		}
	}
	
	protected void addParameterToService(String name, BigDecimal value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, Double.toString(value.doubleValue()),
					size, fillWith);
		}
	}
	
	protected void addParameterToService(String name, Date value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, DATE_FORMAT.format(value),
					size, fillWith);
		}
	}
	
	protected void addParameterToService(String name, Integer value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, value.toString(), size, fillWith);
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected void addParameterToService(String name, Enum value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			this.addParameterToService(name, value.toString(), size, fillWith);
		}
	}
	
	protected void addParameterToService(String name, String value, int size, 
			String fillWith) throws UnsupportedEncodingException {
		if (value != null) {
			if (size > 0) {
				value = StringUtils.leftPad(value, size, fillWith);
			}
			this.aprovaFacilService.addParameter(name, value);
		}
	}

}