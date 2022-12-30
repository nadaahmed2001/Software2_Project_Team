package com.softwarePhase2.se.softwarePhase2.SystemUsers;

import  com.softwarePhase2.se.softwarePhase2.Services.*;
import java.util.Scanner;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Admin implements Subject{
	
	String email;
	String password;
	Refund requestRefund;
	Service service;
	public static boolean flag = false;
	public static String type = "";
	boolean availability;
	private ArrayList observers ;
	
	public Admin() {
		observers = new ArrayList(); 
	}

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
	@GetMapping(value="/Admins/{email}/{password}")
	public String login(@PathVariable("email") String email,@PathVariable("password") String password) {
		if (DataBase.CheckAdminInfo(email, password)) {
			this.email = email;
			this.password = password;
			return "Login Successfully";
		} else
			return "Email or Password is incorrect";
		
	}

	void AddServiceProvider(Provider provider) {
	}

	@PostMapping(value="/AddDiscount")
	public void AddDiscount(@RequestBody String discount) {
		System.out.println("Choose: 1-Overall Discout   2-Specific Discount");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		String d;
		
		switch(n){
		case 1:

			System.out.println("Enter the Discount: ");
			d = scan.nextLine();
			
			service =  MobileRechargeService.GetInstance();
			service = new OverallDiscount(service );
			service.AddDiscount(d);
		
			
			
			
			service =  InternetPaymentService.GetInstance();
			service = new OverallDiscount( service);
			service.AddDiscount(d);
		
			
			service =  LandlinesService.GetInstance();
			service = new OverallDiscount( service);
			service.AddDiscount(d);
	
			
			
			service =  DonationsService.GetInstance();
			service = new OverallDiscount( service);
			service.AddDiscount(d);
			
			flag = true;
			type = "Overall Discount on MobileRecharge Service, InternetPayment Service, "
					+ "Landlines Service, DonationsService";
		
	
			
			
		break;
		
		case 2:
				System.out.println("Choose Service to add discount:");
				System.out.println("1-MobileRechargement Service \n 2-InternetPayment Service"
						+ " \n 3-LAndlines Service \n 4-Donations Service");
				

				int option = scan.nextInt();
				System.out.println("Enter the Discount: ");
				d = scan.nextLine();
				
				switch(option) {
				
				case 1:
					service =  MobileRechargeService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(d);
					flag = true;
					type = "Specific Discount on MobileRecharge Service";

				
					break;
				case 2:
					service =  InternetPaymentService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(d);
					flag = true;
					type = "Specific Discount on InternetPayment Service";


					break;
				case 3:
					service =  LandlinesService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(d);
					flag = true;
					type = "Specific Discount on Landlines Service";



					 
				break;
				case 4:
					
					service =  DonationsService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(d);
					flag = true;
					type = "Specific Discount on Donations Service";

					break;				
				}
				
			break;

		}
		
	}

	@GetMapping(value="/Refunds")
	public ArrayList<Refund> getListOfRefunds() {
		System.out.println("List of Refunds: ");
		System.out.println("-------------------------");
		for(int i=0;i<DataBase.noOfRefunds;i++) {
			System.out.println("ServiceName: "+DataBase.AllRefundRequests.get(i).getServiceName());
			System.out.println("User Email: "+DataBase.AllRefundRequests.get(i).getUser().email);
			System.out.println("Amount: "+DataBase.AllRefundRequests.get(i).getAmount());
			System.out.println("State: "+DataBase.AllRefundRequests.get(i).getState());
		}
		return DataBase.AllRefundRequests;
	}
	
	@GetMapping(value="/Refunds/{email}/{serviceName}/{state}")
	public void AcceptOrReject(@PathVariable("email") String email,@PathVariable("state") String state,@PathVariable("serviceName") String serviceName) {
		for(int i=0;i<DataBase.noOfRefunds;i++) {
			if(email.equals(DataBase.AllRefundRequests.get(i).getUser().getEmail()) &&(DataBase.AllRefundRequests.get(i).serviceName.equals(serviceName))) {
				if(state=="accepted") {
					DataBase.AllRefundRequests.get(i).getUser().wallet=new Wallet();
					for (int j = 0; j < 50; j++) {
						if (DataBase.userInfo[j][0].equals(DataBase.AllRefundRequests.get(i).getUser().getEmail())) {
							DataBase.userInfo[j][2]=String.valueOf(DataBase.AllRefundRequests.get(i).getAmount() 
									+ Double.valueOf(DataBase.userInfo[j][2]));
						}
	
					}
					DataBase.AllRefundRequests.get(i).getUser().wallet.amount+=DataBase.AllRefundRequests.get(i).getAmount();
				}
				DataBase.AllRefundRequests.remove(i);
				DataBase.noOfRefunds--;
			}
		}
	}
	
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			observer observer = (observer)observers.get(i);
			observer.update(availability);
		}
			 
		
	}


	
	public void RegistRequest(observer o) {
		
		observers.add(o);
	}

	
	public void RemoveRequest(observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
		observers.remove(i);
		}
		
	}
	public void AvailiabiltyChanged() {
		 
		notifyObservers();
		 
		}
		 
		public void setAvailiabilty(boolean message) {
			
		 this.availability = message;
		 AvailiabiltyChanged();
		 
		}
}
