package Services;


public class School implements DonationFactory{
	public double amount;
	public double fees;
	public School( double amount){
		this.amount = amount;
		fees=22.0;
	}
	
	public double totalFees() {
			
			return amount + fees;
		}
	

}
