package com.ahi.exp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class JsonController 
{
	@Autowired
	JsonService service;
	
	@PostMapping(path="/validate",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
	public List<String> validateJson( @RequestParam Map<String, String> map) 
	{
		return service.validateJson(map.get("jsonSchema").toString(), map.get("jsonData").toString());
	}
}
