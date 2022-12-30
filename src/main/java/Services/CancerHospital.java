package com.softwarePhase2.se.softwarePhase2.Services;

public class CancerHospital implements DonationFactory {
	public double amount;
	public double fees;
	public CancerHospital( double amount){
		this.amount = amount;
		fees=3.0;
	}
	
	public double totalFees() {
			
			return amount + fees;
		}
	

}