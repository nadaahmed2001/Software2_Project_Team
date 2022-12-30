package com.softwarePhase2.se.softwarePhase2.Services;

import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MobileRechargeService implements Service {
	public Provider provider;
	public double amount;
	public double fees;
	private static MobileRechargeService instance= null; 
	
		private MobileRechargeService() {
			this.fees = 10.0;
		}
		
		public void Setfees(double fees) {
			this.fees =  fees;
		}
		
		public void SetProvider(String type) {
			if (type.equals("Vodafone")) 
				provider = new Vodafone();
			else if (type.equals("Etisalat")) 
				provider = new Etisalat();
			
	    	else if (type.equals("Orange")) 
				provider = new Orange();
			
		    else if (type.equals("We")) 
				provider = new We();
		}
		
		public  static MobileRechargeService GetInstance() {
			if(instance == null) {
				instance = new MobileRechargeService();
			}
			return instance;
		}
		
		public double GetTotalFees() {
			return provider.templete() + instance.fees;
		}


		@PostMapping(value="/AddDiscountMobileRecharge")
		public String AddDiscount(@RequestParam String discount) {
			GetInstance();
			instance.fees-=Double.parseDouble(discount);
			return "MobileRecharge after discount is: " + instance.fees +"\n";
			
		}

	
}
