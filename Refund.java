

public class Refund {
	public String serviceName ;
	public User user ;//The user who requests this refund
	public double amount;
	public String state;//Accepted or rejected or Suspended
	
	public void addRefundToArrayList(Refund r){
		r.state="Suspended";
		DataBase.AllRefundRequests.add(r);
		DataBase.noOfRefunds++;
	}
	
}
