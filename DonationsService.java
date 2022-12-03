
public class DonationsService implements Service{
	public DonationFactory obj;
	public double fees;

	public DonationsService() {}
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	
	public DonationsService(String type , double amount){
		if(type.equals("School"))
			obj = new School(amount);
		else if(type.equals("NGOs"))
			obj = new NGOs(amount);
		else
			obj = new CancerHospital(amount);
	}

	public double GetTotalFees() {
		
		return obj.totalFees();
	}
	
}
