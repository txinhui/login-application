// model class
package model;

import java.sql.*;

public class userModel {
    
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3308/user _account";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password123";
	
    private Connection connection;

    public userModel() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User getUsers (String username, String password, String name, String role) {
    	User user = null;
    	String sql = "SELECT * FROM user_details";
    	try (PreparedStatement statement = connection.prepareStatement(sql)) {
    		ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String getUsername = resultSet.getString("username");
                String getName = resultSet.getString("name");
                String getPassword = resultSet.getString("password");
                String getRole = resultSet.getString("role");
                user = new User(getUsername, getName, getPassword, getRole);
            }
    	}
    	catch (SQLException e){
        	System.err.println("Connection failed!");
            e.printStackTrace();
    	}
    	return user;
    }
    
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM user_details WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String getUsername = resultSet.getString("username");
                String getName = resultSet.getString("name");
                String getPassword = resultSet.getString("password");
                String getRole = resultSet.getString("role");
                user = new User(getUsername, getName, getPassword, getRole);
            }
        } 
        catch (SQLException e) {
        	System.err.println("Connection failed!");
            e.printStackTrace();
        }
        return user;
    }
    
    public User getUserByPassword(String password) {
        User user = null;
        String sql = "SELECT * FROM user_details WHERE password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String getUsername = resultSet.getString("username");
                String getName = resultSet.getString("name");
                String getPassword = resultSet.getString("password");
                String getRole = resultSet.getString("role");
                user = new User(getUsername, getName, getPassword, getRole);
            }
        } 
        catch (SQLException e) {
        	System.err.println("Connection failed!");
            e.printStackTrace();
        }
        return user;
    }
    
    public User getUserByRole(String role) {
        User user = null;
        String sql = "SELECT * FROM user_details WHERE role = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String getUsername = resultSet.getString("username");
                String getName = resultSet.getString("name");
                String getPassword = resultSet.getString("password");
                String getRole = resultSet.getString("role");
                user = new User(getUsername, getName, getPassword, getRole);
                
            }
        } 
        catch (SQLException e) {
        	System.err.println("Connection failed!");
            e.printStackTrace();
        }
        return user;
    }
    
}
