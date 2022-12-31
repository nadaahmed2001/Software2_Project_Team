package com.softwarePhase2.se.softwarePhase2.SystemUsers;
import org.springframework.web.bind.annotation.*;
import  com.softwarePhase2.se.softwarePhase2.Services.*;


import java.util.Scanner;

import org.springframework.stereotype.Component;

@RestController
public class User implements observer {
	String password;
	String email;
	String username;
	String service="";
	public Wallet wallet = new Wallet();
	CreditCard credit = new CreditCard();
	Refund refund ;
	Scanner scan = new Scanner(System.in);
	// Login
	public void setEmail(String Email) {
		this.email=Email;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String pass) {
		this.password=pass;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String name) {
		this.username=name;
	}
		
	public String getUsername() {
		return username;
	}
	@GetMapping(value="/users/{email}/{password}")
	public String login(@PathVariable("email") String email,@PathVariable("password") String password) {
		if (DataBase.CheckUserInfo(email,password)) {
			this.email = getEmail();
			System.out.println();
			for(int i=0;i<50;i++) {
				if (DataBase.userInfo[i][0].equals(getEmail())) {
					if(DataBase.userInfo[i][4] =="0") {
						return "Login Successfully\n"+ getUsername() +" Has new notification: Request Rejected.\n";
						
					}else if(DataBase.userInfo[i][4] =="1"){
						
						return "Login Successfully\n"+ getUsername() +" Has new notification: "
								+ "Request Accepted, and the transaction done successfully!😀\n";
					}
				}
			}
			return "Login Successfully\n";
		} else 
			System.out.println();
		    return "Email or Password is incorrect";
	}
	@PostMapping(value="/users")
	public String signUp(@RequestBody User user) {
		if (DataBase.AddUserInfo(user.getEmail(),user.getPassword())) {
			this.email = user.getEmail();
			this.password = user.getPassword();
			this.username = user.getUsername();
			return "SignUp Successfully";
		} else
			return "Email is already Exists";
	}
	
	@GetMapping(value="/Services/{serviceName}")
	public String search(@PathVariable("serviceName") String serviceName) {
		String[] servicesList= {"Mobile recharge services","Internet Payment services","Landline services","Donations"};
		String[] Result= new String[10];
		for(int i=0;i<servicesList.length;i++) {
				if((servicesList[i].toLowerCase()).contains(serviceName.toLowerCase()))
					Result[i]=(servicesList[i]);
		
		}
		for(int i=0;i<Result.length;i++) {
			if(Result[i]!=null) {
				System.out.println((i+1)+" "+Result[i]);
				if(Result[i].equalsIgnoreCase("Mobile recharge services")) {
					return " a.Vodafone\n b.Etisalat\n c.Orange\n d.We\n";
					
				}
				else if(Result[i].equalsIgnoreCase("Internet Payment services")) {
					return " a.Vodafone\n b.Etisalat\n c.Orange\n d.We\n";
					
				}
				else if(Result[i].equalsIgnoreCase("Landline services")) {
					return " a.	Monthly receipt\n b.Quarter receipt\n";	
				}
				else if(Result[i].equalsIgnoreCase("Donations")) {
					return " a.	Cancer Hospital\n b.Schools \nc.NGOs (Non profitable organizations)\n";
					
					
				}
		}}
		return "Not Found";

	}

	public void pay(int servicenumber) {
		
		int specificServiceNumber = 0;
		String mobile;
		double amount;
		double TotalFees=0;
		switch (servicenumber) {
		
		case 1:// mobile recharge
			MobileRechargeService v = MobileRechargeService.GetInstance();
			
			System.out.println("Choose:\n 1-Vodafone \n 2-Etisalat \n 3-Orange \n 4-We");
			System.out.print("Select: ");
			// Take input specificServiceNumber from user
			
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			
			
			switch (specificServiceNumber) {
			case 1: // vodafone
				v.SetProvider("Vodafone");
				TotalFees = v.GetTotalFees();
				this.service = "vodafone MobileRecharge Service";
			
			
			break;
			
			case 2:
				v.SetProvider("Etisalat");
				TotalFees = v.GetTotalFees();
				this.service = "Etisalat MobileRecharge Service";
		
				
			break;
			case 3:
				v.SetProvider("Orange");
				TotalFees = v.GetTotalFees();
				this.service = "Orange MobileRecharge Service";

				
			break;
			case 4:
				v.SetProvider("We");
				TotalFees = v.GetTotalFees();
				this.service = "We MobileRecharge Service";
				
				
			}	
			break;
			
			
		case 2: // internet Payment services
			
			InternetPaymentService v2 = InternetPaymentService.GetInstance();
		
			System.out.println("Choose:\n 1-Vodafone \n 2-Etisalat \n 3-Orange \n 4-We");
			System.out.print("Select: ");
			
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			
			
			switch (specificServiceNumber) {
			case 1: // vodafone
				v2.SetProvider("Vodafone");
				TotalFees = v2.GetTotalFees();
				this.service  = "Vodafone InternetPayment Service";
		
			break;
			
			case 2:
				v2.SetProvider("Etisalat");
				TotalFees = v2.GetTotalFees();
				this.service  = "Etisalat InternetPayment Service";
				
				
			break;
			case 3:
				v2.SetProvider("Orange");
				TotalFees = v2.GetTotalFees();
				this.service  = "Orange InternetPayment Service";
				
				
			break;
			case 4:
				
				v2.SetProvider("We");
				TotalFees = v2.GetTotalFees();
				this.service  = "We InternetPayment Service";
			
			break;
			}
			
			break;
			
		
		case 3: // Landline Service
			
			LandlinesService v3 = LandlinesService.GetInstance();
		
			System.out.println("Choose:\n 1-Monthly Receipt \n 2-Quarter Receipt \n ");
			System.out.print("Select: ");
			
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			
			System.out.print("Enter Amount: ");
			amount = scan.nextDouble();
			scan.nextLine();
			
			switch (specificServiceNumber) {
				case 1: //Monthly Receipt
					v3.SetLandlinesFactory("Monthly Receipt" , amount);
					TotalFees = v3.GetTotalFees();
					this.service  = "Monthly Receipt Landlines Service";
					

				break;
				
				case 2:
					
					v3.SetLandlinesFactory("Quarter Receipt" , amount);
					TotalFees = v3.GetTotalFees();
					this.service  = "Quarter Receipt Landlines Service";
					
				break;
		
			}

			break;
			
		case 4: // Donations
			
			DonationsService v4 = DonationsService.GetInstance();
			
			System.out.println("Choose:\n 1-NGOs \n 2-School \n 3-Canser Hospital \n ");
			System.out.print("Select: ");
			
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			
			System.out.print("Enter Amount: ");
			amount = scan.nextDouble();
			scan.nextLine();
			
			switch (specificServiceNumber) {
				case 1:
					
					v4.SetDonationFactory("NGOs" , amount);
					TotalFees = v4.GetTotalFees();
					this.service  = "NGOs Donations Service";
					
			
				break;
				
				case 2:
					
					v4.SetDonationFactory("School" , amount);
					TotalFees = v4.GetTotalFees();
					this.service  = "School Donations Service";
					
					
				break;
				
				case 3:
					v4.SetDonationFactory("CancerHospital" , amount);
					TotalFees = v4.GetTotalFees();
					this.service  = "Canser Hospital Donations Service";

					
				break;
			}
			
	
			break;
			
			
		}// End switch 1
		
		String option = "";
		// Take option input from user
		
		System.out.println("Choose the payment method: 1-Credit card   2-Wallet");
		
		option = scan.nextLine();
		// Choose between credit card or wallet
		switch (option) {
		case "1":
			
			System.out.println("Enter Credit card number: ");
			String creditNum = scan.next();
			if(creditNum.length()==16) {
				if (this.credit.amount >= TotalFees ) {
					this.credit.TakeFromCredit(this, TotalFees);
					System.out.println("TotalFees is = " + TotalFees);
				}else
					System.out.println("There is not enough money in your credit card!");
			}else {
				System.out.println("Credit card number is invalid!");
			}
			
			
		break;
		
		case "2":
	
			if (this.wallet.amount >= TotalFees ) {
				this.wallet.TakeFromWallet(this, TotalFees); 
			System.out.println("TotalFees is = " + TotalFees);
			}
			else
				System.out.println("There is not enough money in your wallet!");
		break;
			
		}
		DataBase.PayTransaction[DataBase.paycount][0] = this.email;
		DataBase.PayTransaction[DataBase.paycount][1] = this.service;
		DataBase.PayTransaction[DataBase.paycount][2] = Double.toString(TotalFees);  
		DataBase.paycount++ ;
	
		}
	
	@GetMapping(value="/PAY/{serviceName}/{provider}/{mobile}/{amount}")//this function to use in web server , it does the same job of function pay above(function pay used in console app only as it take alot of inputs)
	public String PAY(@PathVariable("serviceName")String serviceName ,@PathVariable("provider") String provider ,@PathVariable("mobile")  String mobile ,@PathVariable("amount") double amount) {
		double TotalFees =0;
		serviceName.toLowerCase();
		provider.toLowerCase();
		
		if(serviceName.equals("mobilerecharge")  || serviceName.equals("mobile recharge")) {
			MobileRechargeService v = MobileRechargeService.GetInstance();
			v.SetProvider(provider);
			TotalFees = v.TOTALFEES(amount);
			this.service = provider +  " MobileRecharge Service";
			
		}else if(serviceName.equals("internetpayment")  || serviceName.equals("internet payment")) {
				InternetPaymentService v2 = InternetPaymentService.GetInstance();
				v2.SetProvider(provider);
				TotalFees = v2.TOTALFEES(amount);
				this.service  = provider + " InternetPayment Service";
				
		}else if(serviceName.equals("landlines")) {
			LandlinesService v3 = LandlinesService.GetInstance();
			v3.SetLandlinesFactory(provider, amount );
			TotalFees = v3.GetTotalFees();
			this.service  =  provider +" Receipt Landlines Service";
		}else if(serviceName.equals("donations")){
			DonationsService v4 = DonationsService.GetInstance();
			v4.SetDonationFactory(provider , amount);
			TotalFees = v4.GetTotalFees();
			this.service  = provider +" Donations Service";
		}else {
			return "Enter valid service.";
		}
			
		if (this.wallet.amount >= TotalFees ) {
			this.wallet.TakeFromWallet(this, TotalFees); 
			DataBase.PayTransaction[DataBase.paycount][0] = this.email;
			DataBase.PayTransaction[DataBase.paycount][1] = this.service;
			DataBase.PayTransaction[DataBase.paycount][2] = Double.toString(TotalFees);  
			DataBase.paycount++ ;
		return "TotalFees is = " + TotalFees;
		}
		else {
			return "There is not enough money in your wallet!";
		}
	

	}
	
	@PostMapping(value="/Refunds")
	public String RequestRefund(@RequestBody Refund obj) {
		for(int i=0;i<50;i++) {
			if (DataBase.userInfo[i][0].equals(obj.getUser().getEmail())) {
				DataBase.userInfo[i][4] = "3";
			}
		}
		this.refund = new Refund();
		this.refund.setUser(obj.getUser());
		this.refund.setServiceName(obj.serviceName);
		this.refund.setAmount(obj.getAmount());
		this.refund.setState(obj.getState());
		//this.refund.getUser().email = email;
		return this.refund.addRefundToArrayList(refund);
	}


	@GetMapping(value="/showDiscounts")
	public String CheckDiscount() {
		if(Admin.discounts.getDiscounts() == "") {
			return"There is no Discount rightnow..";
		}else {
			return "There is "+Admin.discounts.getDiscounts();
		}
	}
	@GetMapping(value="/userWallet")
	public String showWallet(@RequestBody User user) {
		return this.wallet.showWallet(user);
	}
	@PutMapping(value="/userWallet/{Amount}")
	public String AddTOWallet( @RequestBody User user, @PathVariable ("Amount") double Amount) {
		return this.wallet.AddtoWallet(user, Amount);
	}
	
	public void update(boolean message) {
		if(message == false) {
			for(int i=0;i<50;i++) {
				if (DataBase.userInfo[i][0].equals(this.email)) {
					DataBase.userInfo[i][4] ="0";
				}
			}
			
			
		}else {
			for(int i=0;i<50;i++) {
				if (DataBase.userInfo[i][0].equals(this.email)) {
					DataBase.userInfo[i][4] ="1";
				}
			}

			
		}
		
		
	}
	

}
