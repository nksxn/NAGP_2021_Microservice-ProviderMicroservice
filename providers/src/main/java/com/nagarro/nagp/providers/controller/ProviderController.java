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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.providers.entity.Provider;
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

		LOG.info("Working from port " + port + " of Providers microservice");

		Map<String, Object> responseData = providerFacade.getAllProviders();

		return new ResponseEntity<Map<String, Object>>(responseData, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Void> addProvider(@RequestBody Provider provider) {
		LOG.info("Working from port " + port + " of Providers microservice");
		try {
			providerFacade.addProvider(provider);
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<Map<String, Object>> getProviderByUsername(@PathVariable("username") String username) {
		LOG.info("Working from port " + port + " of Users microservice");
		Map<String, Object> responseData = providerFacade.getProviderByUsername(username);
		return new ResponseEntity<Map<String, Object>>(responseData, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<Void> updateProvider(@RequestBody Provider provider) {
		LOG.info("Working from port " + port + " of Users microservice");
		HttpHeaders headers = new HttpHeaders();
		if (providerFacade.updateProvider(provider)) {
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/notifyProviders")
	public ResponseEntity<Void> notifyProviders(@RequestBody String serviceRegion) {
		LOG.info("Working from port " + port + " of Users microservice");
		providerFacade.notifyProviders(serviceRegion);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@PostMapping("/accept")
	public ResponseEntity<Void> acceptOrder(@RequestBody String OrderCode, @RequestBody String username) {
		LOG.info("Working from port " + port + " of Users microservice");
		providerFacade.acceptOrder(OrderCode, username);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@PostMapping("/notifyProvider")
	public ResponseEntity<Void> notifyProvider(@RequestBody String username) {
		LOG.info("Working from port " + port + " of Users microservice");
		providerFacade.notifyProvider(username);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
}
