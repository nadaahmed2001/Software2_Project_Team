import java.util.ArrayList;

public class DataBase {
	public static int counter = 0;
	public static String userInfo[][] = new String[50][4];
	public static String AdminInfo[][] = new String[20][2];
	static ArrayList<Refund> AllRefundRequests = new ArrayList<Refund>();
	static int noOfRefunds = 0;
	static ArrayList<Provider> Providers = new ArrayList<Provider>();//Array of objects that holds all service providers

	// check admin email and password[login]
	public static boolean CheckAdminInfo(String Email, String pass) {
		for (int i = 0; i < 20; i++) {
			AdminInfo[i][0] = "";
			AdminInfo[i][1] = "";
		}
		AdminInfo[0][0] = "yasmin@gmail.com";
		AdminInfo[0][1] = "20200632";
		AdminInfo[1][0] = "eman@gmail.com";
		AdminInfo[1][1] = "20200104";
		for (int i = 0; i < 20; i++) {
			if (AdminInfo[i][0].equals(Email))
				return true;
		}
		return false;
	}

	// check user email and password[login]
	public static boolean CheckUserInfo(String Email, String pass) {
		if (counter == 0)
			return false;
		for (int i = 0; i < 50; i++) {
			if (userInfo[i][0].equals(Email) )
				return true;
		}
		return false;
	}

	// check user info and check if it is already exit[sign up]
	public static boolean AddUserInfo(String Email, String pass) {
		if (counter == 0) {
			for (int i = 0; i < 50; i++) {
				userInfo[i][0] = "";
				userInfo[i][1] = "";
				userInfo[i][2] = "1500";
				userInfo[i][3] = "2000";
			}
		}
		if (!CheckUserInfo(Email, pass)) {
			userInfo[counter][0] = Email;
			userInfo[counter][1] = pass;
			counter++;
			return true;
		}
		return false;
	}
}
