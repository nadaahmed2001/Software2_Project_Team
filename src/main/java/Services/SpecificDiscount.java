package Services;

public class SpecificDiscount extends DiscountDecorator{
	
	Service service;
    public SpecificDiscount(Service service) {
       
    	this.service = service;
    }
	

	public void AddDiscount(double discount) {
		 
		service.AddDiscount(discount);
		
	}
	public double GetTotalFees() {
		
		return super.GetTotalFees();
	}
	}
