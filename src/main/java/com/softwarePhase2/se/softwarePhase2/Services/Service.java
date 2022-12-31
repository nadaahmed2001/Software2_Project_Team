package com.softwarePhase2.se.softwarePhase2.Services;


public interface Service  {
	public double amount=0.0;//Amount from user
	public double fees=0.0;//Service fees
	public double GetTotalFees();
	public void Setfees(double fees);
	public String AddDiscount(double discount) ;
}