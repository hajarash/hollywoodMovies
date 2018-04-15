package movieApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AreaAveragingScaleFilter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.util.LinkedList;


public class ProducerView {
	private static String username;
	private static String fName;
	private static LinkedList<Movie> producersMovies;
	private static int m;
	private JFrame frame;
	private JTextField fromText;
	private JTextField toText;
	private JTextField budgetText;
	private JTextField heightText;
	private static ProducerView window;
	private JTextField listText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		username = args[0];
		fName = args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ProducerView();
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
	public ProducerView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Producer");
		
		JLabel lblWe = new JLabel("Welcome" + fName + "("+ username + ")");
		lblWe.setBounds(26, 6, 261, 16);
		frame.getContentPane().add(lblWe);
		
		JLabel lblAvailabilityLabel = new JLabel("When are you free?");
		lblAvailabilityLabel.setBounds(26, 43, 193, 16);
		frame.getContentPane().add(lblAvailabilityLabel);
		
		JLabel lblFrommmddyyyy = new JLabel("From: (mm-dd-yyyy)");
		lblFrommmddyyyy.setBounds(26, 71, 154, 16);
		frame.getContentPane().add(lblFrommmddyyyy);
		
		fromText = new JTextField();
		fromText.setBounds(26, 99, 193, 26);
		frame.getContentPane().add(fromText);
		fromText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("To: (mm-dd-yyyy)");
		lblNewLabel.setBounds(26, 137, 154, 16);
		frame.getContentPane().add(lblNewLabel);
		
		toText = new JTextField();
		toText.setBounds(26, 162, 193, 26);
		frame.getContentPane().add(toText);
		toText.setColumns(10);
		
		JLabel lblYourBudget = new JLabel("Your Budget:");
		lblYourBudget.setBounds(26, 204, 87, 16);
		frame.getContentPane().add(lblYourBudget);
		
		budgetText = new JTextField();
		budgetText.setBounds(26, 232, 193, 26);
		frame.getContentPane().add(budgetText);
		budgetText.setColumns(10);
		
		// get the values for the following on start of the program
		fromText.setText(QueryHandler.fromText(username));
		toText.setText(QueryHandler.toText(username));
		budgetText.setText(QueryHandler.budgetText(username));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(25, 270, 194, 29);
		frame.getContentPane().add(btnUpdate);
		
		// update:
		btnUpdate.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				QueryHandler.updateInformationForProducer(username, fromText.getText(), toText.getText(), budgetText.getText());
			}
		});
		
		JLabel lblYourMovies = new JLabel("Your Movies");
		lblYourMovies.setBounds(26, 339, 140, 16);
		frame.getContentPane().add(lblYourMovies);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(182, 334, 75, 29);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblActorSearch = new JLabel("Actor Search");
		lblActorSearch.setBounds(339, 6, 100, 16);
		frame.getContentPane().add(lblActorSearch);
		
		JLabel lblHeight = new JLabel("Height  (cm)");
		lblHeight.setBounds(339, 43, 100, 16);
		frame.getContentPane().add(lblHeight);
		
		heightText = new JTextField();
		heightText.setBounds(451, 38, 87, 26);
		frame.getContentPane().add(heightText);
		heightText.setColumns(10);
		
		JCheckBox lessThanCB = new JCheckBox("Less Than");
		lessThanCB.setBounds(371, 67, 193, 23);
		frame.getContentPane().add(lessThanCB);
		
		JLabel lblNewLabel_1 = new JLabel("Languages:");
		lblNewLabel_1.setBounds(339, 104, 100, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JCheckBox actorEng = new JCheckBox("English");
		actorEng.setBounds(339, 133, 87, 23);
		frame.getContentPane().add(actorEng);
		
		JCheckBox actorSpan = new JCheckBox("Spanish");
		actorSpan.setBounds(339, 163, 92, 23);
		frame.getContentPane().add(actorSpan);
		
		JCheckBox actorFrench = new JCheckBox("French");
		actorFrench.setBounds(339, 200, 128, 23);
		frame.getContentPane().add(actorFrench);
		
		JLabel lblGenreOfInterest = new JLabel("Genre(s) of Interest");
		lblGenreOfInterest.setBounds(339, 253, 128, 16);
		frame.getContentPane().add(lblGenreOfInterest);
		
		JCheckBox actorHorror = new JCheckBox("horror");
		actorHorror.setBounds(339, 281, 75, 23);
		frame.getContentPane().add(actorHorror);
		
		JCheckBox actorFantasy = new JCheckBox("fantasy");
		actorFantasy.setBounds(436, 281, 100, 23);
		frame.getContentPane().add(actorFantasy);
		
		JCheckBox actorAction = new JCheckBox("action");
		actorAction.setBounds(339, 311, 87, 23);
		frame.getContentPane().add(actorAction);
		
		JCheckBox actorThriller = new JCheckBox("thriller");
		actorThriller.setBounds(436, 311, 87, 23);
		frame.getContentPane().add(actorThriller);
		
		JCheckBox actorDrama = new JCheckBox("drama");
		actorDrama.setBounds(339, 340, 80, 23);
		frame.getContentPane().add(actorDrama);
		
		JCheckBox actorComedy = new JCheckBox("comedy");
		actorComedy.setBounds(436, 339, 87, 23);
		frame.getContentPane().add(actorComedy);
		
		JCheckBox actorMusical = new JCheckBox("musical");
		actorMusical.setBounds(339, 366, 87, 23);
		frame.getContentPane().add(actorMusical);
		
		JCheckBox actorScifi = new JCheckBox("sci-fi");
		actorScifi.setBounds(436, 366, 87, 23);
		frame.getContentPane().add(actorScifi);
		
		JButton btnActorSearch = new JButton("Search");
		btnActorSearch.setBounds(339, 476, 184, 61);
		frame.getContentPane().add(btnActorSearch);
		
		JLabel lblWriter = new JLabel("Writer Search");
		lblWriter.setBounds(597, 6, 111, 16);
		frame.getContentPane().add(lblWriter);
		
		JLabel lblPreferredGenre = new JLabel("Preferred Genre(s)");
		lblPreferredGenre.setBounds(596, 43, 112, 16);
		frame.getContentPane().add(lblPreferredGenre);
		
		JCheckBox writerHorror = new JCheckBox("horror");
		writerHorror.setBounds(597, 81, 128, 23);
		frame.getContentPane().add(writerHorror);
		
		JCheckBox writerFantasy = new JCheckBox("fantasy");
		writerFantasy.setBounds(597, 116, 128, 23);
		frame.getContentPane().add(writerFantasy);
		
		JCheckBox writerAction = new JCheckBox("action");
		writerAction.setBounds(597, 151, 128, 23);
		frame.getContentPane().add(writerAction);
		
		JCheckBox writerThriller = new JCheckBox("thriller");
		writerThriller.setBounds(597, 186, 128, 23);
		frame.getContentPane().add(writerThriller);
		
		JCheckBox writerDrama = new JCheckBox("drama");
		writerDrama.setBounds(597, 221, 100, 23);
		frame.getContentPane().add(writerDrama);
		
		JCheckBox writerComedy = new JCheckBox("comedy");
		writerComedy.setBounds(597, 249, 111, 23);
		frame.getContentPane().add(writerComedy);
		
		JCheckBox writerMusical = new JCheckBox("musical");
		writerMusical.setBounds(597, 284, 128, 23);
		frame.getContentPane().add(writerMusical);
		
		JCheckBox writerScifi = new JCheckBox("sci-fi");
		writerScifi.setBounds(597, 319, 128, 23);
		frame.getContentPane().add(writerScifi);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(339, 401, 61, 16);
		frame.getContentPane().add(lblGender);
		
		JCheckBox actorMale = new JCheckBox("Male");
		actorMale.setBounds(339, 429, 75, 23);
		frame.getContentPane().add(actorMale);
		
		JCheckBox actorFemale = new JCheckBox("Female");
		actorFemale.setBounds(436, 429, 87, 23);
		frame.getContentPane().add(actorFemale);
		
		JButton btnWriterSearch = new JButton("Search");
		btnWriterSearch.setBounds(591, 476, 159, 61);
		frame.getContentPane().add(btnWriterSearch);
		
		JLabel lblNewLabel_2 = new JLabel("Director Search");
		lblNewLabel_2.setBounds(807, 6, 100, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JCheckBox dirHorror = new JCheckBox("horror");
		dirHorror.setBounds(807, 81, 128, 23);
		frame.getContentPane().add(dirHorror);
		
		JCheckBox dirFantasy = new JCheckBox("fantasy");
		dirFantasy.setBounds(807, 116, 128, 23);
		frame.getContentPane().add(dirFantasy);
		
		JCheckBox dirAction = new JCheckBox("action");
		dirAction.setBounds(807, 151, 128, 23);
		frame.getContentPane().add(dirAction);
		
		JCheckBox dirThriller = new JCheckBox("thriller");
		dirThriller.setBounds(807, 186, 128, 23);
		frame.getContentPane().add(dirThriller);
		
		JCheckBox dirDrama = new JCheckBox("drama");
		dirDrama.setBounds(807, 221, 128, 23);
		frame.getContentPane().add(dirDrama);
		
		JCheckBox dirComedy = new JCheckBox("comedy");
		dirComedy.setBounds(807, 249, 128, 23);
		frame.getContentPane().add(dirComedy);
		
		JCheckBox dirMusical = new JCheckBox("musical");
		dirMusical.setBounds(807, 281, 128, 23);
		frame.getContentPane().add(dirMusical);
		
		JCheckBox dirScifi = new JCheckBox("sci-fi");
		dirScifi.setBounds(807, 319, 128, 23);
		frame.getContentPane().add(dirScifi);
		
		JLabel lblAreasOfExpertise = new JLabel("Area(s) of Expertise");
		lblAreasOfExpertise.setBounds(807, 43, 141, 16);
		frame.getContentPane().add(lblAreasOfExpertise);
		
		JButton btnDirectorSearch = new JButton("Search");
		btnDirectorSearch.setBounds(807, 476, 154, 61);
		frame.getContentPane().add(btnDirectorSearch);
		
		JButton listBtn = new JButton("List");
		listBtn.setBounds(371, 643, 117, 29);
		frame.getContentPane().add(listBtn);
		
		listBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int rating = Integer.parseInt(listText.getText());
					LinkedList<WriterDirectorObject> myWD =QueryHandler.witersWithHigherRatingThan(rating);
					SearchView.wDResult = myWD;
					String args[] = {"1", username};
					SearchView.main(args);
				}catch(Exception ex) {
					String[] args = {"bad format or empty"};
					ErrorMsg.main(args);
				}
				
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("find writers with movies");
		lblNewLabel_3.setBounds(357, 604, 154, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(" higher than following rating");
		lblNewLabel_4.setBounds(339, 625, 197, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		listText = new JTextField();
		listText.setBounds(536, 620, 87, 26);
		frame.getContentPane().add(listText);
		listText.setColumns(10);
		
		
		
		producersMovies= QueryHandler.producerMovies(username);
		SearchView.moviesAvailable = producersMovies;
		
		handleMovies();
		
		btnActorSearch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				int height;
				try {
					if (heightText.getText() == null) {
						throw new Exception("empty height");
					}
					height = Integer.parseInt(heightText.getText());
				} catch(Exception e) {
					height = -1;
					lessThanCB.setSelected(false);
				}
				
				boolean[] languages = QueryHandler.languageArrayMaker(actorEng.isSelected(), actorSpan.isSelected(), actorFrench.isSelected());
				boolean[] genres = QueryHandler.genreArrayMaker(actorHorror.isSelected(), actorFantasy.isSelected(),
						actorAction.isSelected(), actorThriller.isSelected(), actorDrama.isSelected(), actorComedy.isSelected(),
						actorMusical.isSelected(), actorScifi.isSelected());
				boolean[] gender = QueryHandler.genderArrayMaker(actorMale.isSelected(), actorFemale.isSelected());
				LinkedList<ActorObject> listToRender = QueryHandler.searchForActors(height, lessThanCB.isSelected(), languages, genres, gender);
				SearchView.ActorResults = listToRender;
				String[] args = {"2" , username};
				SearchView.main(args);
			}
		});
		btnWriterSearch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				boolean[] genres = QueryHandler.genreArrayMaker(writerHorror.isSelected(), writerFantasy.isSelected(),
						writerAction.isSelected(), writerThriller.isSelected(), writerDrama.isSelected(), writerComedy.isSelected(),
						writerMusical.isSelected(), writerScifi.isSelected());
				
				LinkedList<WriterDirectorObject> listToRender = QueryHandler.searchForEmployees("writers_writes" ,genres);
				SearchView.wDResult = listToRender;
				String[] args = {"1" , username};
				SearchView.main(args);
			}
		});
		btnDirectorSearch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				boolean[] genres = QueryHandler.genreArrayMaker(dirHorror.isSelected(), dirFantasy.isSelected(),
						dirAction.isSelected(), dirThriller.isSelected(), dirDrama.isSelected(), dirComedy.isSelected(),
						dirMusical.isSelected(), dirScifi.isSelected());
				
				LinkedList<WriterDirectorObject> listToRender = QueryHandler.searchForEmployees("director" ,genres);
				SearchView.wDResult = listToRender;
				String[] args = {"1" , username};
				SearchView.main(args);
			}
		});
		
	btnAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String[] args = {username,fName};
			window.frame.setVisible(false);
			AddAMovieView.main(args);
		}
	});
	}
	public void handleMovies(){
		
		int x = 26;
		int y=370;
		int width = 154;
		int height = 16;
		int maximum  = Math.min(producersMovies.size(), 5);
			for (int i = 0 ; i < maximum; i++) {
				JLabel myLabel = new JLabel(producersMovies.get(i).getTitle());
				myLabel.setBounds(x,y+(height+2)*i, width, height);
				frame.getContentPane().add(myLabel);
			}
		handleDelete();
	}
	public void handleDelete() {
		int x = 185;
		int y=370;
		int width = 35;
		int height = 16;
		int maximum  = Math.min(producersMovies.size(), 5);
		for (int i = 0 ; i < maximum; i++) {
			deleteTheMovie(producersMovies.get(i),x, y+(height+2), width, height, i);
		}
	}
	public void deleteTheMovie(Movie movie, int x , int y, int width, int height, int index) {
		JButton myNewButton = new JButton("Delete");
		myNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String myText= myNewButton.getText();
				if(!myText.equals("Restore")) {
					QueryHandler.deleteAMovie(movie.getTitle(), movie.getReleaseDate());
					myNewButton.setText("Restore");
				}else {
					QueryHandler.addAMovie(movie);
					myNewButton.setText("Delete");
				}
			}
		});
		
	}
}
