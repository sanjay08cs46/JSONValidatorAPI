package com.iqvia.validator.main.xmlvalidator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xml")
public class XmlController 
{
	@Autowired
	XmlService service;
	
	@PostMapping(path="/validate",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
	public String validateXml( @RequestParam Map<String, String> map) 
	{
		return service.validateXml(map.get("xmlData").toString(), map.get("xmlSchema").toString());
	}
}
