package movieApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.SwitchPoint;
import java.util.Date;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView {

	private JFrame frame;
	private JTextField userText;
	private JTextField userPw;
	private static LoginView window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		QueryHandler.getDriverRunning();
		// QueryHandler.loginAuthentication("Mohzin!", "123");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		
		JLabel lblNewLabel = new JLabel("Welcome to Hollywood Gig Market Place");
		lblNewLabel.setBounds(153, 18, 264, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(45, 71, 70, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 140, 70, 16);
		frame.getContentPane().add(lblPassword);
		
		userText = new JTextField();
		userText.setBounds(161, 66, 235, 26);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		userPw = new JPasswordField();
		userPw.setBounds(161, 135, 235, 26);
		frame.getContentPane().add(userPw);
		userPw.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				String password = userPw.getText();
				String username = userText.getText();
				int type = QueryHandler.loginAuthentication(username, password);
				// type = 2;
				if (type == 5) {
					window.frame.setVisible(false);;
					String[] args = {"username/password doesn't match"};
					LoginView.main(null);
					ErrorMsg.main(args);
					userPw.setText(null);
					userText.setText(null);
					
				}else {
					// SQL command to get full name for the username
					String fName= "";
					LoginView.launchProgram(username, fName, type);
				}
					
			}
		});
		btnLogin.setBounds(222, 173, 117, 29);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				// RegisterView myRV = new RegisterView();
				window.frame.setVisible(false);
				RegisterView.main(null);
			}
		});
		btnRegister.setBounds(222, 243, 117, 29);
		frame.getContentPane().add(btnRegister);
	}
	
	public static void launchProgram(String username, String fName, int type) {
		String[] args = {username,fName};
		window.frame.setVisible(false);
		switch (type){
		case 0:
			ProducerView.main(args);
			break;
		case 1:
			WriterView.main(args);
			break;
		case 2:
			ActorView.main(args);
			break;
		case 3:
			DirectorView.main(args);
			break;
		}
	}
}
