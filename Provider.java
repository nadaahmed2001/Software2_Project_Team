import java.util.Scanner;

public abstract class Provider  {
	public String mobileNum = null ;
	public double amount=0.0;
	public double fees=0.0;
	public abstract double totalFees(double money);
	public void getInformation() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter mobile number: ");
		mobileNum = scan.next();
		System.out.print("Enter Amount: ");
		amount = scan.nextDouble();
		scan.nextLine();
	}
	public double templete() {
		getInformation();
		return totalFees(amount);
	}
}
