package movieApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.tools.ForwardingJavaFileManager;
import javax.management.Query;
import javax.swing.JButton;

public class SearchView {
	private final int LABEL_SIZE_X = 796;
	private final int LABEL_CONSTANT_X = 58;
	private final int BUTTON_SIZE_X = 117;
	private final int BUTTON_CONSTANT_X = 866;
	private int i;
	private int j;
	private int moviesAvai;
	private final int HEIGHT = 29;
	
	private JFrame frame;
	private static int searchType; // 0 for movies  1 for Writer/Director  2 for Actor
	private static String username;
	public static LinkedList<ActorObject> ActorResults;
	public static LinkedList<WriterDirectorObject> wDResult;
	public static LinkedList<Movie> moviesResults = new LinkedList<Movie>();
	public static LinkedList<Movie> moviesAvailable = new LinkedList<Movie>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		searchType = Integer.parseInt(args[0]);
		username = args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchView window = new SearchView();
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
	public SearchView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 750);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(621, 6, 361, 29);
		frame.getContentPane().add(lblNewLabel);
		/*
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(866, 43, 117, 29);
		frame.getContentPane().add(btnNewButton);*/
		switch(searchType) {
			case 0:
				doMovies();
				break;
			case 1:
				doWD();
				break;
			case 2: 
				doActors();
				break;
		}
		
		
		
	}
	private void doMovies() {
		int baseY = 43;
		for( int h = 0 ; h < moviesResults.size() ; h++) {
			createRowForMovies(baseY+ h * (HEIGHT+2), moviesResults.get(h));
		}
	}
	public void createRowForMovies(int y, Movie m) {
		JLabel myNewLabel = new JLabel(m.toString());
		myNewLabel.setBounds(LABEL_CONSTANT_X, y , LABEL_SIZE_X , HEIGHT);
		frame.getContentPane().add(myNewLabel);
		
		JButton myNewJButton = new JButton("Like");
		myNewJButton.setBounds(BUTTON_CONSTANT_X,y, BUTTON_SIZE_X,HEIGHT);
		frame.getContentPane().add(myNewJButton);
		
		myNewJButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				String myText= myNewJButton.getText();
				if(!myText.equals("Liked")) {
					QueryHandler.likeAMovie(username, m.getTitle(), m.getReleaseDate());
					myNewJButton.setText("Liked");
				}else {
					QueryHandler.unlikeAMovie(username,m.getTitle() , m.getReleaseDate());
					myNewJButton.setText("Like");
				}
			}
		});
	}
	private void doWD() {
		int baseY = 43;
		moviesAvai = Math.min(3, moviesAvailable.size());
		
		for(int a = 0 ; a < wDResult.size() ; a++) {
			createRowForDW(baseY + a*(HEIGHT + 2), wDResult.get(a), moviesAvai);
		}
	}
	private void createRowForDW(int y , WriterDirectorObject m , int moviesAvai) {
		String buttonOrder = "";
		if(moviesAvai>0) {
			for (int k = moviesAvai-1 ; k >= 0  ; k--) {
				buttonOrder = buttonOrder.concat(moviesAvailable.get(k).title + "   ");
			}
			JLabel lblNewLabel = new JLabel("Button order: " + buttonOrder);
			lblNewLabel.setBounds(621, 6, 361, 29);
			frame.getContentPane().add(lblNewLabel);
		}
		JLabel myNewLabel = new JLabel(m.toString());
		myNewLabel.setBounds(LABEL_CONSTANT_X - ((BUTTON_SIZE_X +2) *moviesAvai)  , y , LABEL_SIZE_X , HEIGHT);
		frame.getContentPane().add(myNewLabel);
		
		if (moviesAvai > 0 ) {
			for (int b = 0 ; b < moviesAvai ; b++) {
				handleHireWD(moviesAvailable.get(b) , BUTTON_CONSTANT_X - (b * (BUTTON_SIZE_X +2)), y, m);
			}
		}
		
		
	}
	public void handleHireWD(Movie movie , int x, int y, WriterDirectorObject m) {
		JButton myNewButton = new JButton("Hire");
		myNewButton.setBounds(x, y, BUTTON_SIZE_X, HEIGHT);
		frame.getContentPane().add(myNewButton);
		
		myNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String myText= myNewButton.getText();
				if(!myText.equals("Hired")) {
					QueryHandler.hireForMovie(m.getUsr(), movie.getTitle(), movie.getReleaseDate());
					myNewButton.setText("Hired");
				}else {
					QueryHandler.fireFromMovie(m.getUsr(),movie.getTitle() , movie.getReleaseDate());
					myNewButton.setText("Hire");
				}
			}
		});
	}
	public void handleHireActor(Movie movie , int x, int y, ActorObject m) {
		JButton myNewButton = new JButton("Hire");
		myNewButton.setBounds(x, y, BUTTON_SIZE_X, HEIGHT);
		frame.getContentPane().add(myNewButton);
		
		myNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String myText= myNewButton.getText();
				if(!myText.equals("Hired")) {
					QueryHandler.hireForMovie(m.getUsr(), movie.getTitle(), movie.getReleaseDate());
					myNewButton.setText("Hired");
				}else {
					QueryHandler.fireFromMovie(m.getUsr(),movie.getTitle() , movie.getReleaseDate());
					myNewButton.setText("Hire");
				}
			}
		});
	}
	private void createRowForActor(int y , ActorObject m , int moviesAvai) {
		String buttonOrder = "";
		if(moviesAvai>0) {
			for (int k = moviesAvai-1 ; k >= 0  ; k--) {
				buttonOrder = buttonOrder.concat(moviesAvailable.get(k).title + "   ");
			}
			JLabel lblNewLabel = new JLabel("Button order: " + buttonOrder);
			lblNewLabel.setBounds(621, 6, 361, 29);
			frame.getContentPane().add(lblNewLabel);
		}
		JLabel myNewLabel = new JLabel(m.toString());
		myNewLabel.setBounds(LABEL_CONSTANT_X - ((BUTTON_SIZE_X +2) *moviesAvai)  , y , LABEL_SIZE_X , HEIGHT);
		frame.getContentPane().add(myNewLabel);
		
		if (moviesAvai > 0 ) {
			for (int b = 0 ; b < moviesAvai ; b++) {
				handleHireActor(moviesAvailable.get(b) , BUTTON_CONSTANT_X - (b * (BUTTON_SIZE_X +2)), y, m);
			}
		}
	}
	private void doActors() {
		int baseY = 43;
		moviesAvai = Math.min(3, moviesAvailable.size());
		
		for(int a = 0 ; a < wDResult.size() ; a++) {
			createRowForActor(baseY + a*(HEIGHT + 2), ActorResults.get(a), moviesAvai);
		}
	}
}
