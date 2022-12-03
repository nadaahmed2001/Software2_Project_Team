
public class We implements Provider {
	public String mobileNum ;
	public double amount;
	public double fees;
	public We(String mobileNum, double amount){
		this.amount = amount;
		this.mobileNum = mobileNum;
		fees = 5;
	}


	public double totalFees() {
		
		return fees+ amount;
	}
}
