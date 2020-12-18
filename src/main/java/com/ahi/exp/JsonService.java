package com.ahi.exp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

@Service
public class JsonService 
{
	public List<String> validateJson(String jsonSchema, String jsonData) 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
		List<String> validationMessagesList=new ArrayList<>();
		try 
		{
			InputStream schemaStream=new ByteArrayInputStream(jsonSchema.getBytes());
			InputStream jsonStream=new ByteArrayInputStream(jsonData.getBytes());
			JsonNode json = objectMapper.readTree(jsonStream);
			JsonSchema schema = schemaFactory.getSchema(schemaStream);
			Set<ValidationMessage> validationResult = schema.validate(json);

			if (!validationResult.isEmpty()) 
			{
				for(ValidationMessage msg:validationResult)
					validationMessagesList.add(msg.getMessage());

			}
			else
				validationMessagesList.add("No Validation Error Found!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());	
		}
		return validationMessagesList;
	}
}
