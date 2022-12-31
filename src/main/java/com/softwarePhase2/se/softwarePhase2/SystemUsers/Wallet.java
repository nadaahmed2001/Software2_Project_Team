package com.softwarePhase2.se.softwarePhase2.SystemUsers;


public class Wallet {
	public double amount = 1500;//total amount in wallet
	
	public String showWallet(User user) {
		if (DataBase.CheckUserInfo(user.getEmail(),user.getPassword())) {
			for(int i=0;i<50;i++) {
				if (DataBase.userInfo[i][0].equals(user.email)) {
					return "The amount in Wallet: "+DataBase.userInfo[i][2];
				}
			}
		}
		return  "Email doesn't exist";
	}
	
	public void TakeFromWallet(User user , double Amount) {
		for(int i=0;i<50;i++) {
			if (DataBase.userInfo[i][0].equals(user.email)) {
				DataBase.userInfo[i][2] = String.valueOf(Double.valueOf(DataBase.userInfo[i][2]) - Amount);
			}
		
		}
	}
	
	public String AddtoWallet(User user , double Amount) {
		if (DataBase.CheckUserInfo(user.getEmail(),user.getPassword())) {
			for(int i=0;i<50;i++) {
				if (DataBase.userInfo[i][0].equals(user.email)) {
					DataBase.userInfo[i][2] = String.valueOf(Double.valueOf(DataBase.userInfo[i][2]) + Amount);
					DataBase.WalletTransaction[i][0] = user.getEmail();
					DataBase.WalletTransaction[i][1] =  Double.toString(Amount);
					return "Added successfully";
				}
			}
		}
		return "Email doesn't exist";
		

	}
		
}

