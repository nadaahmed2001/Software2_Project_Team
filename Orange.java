
public class Orange extends Provider {
	public String mobileNum ;
	public double amount;
	public double fees;

	public Orange(){
		fees = 10;
	}

	public double totalFees(double amount) {
		return fees+ amount;
	}
}
