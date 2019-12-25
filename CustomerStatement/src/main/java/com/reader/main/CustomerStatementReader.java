package com.reader.main;

import static com.reader.constants.CommonConstants.CSVFORMAT;
import static com.reader.constants.CommonConstants.XMLFORMAT;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.reader.csvreader.OpenCSVReader;
import com.reader.xmlreader.CustomerParserSAX;

public class CustomerStatementReader {
 
	public static void main(String[] args) {
		try(Scanner myObj = new Scanner(System.in)){
		    System.out.println("Please enter the File Path :");
		
		File folder = new File(myObj.nextLine());
		File[] listOfFiles = folder.listFiles();
		Path path = null;
		String contentType = null;
		boolean validFileExist =false;
		if(listOfFiles.length==0) {
			System.out.println("No File exist in this folder");
		}else {
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	path = file.toPath(); 
				contentType = Files.probeContentType(path);
		    	if(contentType.equalsIgnoreCase(CSVFORMAT)){
		    		new OpenCSVReader().genrateCSVReport(new FileReader(file));
		    		validFileExist =true;
		    	}else if(contentType.equalsIgnoreCase(XMLFORMAT)){
		    		new CustomerParserSAX().genrateXMLReport(file);
		    		validFileExist =true;
		    	}
		    }
		} 
		if(!validFileExist) {
			System.out.println("No Valid File exist in this folder");
		}
		}
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
