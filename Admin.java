//import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;

public class Admin {
	
	String email;
	String password;
	Refund requestRefund;
	Discount discount;
	Service service;
	

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

	void AddDiscount(Discount discount) {
		System.out.println("Choose: 1-Overall Discout   2-Specific Discount");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		switch(n){
			case 1:
				discount = new OverallDiscount();
				
			break;
			case 2:
				discount = new SpecificDiscount();
				//discount.addDiscount(0.05);
				
				
			
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
							System.out.print("Amount: "+DataBase.AllRefundRequests.get(i).amount);
							System.out.print("Amountst: "+Double.valueOf(DataBase.userInfo[j][2]));
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
