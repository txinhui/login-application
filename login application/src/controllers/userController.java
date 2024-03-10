// controller class
package controllers;

import model.User;
import model.userModel;
import view.userView;

public class userController {
	private userModel model;
	private userView view;
	
	public userController (userModel model, userView view) {
        this.model = model;
        this.view = view;
    }

    public String displayUserByUsername (String username) {
        User user = model.getUserByUsername(username);
        return view.displayUser(user);
    }
    
    public String displayUserByPW (String password) {
    	User userPW = model.getUserByPassword(password);
    	return view.displayUserPW(userPW);
  
    }
}
