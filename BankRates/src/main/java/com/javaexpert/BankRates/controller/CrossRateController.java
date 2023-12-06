package com.javaexpert.BankRates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpert.BankRates.model.CrossRate;
import com.javaexpert.BankRates.service.CrossRateService;

@RestController
@RequestMapping("/banking/rates")
public class CrossRateController {
	
	@Autowired
	CrossRateService crossRateService;
	
	@PostMapping("/crossRate")
	public CrossRate getCrossRate(@RequestBody CrossRate crossRate) {
		return crossRateService.calculateCrossRate(crossRate);
	}
	

}
