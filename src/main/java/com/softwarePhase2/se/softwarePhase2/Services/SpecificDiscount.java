package com.softwarePhase2.se.softwarePhase2.Services;


public class SpecificDiscount extends DiscountDecorator{
	
	Service service;
    public SpecificDiscount(Service service) {
       
    	this.service = service;
    }
	

	public String AddDiscount(double discount) {
		 
		return service.AddDiscount(discount);
		
	}
	public double GetTotalFees() {
		
		return super.GetTotalFees();
	}
	}
