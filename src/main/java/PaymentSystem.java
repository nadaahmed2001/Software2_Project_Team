/*
 * Team members:        IDs:
 * * * * * * * *       * * * *
 * 1- Hadeer Adel          20201210
 * 2- Yasmine Ahmed        20200632
 * 3- Nada Ahmed           20190578
 * 4- Eman Ramadan         20200104
 */

import java.util.Scanner;
//import Services.*;
import SystemUsers.*;

public class PaymentSystem {

	

	public static void main(String[] args) {
		
		User user = new User();
		Admin admin = new Admin();
		boolean flag = false;
		char AorU = '0';// to know user or admin 2:Admin 1:user
		boolean registerAccepted = false;// to know if registered correctly
		Scanner scan = new Scanner(System.in);
		// login |sign up
		do {
			System.out.print("Enter 1:Admin | 2:User: ");
			int choiceAorU = scan.nextInt();
			
			if (choiceAorU == 1) {
				
				System.out.print("Enter 1:Login | 2:Exit: ");
				AorU = '2';
			} else {
				
				System.out.print("Enter 0:SignUp | 1:Login | 2:Exit: ");
				AorU = '1';
			}
			int choice = scan.nextInt();
			scan.nextLine();
			
			if (choice == 1) {
				System.out.print("Enter Email: ");
				String Email = scan.nextLine();
				System.out.print("Enter Password: ");
				String pass = scan.nextLine();
				if (choiceAorU == 1) {
					registerAccepted = admin.login(Email, pass);
					
				} else {
					registerAccepted = user.login(Email, pass);
				}
			} else if (choice == 0 && choiceAorU == 2) {
				System.out.print("Enter Username: ");
				String userName = scan.nextLine();
				System.out.print("Enter Email: ");
				String Email = scan.nextLine();
				System.out.print("Enter Password: ");
				String pass = scan.nextLine();
				

				registerAccepted = user.signUp(Email, pass, userName);

			} else if (choice == 2)
				break;
			if (registerAccepted) {
				if (AorU == '2') {// admin functionality
					
					System.out.println("1: Add Discount");
					System.out.println("2: Show List of Requests Refunds");
					System.out.println("3: Edit List of Requests Refunds");
					System.out.print("Select: ");
					int select = scan.nextInt();
					scan.nextLine();
					
						
					if (select==1) {
						
						admin.AddDiscount();
						
					}else if(select==2) {
						admin.getListOfRefunds();
					}else if(select==3) {
						System.out.print("Enter email of user: ");
						String emailRefund=scan.nextLine();
						System.out.print("Enter status of this refund 0:rejected | 1:Accepted: ");
						int state=scan.nextInt();
						
						boolean stateRefund;
						if(state==0)
							stateRefund = false;
							
						else
							stateRefund = true;
						
						scan.nextLine();
						admin.AcceptOrReject(emailRefund, stateRefund);
						admin.setAvailiabilty(stateRefund);
					}
					
					
				} else if(AorU =='1'){// user functionality
					System.out.println("1: Search Service");
					System.out.println("2: Show Wallet");
					System.out.println("3: Add request refund");
					System.out.println("4: Pay with special service");
					System.out.println("5: Check Discount");
					System.out.print("Select: ");
					int select = scan.nextInt();
					scan.nextLine();
					if(select==1) {
						System.out.print("Enter word to search: ");
						String s=scan.nextLine();
						user.search(s);
						
					}else if(select==2) {
						
						System.out.print("The amount in Wallet: "+user.wallet.showWallet(user));
						
					}else if(select==3) {
						if(flag == true) {
						
							System.out.print("Enter Service Name: ");
							String serviceName = scan.nextLine();
							System.out.print("Enter amount: ");
							Double amount = scan.nextDouble();
							scan.nextLine();
							user.RequestRefund(serviceName, amount);
							admin.RegistRequest(user);
						}else {
							System.out.println();
							System.out.println("You have not paid for any service yet to request refund!😒 \n");
						}
					}else if(select==4) {
					
						System.out.println("Enter Service Number: ");
						System.out.println("1-Mobile recharge services.");
						System.out.println("2-Internet Payment services.");
						System.out.println("3-Landline services ");
						System.out.println("4-Donations");
						System.out.print("Select: ");
						int n=scan.nextInt();
						user.pay(n);
						flag = true;
						
					}else {
						
						user.CheckDiscount();
					}
				}
			}
			scan.nextLine();
		} while (true);

	}
}
