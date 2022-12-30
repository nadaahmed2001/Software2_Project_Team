package com.softwarePhase2.se.softwarePhase2.Services;

import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LandlinesService implements Service{
	LandlinesFactory obj;
	public  double fees;
	public double amount;
	private static LandlinesService instance= null; 
	
	public LandlinesService() {
		this.fees = 10.0;
	}
	
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	
	public void SetLandlinesFactory(String type , double amount) {
		if(type.equals("Monthly Receipt"))
			obj = new MonthlyReceipt(amount );
		else
			obj = new QuarterReceipt(amount);
	}
	
	public  static LandlinesService GetInstance() {
		if(instance == null) {
			instance = new LandlinesService();
		}
		return instance;
	}

	
	public double GetTotalFees() {
		return obj.totalFees() + instance.fees;
	}
	@PostMapping(value="/AddDiscountLandlines")
	public String AddDiscount(@RequestParam String discount) {
		GetInstance();
		instance.fees-=Double.parseDouble(discount);
		return "Landlines Service after discount is: " + instance.fees +"\n";
		
	}

}
