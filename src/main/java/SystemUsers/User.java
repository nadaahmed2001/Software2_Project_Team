package com.softwarePhase2.se.softwarePhase2.SystemUsers;
import org.springframework.web.bind.annotation.*;
import  com.softwarePhase2.se.softwarePhase2.Services.*;



import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User implements observer {
	String password;
	String email;
	String username;
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
			
			
			break;
			
			case 2:
				v.SetProvider("Etisalat");
				TotalFees = v.GetTotalFees();
		
				
			break;
			case 3:
				v.SetProvider("Orange");
				TotalFees = v.GetTotalFees();

				
			break;
			case 4:
				v.SetProvider("We");
				TotalFees = v.GetTotalFees();
				
				
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
		
			break;
			
			case 2:
				v2.SetProvider("Etisalat");
				TotalFees = v2.GetTotalFees();
				
				
			break;
			case 3:
				v2.SetProvider("Orange");
				TotalFees = v2.GetTotalFees();
				
				
			break;
			case 4:
				
				v2.SetProvider("We");
				TotalFees = v2.GetTotalFees();
			
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
					

				break;
				
				case 2:
					
					v3.SetLandlinesFactory("Quarter Receipt" , amount);
					TotalFees = v3.GetTotalFees();

					
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
					
			
				break;
				
				case 2:
					
					v4.SetDonationFactory("School" , amount);
					TotalFees = v4.GetTotalFees();
					
					
				break;
				
				case 3:
					v4.SetDonationFactory("CancerHospital" , amount);
					TotalFees = v4.GetTotalFees();

					
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

	public void AddToWallet(double amount) {
		// Get money from credit card

		// If success, Add amount to wallet
		wallet.amount += amount;
	}
	
	public void CheckDiscount() {
		if(Admin.flag == false) {
			System.out.println("There is no Discount rightnow..");
		}else {
			System.out.println("There is "+Admin.type);
		}
		
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
