
public class MonthlyReceipt implements LandlinesFactory {
	
	public double amount;
	public double fees;
	public MonthlyReceipt( double amount){
		this.amount = amount;
		fees=19.0;
	}
	
	public double totalFees() {
			
			return amount + fees;
		}
}
