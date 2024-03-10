import java.sql.*;

public class database {

	public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection to MySQL database
            String url = "jdbc:mysql://127.0.0.1:3308/user _account";
            String USERNAME = "root";
            String PASSWORD = "password123";
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            
            // check connection
            //System.out.println("Connected to the database!");
            
            // Create statement
            statement = connection.createStatement();

            // Execute query
            resultSet = statement.executeQuery("SELECT * FROM user_details");

            // Process the result set
            while (resultSet.next()) {
            	
                int id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                
                //System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password + ", Role: " + role);
            }
        } 
        catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found!");
            e.printStackTrace();
        } 
        catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        } 
        finally {
            // Close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
