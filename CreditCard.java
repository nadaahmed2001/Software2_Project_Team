
public class CreditCard {
	double amount = 2000;
	public Double ShowCreditCard(User user) {
		for(int i=0;i<50;i++) {
			if (DataBase.userInfo[i][0].equals(user.email)) {
				return Double.valueOf(DataBase.userInfo[i][3]);
			}
		}
		return amount;
	}
	public void TakeFromCredit(User user , double amount) {
		for(int i=0;i<50;i++) {
			if (DataBase.userInfo[i][0].equals(user.email)) {
				DataBase.userInfo[i][3] = String.valueOf(Double.valueOf(DataBase.userInfo[i][3]) - amount);
			}
		
	}
	}
}
