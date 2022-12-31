package com.softwarePhase2.se.softwarePhase2.Services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//@RestController
public class Etisalat extends Provider{
	public String mobileNum;
	public double amount;
	public double fees;
	public Etisalat(){
		fees = 5.0;
	}

	
	public double totalFees(double amount) {
		return fees+ amount;
	}
}