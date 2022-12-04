
public class QuarterReceipt implements LandlinesFactory {
	public double amount;
	public double fees;
	public QuarterReceipt( double amount){
		this.amount = amount;
		fees=17.0;
	}
	
	public double totalFees() {
			
			return amount + fees;
		}

}
