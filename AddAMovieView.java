package movieApp;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class AddAMovieView {

	private JFrame frame;
	private static String producerUserName;
	private static String producerFName;
	private JTextField titleText;
	private JTextField releaseDate;
	private JTextField budgetText;
	private JTextField locationText;
	private JTextField startDateText;
	private JTextField endDateText;
	private JCheckBox actionCB;
	private JCheckBox comedyCB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		producerUserName = args[0];
		producerFName = args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAMovieView window = new AddAMovieView();
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
	public AddAMovieView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Title*");
		titleLabel.setBounds(28, 37, 61, 16);
		frame.getContentPane().add(titleLabel);
		
		titleText = new JTextField();
		titleText.setBounds(157, 32, 229, 26);
		frame.getContentPane().add(titleText);
		titleText.setColumns(10);
		
		JLabel rdLabel = new JLabel("Release Date*");
		rdLabel.setBounds(28, 65, 96, 16);
		frame.getContentPane().add(rdLabel);
		
		releaseDate = new JTextField();
		releaseDate.setToolTipText("");
		releaseDate.setBounds(157, 70, 229, 26);
		frame.getContentPane().add(releaseDate);
		releaseDate.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("(mm-dd-yyyy)");
		lblNewLabel.setBounds(28, 80, 96, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel budgetLabel = new JLabel("Budget");
		budgetLabel.setBounds(28, 108, 61, 16);
		frame.getContentPane().add(budgetLabel);
		
		budgetText = new JTextField();
		budgetText.setBounds(157, 103, 229, 26);
		frame.getContentPane().add(budgetText);
		budgetText.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(28, 140, 61, 16);
		frame.getContentPane().add(lblLocation);
		
		locationText = new JTextField();
		locationText.setBounds(157, 135, 229, 26);
		frame.getContentPane().add(locationText);
		locationText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Production Start");
		lblNewLabel_1.setBounds(28, 168, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		startDateText = new JTextField();
		startDateText.setBounds(157, 163, 229, 26);
		frame.getContentPane().add(startDateText);
		startDateText.setColumns(10);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setBounds(98, 196, 61, 16);
		frame.getContentPane().add(lblEnd);
		
		endDateText = new JTextField();
		endDateText.setBounds(157, 191, 229, 26);
		frame.getContentPane().add(endDateText);
		endDateText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Genre(s)");
		lblNewLabel_2.setBounds(196, 259, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JCheckBox horrorCB = new JCheckBox("Horror");
		horrorCB.setBounds(28, 301, 87, 23);
		frame.getContentPane().add(horrorCB);
		
		JCheckBox fantasyCB = new JCheckBox("Fantasy");
		fantasyCB.setBounds(127, 301, 87, 23);
		frame.getContentPane().add(fantasyCB);
		
		actionCB = new JCheckBox("Action");
		actionCB.setBounds(226, 301, 87, 23);
		frame.getContentPane().add(actionCB);
		
		JCheckBox thrillerCB = new JCheckBox("Thriller");
		thrillerCB.setBounds(326, 301, 96, 23);
		frame.getContentPane().add(thrillerCB);
		
		JCheckBox dramaCB = new JCheckBox("Drama");
		dramaCB.setBounds(31, 347, 87, 23);
		frame.getContentPane().add(dramaCB);
		
		comedyCB = new JCheckBox("Comedy");
		comedyCB.setBounds(127, 347, 96, 23);
		frame.getContentPane().add(comedyCB);
		
		JCheckBox musicalCB = new JCheckBox("Musical");
		musicalCB.setBounds(226, 347, 96, 23);
		frame.getContentPane().add(musicalCB);
		
		JCheckBox scifiCB = new JCheckBox("Sci-Fi");
		scifiCB.setBounds(326, 347, 96, 23);
		frame.getContentPane().add(scifiCB);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(166, 457, 117, 39);
		frame.getContentPane().add(btnAdd);
		frame.setTitle("Add a Movie");
		
		try {
			String[] info = {titleText.getText(), releaseDate.getText(), budgetText.getText(), locationText.getText(),
					startDateText.getText(), endDateText.getText()};
			boolean[] myGens = {horrorCB.isSelected(), fantasyCB.isSelected(), actionCB.isSelected(), thrillerCB.isSelected(),
					dramaCB.isSelected(), comedyCB.isSelected(), musicalCB.isSelected(), scifiCB.isSelected()};
			handleAdd(info, myGens);
			frame.setVisible(false);
			String[] args= {producerUserName, producerFName};
			ProducerView.main(args);
		}catch(Exception e) {
			String[] args = {e.getMessage()};
			ErrorMsg.main(args);
		}
	}
	public void handleAdd(String[] info, boolean[] genres  ) throws Exception{
		if (info[0].equals("")) {
			throw new Exception("Empty title");
		}
		if(info[1].equals("")) {
			throw new Exception("Empty date");
		}
		int budget=0;
		String loc = locationText.getText();
		Movie myMovie;
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			Date releaseDate = takeCareOfDate(info [1]);
			myMovie = new Movie(info[0], releaseDate);
			if(!startDateText.getText().equals("")) {
				startDate = takeCareOfDate(startDateText.getText());
			}
			if(!endDateText.getText().equals("")) {
				endDate = takeCareOfDate(endDateText.getText());
			}
		}catch (Exception e) {
			throw new Exception("bad date format");
		}
		if(!budgetText.getText().equals("")) {
			try {
				Integer.parseInt(budgetText.getText());
			}catch (Exception e) {
				throw new Exception ("bad budget format");
			}
		}
		myMovie.setAttributes(budget, genres, loc, 0, startDate, endDate, producerUserName);
		QueryHandler.addAMovie(myMovie);
	}
	public Date takeCareOfDate(String date) throws Exception {

		if(date.length()!= 10) {
			throw new Exception();
		}
		int mm = Integer.parseInt(date.substring(0, 2));
		int dd = Integer.parseInt(date.substring(3,5));
		int yyyy = Integer.parseInt(date.substring(6));
		return new Date(yyyy, mm, dd);
	}
}
