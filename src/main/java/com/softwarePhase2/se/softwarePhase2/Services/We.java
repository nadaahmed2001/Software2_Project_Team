package com.softwarePhase2.se.softwarePhase2.Services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class We extends Provider {
	public String mobileNum ;
	public double amount;
	public double fees;
	public We(){
		fees = 5;
	}
	@PostMapping(value="/pay/we")
	public double totalFees(@RequestParam double amount) {
		return fees+ amount;
	}
}
