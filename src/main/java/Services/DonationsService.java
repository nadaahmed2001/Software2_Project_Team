package com.softwarePhase2.se.softwarePhase2.Services;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

public class DonationsService implements Service{
	public DonationFactory obj;
	public double amount;
	public double fees;
	private static DonationsService instance= null; 

	public DonationsService() {
		this.fees = 10.0;
	}
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	
	public void SetDonationFactory(String type , double amount ){
		if(type.equals("School"))
			obj = new School(amount);
		else if(type.equals("NGOs"))
			obj = new NGOs(amount);
		else
			obj = new CancerHospital(amount);
	}
	public  static DonationsService GetInstance() {
		if(instance == null) {
			instance = new DonationsService();
		}
		return instance;
	}

	public double GetTotalFees() {
		
		return obj.totalFees() + instance.fees;
	}

	@PostMapping(value="/AddDiscountDonation")
	public String AddDiscount(@RequestParam String discount) {
		GetInstance();
		instance.fees-=Double.parseDouble(discount);
		return "Donation Service after discount is: " + instance.fees +"\n";
		
	}
}
