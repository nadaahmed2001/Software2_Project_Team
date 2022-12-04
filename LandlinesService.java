
public class LandlinesService implements Service{
	LandlinesFactory obj;
	public double fees;
	public LandlinesService() {}
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	
	public LandlinesService(String type , double amount) {
		if(type.equals("MonthlyReceipt"))
			obj = new MonthlyReceipt(amount );
		else
			obj = new QuarterReceipt(amount);
	}
	public double GetTotalFees() {
		return obj.totalFees();
	}

}
