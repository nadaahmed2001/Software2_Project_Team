
public class Etisalat implements Provider{
	public String mobileNum;
	public double amount;
	public double fees;
	public Etisalat(String mobileNum, double amount){
		this.amount = amount;
		this.mobileNum = mobileNum;
		fees = 25;
	}


	public double totalFees() {
		
		return fees+ amount;
	}
}