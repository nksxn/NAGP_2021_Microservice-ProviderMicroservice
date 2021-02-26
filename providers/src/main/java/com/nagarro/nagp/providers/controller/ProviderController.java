package com.nagarro.nagp.providers.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.providers.facade.ProviderFacade;

@RestController
@RequestMapping(value = "/providers")
public class ProviderController {
	private static final Logger LOG = LoggerFactory.getLogger(ProviderController.class);
	
	@Value("${server.port}")
	private int port;
	
	@Autowired
	ProviderFacade providerFacade;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllProviders() {
		
		LOG.info("Working from port " + port +" of Providers microservice");
		
		Map<String, Object> responseData = providerFacade.getAllProviders();

		return new ResponseEntity<Map<String, Object>>(responseData, new HttpHeaders(), HttpStatus.OK);
	}

}
