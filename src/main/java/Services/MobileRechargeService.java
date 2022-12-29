package Services;


public class MobileRechargeService implements Service {
	public Provider provider;
	public double amount;
	public double fees;
	private static MobileRechargeService instance= null; 
	
		private MobileRechargeService() {
			this.fees = 10.0;
		}
		
		public void Setfees(double fees) {
			this.fees =  fees;
		}
		
		public void SetProvider(String type) {
			if (type.equals("Vodafone")) 
				provider = new Vodafone();
			else if (type.equals("Etisalat")) 
				provider = new Etisalat();
			
	    	else if (type.equals("Orange")) 
				provider = new Orange();
			
		    else if (type.equals("We")) 
				provider = new We();
		}
		
		public  static MobileRechargeService GetInstance() {
			if(instance == null) {
				instance = new MobileRechargeService();
			}
			return instance;
		}
		
		public double GetTotalFees() {
			return provider.templete() + instance.fees;
		}


		public void AddDiscount(double discount) {
			
			instance.fees-=discount;
			System.out.println("MobileRecharge after discount is: " + instance.fees +"\n");
			

			
		}
	
}
