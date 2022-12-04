import java.util.Scanner;

public class SpecificDiscount implements Discount {
	public Service service;
	
	public double addDiscount( double discount ) {
		
		return 0;}
	
//		System.out.println("Choose Service to add discount:");
//		System.out.println("1-MobileRechargement Service \n 2-InternetPayment Service"
//				+ " \n 3-LAndlines Service \n 4-Donations Service");
//		
//		Scanner scan = new Scanner(System.in);
//		int option = scan.nextInt();
//		
//		switch(option) {
//		case 1:
//			service = new MobileRechargeService();
//		    service.Setfees(service.fees-discount);
//		    
//		
//			break;
//		case 2:
//			service = new InternetPaymentService();
//			 service.Setfees(service.fees-discount);
//			 
//			break;
//		case 3:
//			service = new LandlinesService();
//			 service.Setfees(service.fees-discount);
//			 
//			break;
//		case 4:
//			service = new DonationsService();
//			 service.Setfees(service.fees-discount);
//			break;				
//		}
//		return service.fees;
//		
//	}
		
//		if (type.equals("InternetPayment Service")) {
//			service = new InternetPaymentService();
//			
//			return service.GetTotalFees()- 0.01;
//		
//		}else if (type.equals("MobileRecharge Service")) {
//			service = new MobileRechargeService();
//			 return service.GetTotalFees()- 0.05;
//		
//		}else if (type.equals("Landlines Service")) {
//			service = new LandlinesService();
//			 return service.GetTotalFees()- 0.1;
//			
//		}else {
//			service = new DonationsService();
//			 return service.GetTotalFees()- 0.5;
//		
//		}
	//}
	
}
