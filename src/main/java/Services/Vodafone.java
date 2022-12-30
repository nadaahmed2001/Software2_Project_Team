package com.softwarePhase2.se.softwarePhase2.Services;


public class Vodafone extends Provider {
	public String mobileNum  ;
	public double amount;
	public double fees;
	public Vodafone(){
		fees=5;
	}
	
    public double totalFees(double amount) {
		return fees+ amount;
	}
}