
public class LandlinesService implements Service{
	LandlinesFactory obj;
	public  double fees;
	public double amount;
	private static LandlinesService instance= null; 
	
	public LandlinesService() {
		this.fees = 10.0;
	}
	
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	
	public void SetLandlinesFactory(String type , double amount) {
		if(type.equals("Monthly Receipt"))
			obj = new MonthlyReceipt(amount );
		else
			obj = new QuarterReceipt(amount);
	}
	
	public  static LandlinesService GetInstance() {
		if(instance == null) {
			instance = new LandlinesService();
		}
		return instance;
	}

	
	public double GetTotalFees() {
		return obj.totalFees() + instance.fees;
	}

	public void AddDiscount(double discount) {
		instance.fees-=discount;
		System.out.println("Landlines Service after discount is: " + instance.fees + "\n");
		
	}

}
