
public class MobileRechargeService implements Service {
	public Provider provider;
	public double amount;
	public double fees;
		public MobileRechargeService() {}
		public void Setfees(double fees) {
			this.fees =  fees;
		}
	
	public MobileRechargeService(String type , String mobile , double amount) {
		if (type.equals("Vodafone")) 
			provider = new Vodafone(mobile , amount);
		
		else if (type.equals("Etisalat")) 
			provider = new Etisalat(mobile , amount);
		
		else if (type.equals("Orange")) 
			provider = new Orange(mobile , amount);
		
	   else if (type.equals("We")) 
			provider = new We(mobile , amount);
		
	}
	public double GetTotalFees() {
		return provider.totalFees();
	}
	
}
