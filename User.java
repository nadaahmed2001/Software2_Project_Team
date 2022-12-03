//import java.sql.Ref;
import java.util.Scanner;

public class User {
	String password;
	String email;
	String username;
	Wallet wallet = new Wallet();
	CreditCard credit = new CreditCard();
	Refund refund ;
	Scanner scan = new Scanner(System.in);
	// Login
	public boolean login(String email, String pass) {
		if (DataBase.CheckUserInfo(email, pass)) {
			this.email = email;
			this.password = pass;
			System.out.println("Login Successfully");
			return true;
		} else 
			System.out.println("Email or Password is incorrect");
		    return false;
	}

	public boolean signUp(String email, String pass, String username) {
		if (DataBase.AddUserInfo(email, pass)) {
			this.email = email;
			this.password = pass;
			this.username = username;
			System.out.println("SignUp Successfully");
			return true;
		} else
			System.out.println("Email is already Exists");
		return false;
	}

	void search(String serviceName) {
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
					System.out.println(" a.	Vodafone");
					System.out.println(" b.	Etisalat");
					System.out.println(" c.	Orange");
					System.out.println(" d.	We");
					
				}
				else if(Result[i].equalsIgnoreCase("Internet Payment services")) {
					System.out.println(" a.	Vodafone");
					System.out.println(" b.	Etisalat");
					System.out.println(" c.	Orange");
					System.out.println(" d.	We");
					
				}
				else if(Result[i].equalsIgnoreCase("Landline services")) {
					System.out.println(" a.	Monthly receipt");
					System.out.println(" b.	Quarter receipt");	
				}
				else if(Result[i].equalsIgnoreCase("Donations")) {
					System.out.println(" a.	Cancer Hospital");
					System.out.println(" b.	Schools");
					System.out.println(" c.	NGOs (Non profitable organizations)");
					
					
				}
		}}

	}

	void pay(int servicenumber) {
		
		int specificServiceNumber = 0;
		String mobile;
		double amount;
		Service v1;
		double TotalFees=0;
		switch (servicenumber) {
		
		case 1:// mobile recharge
			
			System.out.println("Choose:\n 1-Vodafone \n 2-Etisalat \n 3-Mobinil \n 4- We");
	
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			
			System.out.print("Enter mobile number: ");
			mobile = scan.next();
			System.out.print("Enter Amount: ");
			amount = scan.nextDouble();
			scan.nextLine();
			
			switch (specificServiceNumber) {
			case 1: // vodafone
			v1 = new MobileRechargeService("Vodafone" , mobile , amount);
			 TotalFees = v1.GetTotalFees();
			
			
		
			break;
			
			case 2:
				v1 = new MobileRechargeService("Etisalat" , mobile , amount);
				 TotalFees = v1.GetTotalFees();
				
				
			break;
			case 3:
				v1 = new MobileRechargeService("Orange" , mobile , amount);
				 TotalFees = v1.GetTotalFees();
				
			break;
			case 4:
				v1 = new MobileRechargeService("We" , mobile , amount);
				 TotalFees = v1.GetTotalFees();
				
				
			break;
			
			}
		case 2: // internet Payment services
			System.out.println("Choose:\n 1-Vodafone \n 2-Etisalat \n 3-Mobinil \n 4- We");
			
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			
			System.out.print("Enter mobile number: ");
			mobile = scan.next();
			System.out.print("Enter Amount: ");
			amount = scan.nextDouble();
			scan.nextLine();
			
			switch (specificServiceNumber) {
			case 1: // vodafone
			v1 = new InternetPaymentService("Vodafone" , mobile , amount);
			 TotalFees = v1.GetTotalFees();
		
		
			break;
			
			case 2:
				v1 = new InternetPaymentService("Etisalat" , mobile , amount);
				 TotalFees = v1.GetTotalFees();
				
				
			break;
			case 3:
				v1 = new InternetPaymentService("Orange" , mobile , amount);
				 TotalFees = v1.GetTotalFees();
				
			break;
			case 4:
				v1 = new InternetPaymentService("We" , mobile , amount);
				 TotalFees = v1.GetTotalFees();
			
			break;
			}
			
			break;
			
		
		case 3: // Landline Service
			System.out.println("Choose:\n 1-Monthly Receipt \n 2-Quarter Receipt \n ");
			
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			System.out.print("Enter Amount: ");
			amount = scan.nextDouble();
			scan.nextLine();
			
			switch (specificServiceNumber) {
				case 1: //Monthly Receipt
				v1 = new LandlinesService("Monthly Receipt" , amount);
				 TotalFees = v1.GetTotalFees();
			
				break;
				case 2:
					v1 = new LandlinesService("Quarter Receipt", amount);
					 TotalFees = v1.GetTotalFees();
					
					
				break;
		
			}

			break;
			
		case 4: // Donations
			System.out.println("Choose:\n 1-NGOs \n 2-School \n 3-Canser Hospital \n ");
			
			// Take input specificServiceNumber from user
			specificServiceNumber=scan.nextInt();
			scan.nextLine();
			System.out.print("Enter Amount: ");
			amount = scan.nextDouble();
			scan.nextLine();
			
			switch (specificServiceNumber) {
				case 1:
				v1 = new DonationsService("NGOs" , amount);
				 TotalFees = v1.GetTotalFees();
				 //v1.discount.addDiscount();
			
				break;
				case 2:
					v1 = new DonationsService("School", amount);
					 TotalFees = v1.GetTotalFees();
					
					
				break;
				case 3:
					v1 = new DonationsService("CancerHospital"  , amount);
					 TotalFees = v1.GetTotalFees();
					
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
					//System.out.println("Credit amount : " + this.credit.ShowCreditCard(this));
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

	public void RequestRefund(String serviceName, double amount) {
		this.refund = new Refund();
		this.refund.user = new User();
		this.refund.serviceName = serviceName;
		this.refund.amount = amount;
		this.refund.user.email = email;
		this.refund.addRefundToArrayList(refund);
	}

	void AddToWallet(double amount) {
		// Get money from credit card

		// If success, Add amount to wallet
		wallet.amount += amount;
	}

	void CheckDiscount(String serviceName) {
	}
	

}
