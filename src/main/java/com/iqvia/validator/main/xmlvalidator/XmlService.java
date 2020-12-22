package com.iqvia.validator.main.xmlvalidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class XmlService 
{ 
	public String validateXml(String xml, String schem) 
	{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try 
		{
			String xmlFile=writeXmlFile(xml);
			String schemaFile=writeXsdFile(schem);
			Schema schema = schemaFactory.newSchema(new File(schemaFile));

			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlFile)));
			//return true;
		} 
		catch (SAXException | IOException e) 
		{
			return e.getMessage();
		}
		return "Validation Successful with no error!";
	}

	public String writeXmlFile(String xmlFile) 
	{

		try
		{
			String xmlFilePath=Files.createTempDirectory(null).toString()+"\\temp.xml";
			Files.write( Paths.get(xmlFilePath), xmlFile.getBytes());
			return xmlFilePath;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String writeXsdFile(String schemaFile) 
	{

		try
		{
			String xmlSchemaPath=Files.createTempDirectory(null).toString()+"\\temp.xsd";
			Files.write( Paths.get(xmlSchemaPath), schemaFile.getBytes());
			return xmlSchemaPath;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;	
	}
}
