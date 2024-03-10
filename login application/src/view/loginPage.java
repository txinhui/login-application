package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.userController;
import model.User;
import model.userModel;

public class loginPage extends JFrame implements ActionListener {

	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField usernameText;
	JPasswordField password_text;
	JButton submit, cancel;

	loginPage() {
		// Username Label
		user_label = new JLabel();
		user_label.setText("Username :");
		usernameText = new JTextField();

		// Password Label
		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JPasswordField();

		// login button
		submit = new JButton("Login");
		panel = new JPanel(new GridLayout(3, 1));
		panel.add(user_label);
		panel.add(usernameText);
		panel.add(password_label);
		panel.add(password_text);

		message = new JLabel();
		panel.add(message);
		panel.add(submit);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit.addActionListener(this);
		add(panel, BorderLayout.CENTER);
		setTitle("Login Page");
		setSize(400, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new loginPage();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		userModel model = new userModel();
		userView view = new userView();
		userController controller = new userController(model, view);

		String userName = usernameText.getText();
		String password = password_text.getText();

		if (userName.isEmpty() || password.isEmpty()) {
			SwingUtilities.invokeLater(() -> {
				message.setText("Please enter both fields.");
			});
			return; 
		}

		String dbUsername = controller.displayUserByUsername(userName);
		String dbPassword = controller.displayUserByPW(password);

		User user = model.getUserByUsername(userName);


		if (userName.equals(dbUsername)) {
			if (password.equals(dbPassword)) {
				if (user != null) {
					SwingUtilities.invokeLater(() -> {

						String dbRole = user.getRole();
						String dbName = user.getName();

						if (dbRole.equals("manager")) {
							JPanel loggedInPanel = new JPanel();
							loggedInPanel.add(new JLabel("<html>Welcome, " + dbName + "<br>Username: " + userName + "<br>Role: " + dbRole + "</html>"));
							JLabel label = new JLabel("<html><a href=\"https://www.google.com\">Restricted Link</a></html>");

							label.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									if (Desktop.isDesktopSupported()) {
										try {
											Desktop.getDesktop().browse(new java.net.URI("https://www.google.com"));
										} catch (Exception ex) {
											ex.printStackTrace();
										}
									}
								}
							});
							loggedInPanel.add(label);

							// Remove the current login JPanel from the JFrame
							getContentPane().remove(panel);

							// Add the new JPanel to the JFrame
							getContentPane().add(loggedInPanel);

							// Ensure that the JFrame is repainted
							revalidate();
							repaint();
						}
						else {
							JPanel loggedInPanel = new JPanel();
							loggedInPanel.add(new JLabel("<html>Welcome, " + dbName + "<br>Username: " + userName + "<br>Role: " + dbRole + "</html>"));
							// Remove the current login JPanel from the JFrame
							getContentPane().remove(panel);

							// Add the new JPanel to the JFrame
							getContentPane().add(loggedInPanel);

							// Ensure that the JFrame is repainted
							revalidate();
							repaint();
						}
					});
				}
			}
			else {
				SwingUtilities.invokeLater(() -> {
					message.setText("Password is incorrect.");
				});
			}
		}
		else {
			System.out.println(dbUsername);
			SwingUtilities.invokeLater(() -> {
				message.setText("Username not found ");
			});
		}
	}

}
