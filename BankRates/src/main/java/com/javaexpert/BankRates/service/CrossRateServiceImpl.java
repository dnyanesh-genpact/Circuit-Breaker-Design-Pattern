package com.javaexpert.BankRates.service;

import org.springframework.stereotype.Service;

import com.javaexpert.BankRates.model.CrossRate;

@Service
public class CrossRateServiceImpl implements CrossRateService {

	@Override
	public CrossRate calculateCrossRate(CrossRate crossRate) {
		if(crossRate.getFromCurrency().equals(crossRate.getToCurrency())){
			crossRate.setCrossRate(1);
		}else {
			crossRate.setCrossRate(10.345);
		}
		return crossRate;
	}
}
