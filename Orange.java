
public class Orange implements Provider {
	public String mobileNum ;
	public double amount;
	public double fees;

	public Orange(String mobileNum, double amount){
		this.amount = amount;
		this.mobileNum = mobileNum;
		fees = 10;
	}

	public double totalFees() {
		
		return fees+ amount;
	}
}
