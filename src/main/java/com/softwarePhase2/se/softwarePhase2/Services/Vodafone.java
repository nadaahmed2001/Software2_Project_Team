package com.softwarePhase2.se.softwarePhase2.Services;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//@RestController
public class Vodafone extends Provider {
	public String mobileNum  ;
	public double amount;
	public double fees;
	public Vodafone(){
		fees=5;
	}
    public double totalFees( double amount) {
		return fees+ amount;
	}
}