package com.softwarePhase2.se.softwarePhase2.SystemUsers;

import  com.softwarePhase2.se.softwarePhase2.Services.*;


import java.util.Scanner;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Admin implements Subject{
	
	String email;
	String password;
	Refund requestRefund;
	Service service;
	public static boolean flag = false;
	public static DiscountDecorator discounts = new DiscountDecorator();
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


	@PostMapping(value="/AddDiscount/overall")
	public String AddOverallDiscount(@RequestParam double discount ) {
			service =  MobileRechargeService.GetInstance();
			service = new OverallDiscount(service );
			service.AddDiscount(discount);
			
			service =  InternetPaymentService.GetInstance();
			service = new OverallDiscount( service);
			service.AddDiscount(discount);
		
			service =  LandlinesService.GetInstance();
			service = new OverallDiscount( service);
			service.AddDiscount(discount);
			
			service =  DonationsService.GetInstance();
			service = new OverallDiscount( service);
			service.AddDiscount(discount);
			
			flag = true;
			discounts.setDiscounts(discount+ " Overall Discount on MobileRecharge Service, InternetPayment Service, "
					+ "Landlines Service, DonationsService\n "+discounts.getDiscounts());
		   return discount+" is added successfully";
			
		}
		@PostMapping(value="/AddDiscount/specificDiscounts")
		public String AddSpecificDiscount(@RequestParam String OPTION,@RequestParam double discount ) {
				int option = 0;
				if(OPTION.equals("MobileRechargement"))
					option=1;
				else if(OPTION.equals("InternetPayment"))
					option=2;
				else if(OPTION.equals("Landlines"))
					option=3;
				else if(OPTION.equals("Donations"))
					option=4;
				switch(option) {
				
				case 1:
					service =  MobileRechargeService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(discount);
					flag = true;
					discounts.setDiscounts(discount+" Specific Discount on MobileRecharge Service\n " +discounts.getDiscounts());

					break;
				case 2:
					service =  InternetPaymentService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(discount);
					flag = true;
					discounts.setDiscounts(discount+" Specific Discount on InternetPayment Service\n "+discounts.getDiscounts());


					break;
				case 3:
					service =  LandlinesService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(discount);
					flag = true;
					discounts.setDiscounts(discount+" Specific Discount on Landlines Service\n "+discounts.getDiscounts());

				break;
				case 4:
					
					service =  DonationsService.GetInstance();
					service = new SpecificDiscount( service);
					service.AddDiscount(discount);
					flag = true;
					discounts.setDiscounts(discounts+" Specific Discount on Donations Service\n"+discounts.getDiscounts());

					break;				
				}
				return discount+" is added successfully";
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
					DataBase.RefundRequestTransaction.get(i).state = "Accepted";
					DataBase.AllRefundRequests.get(i).getUser().wallet=new Wallet();
					for (int j = 0; j < 50; j++) {
						if (DataBase.userInfo[j][0].equals(DataBase.AllRefundRequests.get(i).getUser().getEmail())) {
							DataBase.userInfo[j][2]=String.valueOf(DataBase.AllRefundRequests.get(i).getAmount() 
									+ Double.valueOf(DataBase.userInfo[j][2]));
						}
	
					}
					DataBase.AllRefundRequests.get(i).getUser().wallet.amount+=DataBase.AllRefundRequests.get(i).getAmount();
				}
				DataBase.RefundRequestTransaction.get(i).state = "Rejected";
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
	@GetMapping(value="/userTransactions")
	public String ListAllUserTransaction() {
			int flag = 0;
			String returnvalue="";
			
			returnvalue+="\nList all user transaction: ";
			returnvalue+="\n-------------------------------";
			for(int i=0 ; i<50; i++ ) {
				if (DataBase.userInfo[i][0] != null) {
					  for(int j=0 ; j<50; j++) {
							if (DataBase.userInfo[i][0].equals(DataBase.PayTransaction[j][0])){
								if (flag == 1) {
									   
										returnvalue+="\nPaid for Service: "+DataBase.PayTransaction[j][1];
										returnvalue+="\nService fees: "+DataBase.PayTransaction[j][2];
								}else {
										
										returnvalue+="\nUser Email: "+DataBase.PayTransaction[j][0];
										returnvalue+="\nPaid for Service: "+DataBase.PayTransaction[j][1];
										returnvalue+="\nService fees: " + DataBase.PayTransaction[j][2];
										flag = 1;
								}
							}
						
						if (DataBase.RefundRequestTransaction.size() != 0) {
							if (j < DataBase.RefundRequestTransaction.size()) {
								if (DataBase.userInfo[i][0].equals(DataBase.RefundRequestTransaction.get(j).user.email)){
									if (flag == 1) {
										
										returnvalue+="\nRefund of Service: " + DataBase.RefundRequestTransaction.get(j).serviceName;
										returnvalue+="\nAmount: "+DataBase.RefundRequestTransaction.get(j).amount;
										returnvalue+="\nState: "+DataBase.RefundRequestTransaction.get(j).state;
									}else {
							
										returnvalue+="\nUser Email: "+DataBase.RefundRequestTransaction.get(j).user.email;
										returnvalue+="\nRefund of Service: "+DataBase.RefundRequestTransaction.get(j).serviceName;
										returnvalue+="\nAmount: "+DataBase.RefundRequestTransaction.get(j).amount;
										returnvalue+="\nState: "+DataBase.RefundRequestTransaction.get(j).state;
										
										flag = 1;
								   }
									 
								}
							}
						}
						
						if (DataBase.userInfo[i][0].equals(DataBase.WalletTransaction[j][0])){
							 if (flag == 1) {
									
									returnvalue+="\nAmount added to the wallet: "+DataBase.WalletTransaction[j][1];
							}else {
							
									returnvalue+="\nUser Email: "+DataBase.WalletTransaction[j][0];
									returnvalue+="\nAmount added to the wallet: "+DataBase.WalletTransaction[j][1];
									flag = 1;
							}
							
						}
					}
					  flag = 0;
				
				}else {
					return "No transactions yet";
				}
			}

			return returnvalue;
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
