//import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;

public class Admin {
	
	String email;
	String password;
	Refund requestRefund;
	Service service;
	public static boolean flag = false;
	public static String type = "";
	

	public Boolean login(String email, String pass) {
		if (DataBase.CheckAdminInfo(email, pass)) {
			this.email = email;
			this.password = pass;
			System.out.println("Login Successfully");
			return true;
		} else
			System.out.println("Email or Password is incorrect");
		return false;
	}

	void AddServiceProvider(Provider provider) {
	}

	void AddDiscount() {
		System.out.println("Choose: 1-Overall Discout   2-Specific Discount");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		double d;
		
		switch(n){
		case 1:

			System.out.println("Enter the Discount: ");
			d = scan.nextDouble();
			
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
				d = scan.nextDouble();
				
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


	public void getListOfRefunds() {
		System.out.println("List of Refunds: ");
		System.out.println("-------------------------");
		for(int i=0;i<DataBase.noOfRefunds;i++) {
			System.out.println("ServiceName: "+DataBase.AllRefundRequests.get(i).serviceName);
			System.out.println("User Email: "+DataBase.AllRefundRequests.get(i).user.email);
			System.out.println("Amount: "+DataBase.AllRefundRequests.get(i).amount);
			System.out.println("State: "+DataBase.AllRefundRequests.get(i).state);
		}
	}
	
	public void AcceptOrReject(String email, boolean state) {
		for(int i=0;i<DataBase.noOfRefunds;i++) {
			if(email.equals(DataBase.AllRefundRequests.get(i).user.email)) {
				if(state==true) {
					DataBase.AllRefundRequests.get(i).user.wallet=new Wallet();
					for (int j = 0; j < 50; j++) {
						if (DataBase.userInfo[j][0].equals(DataBase.AllRefundRequests.get(i).user.email)) {
							DataBase.userInfo[j][2]=String.valueOf(DataBase.AllRefundRequests.get(i).amount + Double.valueOf(DataBase.userInfo[j][2]));
						}
	
					}
					DataBase.AllRefundRequests.get(i).user.wallet.amount+=DataBase.AllRefundRequests.get(i).amount;
				}
				DataBase.AllRefundRequests.remove(i);
				DataBase.noOfRefunds--;
			}
		}
	}
	
}
