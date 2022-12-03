
public class Vodafone implements Provider {
	public String mobileNum  ;
	public double amount;
	public double fees;
	public Vodafone(String mobileNum, double amount){
		this.amount = amount;
		this.mobileNum = mobileNum;
		fees=20;
	}
	
	public double totalFees() {
		
		return amount + fees;
	}
}
