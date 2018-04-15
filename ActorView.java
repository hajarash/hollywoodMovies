package movieApp;

import java.awt.EventQueue;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class ActorView {

	private JFrame frame;
	private static String username;
	private static String fName;
	private JTextField fromText;
	private JTextField toText;
	private JTextField producerTxt;
	private JTextField budgetText;
	private JTextField heightText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		username = args[0];
		fName = args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActorView window = new ActorView();
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
	public ActorView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Actor");
		
		JLabel lblWe = new JLabel("Welcome " + fName + "("+ username + ")");
		lblWe.setBounds(26, 6, 261, 16);
		frame.getContentPane().add(lblWe);
		
		JLabel lblAvailabilityLabel = new JLabel("When are you free?");
		lblAvailabilityLabel.setBounds(26, 28, 193, 16);
		frame.getContentPane().add(lblAvailabilityLabel);
		
		JLabel lblFrommmddyyyy = new JLabel("From: (mm-dd-yyyy)");
		lblFrommmddyyyy.setBounds(26, 56, 154, 16);
		frame.getContentPane().add(lblFrommmddyyyy);
		
		fromText = new JTextField();
		fromText.setBounds(26, 84, 193, 26);
		frame.getContentPane().add(fromText);
		fromText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("To: (mm-dd-yyyy)");
		lblNewLabel.setBounds(26, 122, 154, 16);
		frame.getContentPane().add(lblNewLabel);
		
		toText = new JTextField();
		toText.setBounds(26, 150, 193, 26);
		frame.getContentPane().add(toText);
		toText.setColumns(10);
		
		
		// get the values for the following on start of the program
		fromText.setText(QueryHandler.fromText(username));
		toText.setText(QueryHandler.toText(username));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(26, 335, 194, 29);
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblYourMovies = new JLabel("Your Movies:");
		lblYourMovies.setBounds(26, 376, 91, 16);
		frame.getContentPane().add(lblYourMovies);
		
		JLabel lblNewLabel_1 = new JLabel("Movie Search");
		lblNewLabel_1.setBounds(305, 6, 132, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblProducer = new JLabel("Producer:");
		lblProducer.setBounds(305, 43, 61, 16);
		frame.getContentPane().add(lblProducer);
		
		producerTxt = new JTextField();
		producerTxt.setBounds(307, 66, 130, 26);
		frame.getContentPane().add(producerTxt);
		producerTxt.setColumns(10);
		
		budgetText = new JTextField();
		budgetText.setBounds(307, 132, 130, 26);
		frame.getContentPane().add(budgetText);
		budgetText.setColumns(10);
		
		JLabel label = new JLabel("<Budget");
		label.setBounds(305, 109, 61, 16);
		frame.getContentPane().add(label);
		
		JLabel lblGenre = new JLabel("Genre(s)");
		lblGenre.setBounds(305, 188, 128, 16);
		frame.getContentPane().add(lblGenre);
		
		JCheckBox cbHorror = new JCheckBox("horror");
		cbHorror.setBounds(305, 216, 75, 23);
		frame.getContentPane().add(cbHorror);
		
		JCheckBox cbFantasy = new JCheckBox("fantasy");
		cbFantasy.setBounds(400, 216, 100, 23);
		frame.getContentPane().add(cbFantasy);
		
		JCheckBox cbAction = new JCheckBox("action");
		cbAction.setBounds(305, 240, 87, 23);
		frame.getContentPane().add(cbAction);
		
		JCheckBox cbThriller = new JCheckBox("thriller");
		cbThriller.setBounds(400, 240, 87, 23);
		frame.getContentPane().add(cbThriller);
		
		JCheckBox cbDrama = new JCheckBox("drama");
		cbDrama.setBounds(305, 265, 80, 23);
		frame.getContentPane().add(cbDrama);
		
		JCheckBox cbComedy = new JCheckBox("comedy");
		cbComedy.setBounds(400, 265, 87, 23);
		frame.getContentPane().add(cbComedy);
		
		JCheckBox cbMusical = new JCheckBox("musical");
		cbMusical.setBounds(305, 290, 87, 23);
		frame.getContentPane().add(cbMusical);
		
		JCheckBox cbScifi = new JCheckBox("sci-fi");
		cbScifi.setBounds(400, 290, 87, 23);
		frame.getContentPane().add(cbScifi);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(305, 398, 182, 54);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("What Languages do you speak?");
		lblNewLabel_2.setBounds(26, 188, 214, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JCheckBox chckbxEnglish = new JCheckBox("English");
		chckbxEnglish.setBounds(26, 216, 80, 23);
		frame.getContentPane().add(chckbxEnglish);
		
		JCheckBox chckbxSpanish = new JCheckBox("Spanish");
		chckbxSpanish.setBounds(111, 216, 87, 23);
		frame.getContentPane().add(chckbxSpanish);
		
		JCheckBox chckbxFrench = new JCheckBox("French");
		chckbxFrench.setBounds(191, 216, 91, 23);
		frame.getContentPane().add(chckbxFrench);
		
		// get the languages spoken by actor
		boolean[] langs = QueryHandler.getLangs(username);
		if (langs[0]) {
			chckbxEnglish.setSelected(true);
		}
		
		if (langs[1]) {
			chckbxSpanish.setSelected(true);
		}
		if (langs[2]) {
			chckbxFrench.setSelected(true);
		}
		
		JLabel lblYourHeight = new JLabel("Your Height:");
		lblYourHeight.setBounds(26, 244, 91, 16);
		frame.getContentPane().add(lblYourHeight);
		
		heightText = new JTextField();
		heightText.setBounds(26, 264, 193, 26);
		frame.getContentPane().add(heightText);
		heightText.setColumns(10);
		
		heightText.setText(QueryHandler.getHeight(username));
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(26, 302, 54, 16);
		frame.getContentPane().add(lblGender);
		
		boolean isMale = QueryHandler.isMale(username);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(79, 300, 61, 23);
		frame.getContentPane().add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(152, 300, 87, 23);
		frame.getContentPane().add(rdbtnFemale);
		
		String maxR = QueryHandler.maxRatingAVG();
		String minR = QueryHandler.minRatingAVG();
		
		JLabel lblGenresWithMaximum = new JLabel("Genre With Maximum Rating :");
		lblGenresWithMaximum.setBounds(490, 43, 204, 16);
		frame.getContentPane().add(lblGenresWithMaximum);
		
		JLabel maxRatingLabel = new JLabel("");
		maxRatingLabel.setText(maxR);
		maxRatingLabel.setBounds(490, 89, 193, 16);
		frame.getContentPane().add(maxRatingLabel);
		
		JLabel lblGenreWithMinimum = new JLabel("Genre With Minimum Rating :");
		lblGenreWithMinimum.setBounds(490, 155, 193, 16);
		frame.getContentPane().add(lblGenreWithMinimum);
		
		JLabel minRatingLabel = new JLabel("");
		minRatingLabel.setText(minR);
		minRatingLabel.setBounds(495, 220, 182, 16);
		frame.getContentPane().add(minRatingLabel);
		
		/*JLabel testLabel = new JLabel("New label");
		testLabel.setBounds(26, 416, 193, 16);
		frame.getContentPane().add(testLabel);*/
		LinkedList<Movie> movieList = QueryHandler.employeeMovies(username);
		int x= 26;
		int y = 416;
		int width = 193;
		int height = 16;
		renderMovies(Math.min(movieList.size(),5), movieList, x,y,width,height);
		
		if(isMale) {
			rdbtnMale.setSelected(true);
			rdbtnFemale.setSelected(false);
		}else {
			rdbtnMale.setSelected(false);
			rdbtnFemale.setSelected(true);
		}
		
		
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFemale.setSelected(false);
			}
		});
			
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMale.setSelected(false);
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				QueryHandler.updateInformationForActor(username, fromText.getText(), toText.getText(), chckbxEnglish.isSelected(), chckbxSpanish.isSelected(),
						chckbxFrench.isSelected(), heightText.getText(), rdbtnMale.isSelected());
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() { 
			String producer = producerTxt.getText();
			String budgetString = budgetText.getText();
			int budget = -1;
			boolean stopRunning = false;
			public void actionPerformed(ActionEvent arg0) {
				if (!budgetString.equals("")) {
					try {
						Integer.parseInt(budgetString);
					}catch(Exception e) {
						String[] args = {"bad budget format"};
						ErrorMsg.main(args);
						stopRunning =true;
					}
				}
				if(!stopRunning) {
					boolean[] genres = QueryHandler.genreArrayMaker(cbHorror.isSelected(), cbFantasy.isSelected(),
						cbAction.isSelected(), cbThriller.isSelected(), cbDrama.isSelected(), cbComedy.isSelected(),
						cbMusical.isSelected(), cbScifi.isSelected());
				
					LinkedList<Movie> listToRender = QueryHandler.searchForMovies(budget, producer, genres);
					listToRender.add(new Movie("hello", new Date()));
					listToRender.add(new Movie("hi", new Date()));
					listToRender.add(new Movie("bye", new Date()));
					SearchView.moviesResults = listToRender;
					String[] args = {"0",username};
					SearchView.main(args);
				}else {
					budgetText.setText("");
				}
			}
		});
		
	}
	public void renderMovies(int maximum, LinkedList<Movie> movies, int x , int y , int width, int height){
		for (int i = 0 ; i < maximum; i++) {
			JLabel myLabel = new JLabel(movies.get(i).getTitle());
			myLabel.setBounds(x,y+(height+2)*i, width, height);
			frame.getContentPane().add(myLabel);
		}
	}
}
