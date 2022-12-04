
public class DonationsService implements Service{
	public DonationFactory obj;
	public double amount;
	public double fees;
	private static DonationsService instance= null; 

	public DonationsService() {
		this.fees = 10.0;
	}
	public void Setfees(double fees) {
		this.fees =  fees;
	}
	
	public void SetDonationFactory(String type , double amount ){
		if(type.equals("School"))
			obj = new School(amount);
		else if(type.equals("NGOs"))
			obj = new NGOs(amount);
		else
			obj = new CancerHospital(amount);
	}
	public  static DonationsService GetInstance() {
		if(instance == null) {
			instance = new DonationsService();
		}
		return instance;
	}

	public double GetTotalFees() {
		
		return obj.totalFees() + instance.fees;
	}

	public void AddDiscount(double discount) {
		instance.fees-=discount;
		System.out.println("Donations Service after discount is: " + instance.fees + "\n");
		
	}
	
}
