package com.softwarePhase2.se.softwarePhase2.SystemUsers;

public class Refund {
	public String serviceName ;
	public User user ;//The user who requests this refund
	public double amount;
	public String state;//Accepted or rejected or Suspended
	
	
	public String addRefundToArrayList( Refund r){
		//r.setState("Suspended");
		DataBase.AllRefundRequests.add(r);
		DataBase.noOfRefunds++;
		return "Refund Request Added Sucessfully";
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
}
