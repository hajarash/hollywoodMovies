package movieApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class RegisterView {

	private JFrame frame;
	private JTextField fNameText;
	private JTextField userText;
	private JPasswordField passwordField;
	private static RegisterView regWindow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regWindow = new RegisterView();
					regWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Register");
		
		JLabel lblFName = new JLabel("Full Name");
		lblFName.setBounds(20, 29, 99, 16);
		frame.getContentPane().add(lblFName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 76, 99, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPW = new JLabel("Password");
		lblPW.setBounds(20, 119, 99, 16);
		frame.getContentPane().add(lblPW);
		
		JRadioButton rdbtnProducer = new JRadioButton("Producer");
		rdbtnProducer.setBounds(20, 175, 99, 23);
		frame.getContentPane().add(rdbtnProducer);
		rdbtnProducer.setSelected(true);
		
		JRadioButton rdbtnWriter = new JRadioButton("Writer");
		rdbtnWriter.setBounds(131, 175, 84, 23);
		frame.getContentPane().add(rdbtnWriter);
		
		JRadioButton rdbtnActor = new JRadioButton("Actor");
		rdbtnActor.setBounds(227, 175, 84, 23);
		frame.getContentPane().add(rdbtnActor);
		
		JRadioButton rdbtnDirector = new JRadioButton("Director");
		rdbtnDirector.setBounds(323, 175, 99, 23);
		frame.getContentPane().add(rdbtnDirector);
		
		rdbtnProducer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnWriter.setSelected(false);
				rdbtnActor.setSelected(false);
				rdbtnDirector.setSelected(false);
			}
		});
		
		rdbtnWriter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnProducer.setSelected(false);
				rdbtnActor.setSelected(false);
				rdbtnDirector.setSelected(false);
			}
		});
		
		rdbtnActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnWriter.setSelected(false);
				rdbtnProducer.setSelected(false);
				rdbtnDirector.setSelected(false);
			}
		});
		
		rdbtnDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnWriter.setSelected(false);
				rdbtnActor.setSelected(false);
				rdbtnProducer.setSelected(false);
			}
		});
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(165, 210, 117, 29);
		frame.getContentPane().add(btnRegister);
		
		fNameText = new JTextField();
		fNameText.setBounds(112, 24, 263, 26);
		frame.getContentPane().add(fNameText);
		fNameText.setColumns(10);
		
		userText = new JTextField();
		userText.setBounds(112, 71, 263, 26);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(112, 114, 263, 26);
		frame.getContentPane().add(passwordField);
		
		
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = fNameText.getText();
				String username = userText.getText();
				String usrPassword = passwordField.getText();
				
				int type;
				if(rdbtnProducer.isSelected()) {
					type = 0;
				}else if(rdbtnWriter.isSelected()) {
					type= 1;
				}else if(rdbtnActor.isSelected()) {
					type = 2;
				}else {
					type = 3;
				}
				if(!fName.equals("") && !username.equals("") && !usrPassword.equals("")) {
					if(QueryHandler.registerAuthentication(username)) {
						QueryHandler.registerUser(fName, username, usrPassword, type);
					}else {
						String[] errMsg = {"Yhis username already exists"};
						userText.setText("");
						ErrorMsg.main(errMsg);
					}
				}else {
					String[] errMsg = {"You must fill all the fields"};
					ErrorMsg.main(errMsg);
				}
			}
		});
	}
	
}
