
public class Etisalat extends Provider{
	public String mobileNum;
	public double amount;
	public double fees;
	public Etisalat(){
		fees = 5;
	}


	public double totalFees(double amount) {
		return fees+ amount;
	}
}