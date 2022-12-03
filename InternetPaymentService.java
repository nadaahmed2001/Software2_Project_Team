
public class InternetPaymentService implements Service{
	public Provider provider;
	public double fees;
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	public InternetPaymentService() {}
	
	public InternetPaymentService(String type , String mobile , double amount) {
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
