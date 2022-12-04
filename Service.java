
public interface Service  {
	public double amount=0.0;//Amount from user
	public double fees=0.0;//Service fees
	public double GetTotalFees();
	public void Setfees(double fees);
	public void AddDiscount(double discount) ;
}
