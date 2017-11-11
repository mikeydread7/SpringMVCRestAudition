package com.mike.audition.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mike.audition.service.QueryDBService;
import com.mike.audition.service.Validator;

@RestController
public class LookUpRestController {

	// *************************************************************************************************
	// Constants
	private final Logger LOG = LoggerFactory.getLogger(LookUpRestController.class);
	// *************************************************************************************************
	// member variables
	private QueryDBService queryDBService;

	// *************************************************************************************************
	// Constructors
	public LookUpRestController(QueryDBService queryDBService) {
		this.queryDBService = queryDBService;
	}

	// *************************************************************************************************
	// Implementation
	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getAllAcconts() {
		LOG.debug("getAllAcconts:");
		return new ResponseEntity<>(queryDBService.getAllAccounts(), HttpStatus.OK);
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getAccountById(@PathVariable String id) {
		LOG.debug("getAccountById: {}", id);
		ResponseEntity<?> results = getPreCheckBeforeServiceCall(id);
		if (null == results) {
			results = new ResponseEntity<>(queryDBService.getAccountById(id), HttpStatus.OK);
		}
		return results;
	}
	// *************************************************************************************************
	// Utilities
	private ResponseEntity<?> getPreCheckBeforeServiceCall(String id) {

		ResponseEntity<?> responseEntity = null;
		if (id.isEmpty() || !Validator.isAlphanumeric(id)) {
			responseEntity = new ResponseEntity<>("Unable to process request", HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
