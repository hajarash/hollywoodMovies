package movieApp;

import java.awt.EventQueue;

import javax.swing.JFrame;

import oracle.jdbc.driver.Message;
import javax.swing.JLabel;

public class ErrorMsg {

	private JFrame frame;
	private static String message;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		message = args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorMsg window = new ErrorMsg();
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
	public ErrorMsg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 250, 250, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(message);
		lblNewLabel.setBounds(6, 53, 238, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
