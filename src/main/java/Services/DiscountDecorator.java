package com.softwarePhase2.se.softwarePhase2.Services;

public class DiscountDecorator implements Service {

	Service s;
	
//	DiscountDecorator(Service service) {
//	        this.s = service;
//	    }
	
	public String AddDiscount(String discount) {
		return s.AddDiscount(discount);
		   
	}
	public double GetTotalFees() {
		
		return s.GetTotalFees();
	}


	@Override
	public void Setfees(double fees) {
		// TODO Auto-generated method stub
		
	}



}
