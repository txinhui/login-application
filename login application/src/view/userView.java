// view class
package view;

import model.User;

public class userView {
	
	public String displayUser(User user) {
		if(user != null) {
			return user.getUsername();
		}
		else {
			return "Wrong username";
		}
	}
	
	public String displayUserPW (User user) {
		if (user != null) {
			return user.getPassword();
		}
		else {
			return "Wrong password";
		}
    }
	
	public String displayUserRole (User user) {
		return user.getRole();
	}
}
