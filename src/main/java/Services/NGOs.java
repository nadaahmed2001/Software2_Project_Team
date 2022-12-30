package com.softwarePhase2.se.softwarePhase2.Services;



public class NGOs implements DonationFactory{
	public double amount;
	public double fees;
	public NGOs( double amount){
		this.amount = amount;
		fees=2.0;
	}
	
	public double totalFees() {
			
			return amount + fees;
		}
	

}
