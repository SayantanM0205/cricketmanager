package com.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OffersController {

	@GetMapping("/offers")
	public String getOffers() {
		return "Offers retrieved from db";
	}
}
