package com.softwarePhase2.se.softwarePhase2.Services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Orange extends Provider {
	public String mobileNum ;
	public double amount;
	public double fees;

	public Orange(){
		fees = 5;
	}
	@PostMapping(value="/pay/orange")
	public double totalFees(@RequestParam double amount) {
		return fees+ amount;
	}
}
