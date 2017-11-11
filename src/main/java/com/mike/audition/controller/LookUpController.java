package com.mike.audition.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mike.audition.model.LookUpForm;
import com.mike.audition.service.QueryDBService;
import com.mike.audition.service.Validator;

@Controller
@RequestMapping(value = "/form")
public class LookUpController {
	// *************************************************************************************************
	// Constants
	private final Logger LOG = LoggerFactory.getLogger(LookUpController.class);

	// *************************************************************************************************
	// member variables
	private QueryDBService queryDBService;

	// *************************************************************************************************
	// Constructors
	public LookUpController(QueryDBService queryDBService) {
		this.queryDBService = queryDBService;
	}

	// *************************************************************************************************
	// Implementation
	@RequestMapping(method = RequestMethod.GET)
	public String lookUpForm(Model model) {

		LOG.debug("Get account look up Form");
		model.addAttribute("lookUpForm", new LookUpForm());
		return "form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String LookUpResultsSubmit(@ModelAttribute LookUpForm lookUpResults, Model model) {
		LOG.debug("Show account search");
		if (getPreCheckBeforeServiceCall(lookUpResults.getId())) {
			model.addAttribute("lookUpForm", queryDBService.getAccountById(lookUpResults.getId()));
		}
		return "results";
	}

	// *************************************************************************************************
	// Utilities
	private boolean getPreCheckBeforeServiceCall(String id) {

		if (id.isEmpty() || !Validator.isAlphanumeric(id)) {
			return false;
		}
		return true;
	}
}
