package Services;

public class OverallDiscount  extends DiscountDecorator  {
	
	Service service;
	public OverallDiscount(Service service) {
	
		this.service = service;
	}

	
	public void AddDiscount(double discount) {
	
		service.AddDiscount(discount);
		
	}
	public double GetTotalFees() {
		
		return super.GetTotalFees();
	}

}

