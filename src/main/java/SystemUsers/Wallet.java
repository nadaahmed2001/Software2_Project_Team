package SystemUsers;


public class Wallet {
public double amount = 1500;
public Double showWallet(User user) {
	for(int i=0;i<50;i++) {
		if (DataBase.userInfo[i][0].equals(user.email)) {
			return Double.valueOf(DataBase.userInfo[i][2]);
		}
	}
	return amount;
}
public void TakeFromWallet(User user , double amount) {
	for(int i=0;i<50;i++) {
		if (DataBase.userInfo[i][0].equals(user.email)) {
			DataBase.userInfo[i][2] = String.valueOf(Double.valueOf(DataBase.userInfo[i][2]) - amount);
		}
	
}
}
}
