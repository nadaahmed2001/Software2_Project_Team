
public class MobileRechargeService implements Service {
	public Provider provider;
	public double amount;
	public double fees;
		public MobileRechargeService() {}
		public void Setfees(double fees) {
			this.fees =  fees;
		}
	
	public MobileRechargeService(String type) {

		if (type.equals("Vodafone")) 
			provider = new Vodafone();
		else if (type.equals("Etisalat")) 
			provider = new Etisalat();
		
    	else if (type.equals("Orange")) 
			provider = new Orange();
		
	    else if (type.equals("We")) 
			provider = new We();
		
	}
	public double GetTotalFees() {
		return provider.templete();
	}
	
}
