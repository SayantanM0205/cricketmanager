package com.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
	@GetMapping("/mystats")
	public String getStatistics() {
		return "Stats retireved from db";
	}
	

}
