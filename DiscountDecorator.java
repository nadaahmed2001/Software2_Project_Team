
public class DiscountDecorator implements Service {

	Service s;
	
//	DiscountDecorator(Service service) {
//	        this.s = service;
//	    }
	
	public void AddDiscount(double discount) {
		s.AddDiscount(discount);
		   
	}
	public double GetTotalFees() {
		
		return s.GetTotalFees();
	}


	@Override
	public void Setfees(double fees) {
		// TODO Auto-generated method stub
		
	}



}
