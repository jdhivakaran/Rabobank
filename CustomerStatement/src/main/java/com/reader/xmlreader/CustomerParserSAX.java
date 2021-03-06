package com.reader.xmlreader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.reader.model.CustomerStatement;

public class CustomerParserSAX {
	public void genrateXMLReport(File file) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			CustomerStatementHandler handler = new CustomerStatementHandler();
			saxParser.parse(file, handler);
			List<CustomerStatement> dupCustomerStatementList = handler.getDupcustomerStatementList();
			List<CustomerStatement> invalidCustomerStatementList = handler.getInvalidCurrencyList();
			dupCustomerStatementList.stream().forEach(System.out::println);
			invalidCustomerStatementList.stream().forEach(System.out::println);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
