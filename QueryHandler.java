package movieApp;

import java.security.AllPermission;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import javax.sql.ConnectionEvent;

public class QueryHandler {
	private final String[] TABLE_ORDER = {"producers", "writers", "actors", "directors"};
	private static Connection con;
	public static void getDriverRunning() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(
					  "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_b9g0b", "a39738142");
			/*Statement stmt = con.createStatement();
			ResultSet sqlRes = stmt.executeQuery("SELECT  personid from persons where personid = 1");
			while(sqlRes.next()) {
				System.out.println(sqlRes.getString("personid"));
			}*/
			// practice
			
		}catch(Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		
	}
	public static String fromText(String username) {
		// SQL to get the beginning of availability in a text format mm-dd-yyyy
		String date="";
		try {
		Statement stmt = con.createStatement();
		ResultSet sqlRes = stmt.executeQuery("select fromdate from Employee where username='"+username+"'");
		if (sqlRes.next()) {
			date = sqlRes.getString("FROMDATE").substring(0, 9);
			stmt.close();
		}
		else {
			sqlRes = stmt.executeQuery("select fromdate from Producer where username = '"+username+"'");
			sqlRes.next();
			date = sqlRes.getString("FROMDATE").substring(0, 9);
			stmt.close();
		}
		}catch (Exception e)
		{
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		return date;
	}
	public static String toText(String username) {
		String date="";
		try {
		Statement stmt = con.createStatement();
		ResultSet sqlRes = stmt.executeQuery("select todate from Employee where username='"+username+"'");
		if (sqlRes.next()) {
			date = sqlRes.getString("TODATE").substring(0, 9);
			stmt.close();
		}
		else {
			sqlRes = stmt.executeQuery("select todate from Producer where username = '"+username+"'");
			sqlRes.next();
			date = sqlRes.getString("TODATE").substring(0, 9);
			stmt.close();
		}
		}catch (Exception e)
		{
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		return date;
	}
	/**
	 * @param username
	 * @return
	 */
	public static String budgetText (String username) {
		// SQL to get the budget for producers
		String producer="";
		try {
			Statement stmt = con.createStatement();
			ResultSet sqlRes  = stmt.executeQuery("select overall_budget from producer where username = '"+username+"'");
			sqlRes.next();
			producer = sqlRes.getString("OVERALL_BUDGET");
			stmt.close();
			
		}catch (Exception e)
		{
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
			return producer;
	}
	public static void updateInformationForProducer(String username, String from, String to, String budget) {
		// SQL to update availability and budget for username
	}
	public static void updateInformationForWD(String username, String from, String to) {
		// SQL to update availability for username
	}
	public static boolean[] getLangs(String username) {
		boolean[] res = {false,false,false};
		//ASSUMPTION: ONLY ACTORS HAVE A COLUMN NAME AS LANGUAGE INSIDE THE DATABASE
		// SQL to get the languages, return true for whatever is spoken by the actor order is: English, Spanish, French
		try {
			Statement stmt = con.createStatement();
			ResultSet sqlres = stmt.executeQuery("select AC.languageenglish, AC.languagespanish, AC.languagefrench"
					+ " from actor_stars AC where AC.username_e = '"+username+"'");
			sqlres.next();
			for (int i=1; i<=res.length; i++)
			{
				if (sqlres.getString(i).equals("True")) {
					res[i]=true;
				}
				System.out.println (res[i]);
			}
			
		}catch (Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		return res;
	}
	public static String getHeight(String username) {
		String query = "Select* from actor_stars where username_e = '"+username+"'";
		String h = null;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				h = resultSet.getString("height");
			}
		}catch (Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		return h;
	}
	public static boolean isMale(String username) {
		boolean ret = false;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("Select gender from actor_stars where username_e =  '"+username+"'");
			String answer="";
			while(resultSet.next()) {
				answer = resultSet.getString(1).trim();
				System.out.println(answer);
				if(answer.equals("Male")) {
					ret = true;
				}
			}
		} catch (Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		return ret;
	}
	public static void updateInformationForActor(String username, String from, String to, boolean eng, boolean span, boolean fren,
			String height, boolean isMale) {
		// SQL to update the information of actor
		//UPDATE: languages spoken, gender, dates available
		//IMPLEMENTATION NOT WORKNIG
			String gend = String.valueOf(isMale(username));
				
			String query = 
			"Update actor_stars Set from = '"+from+"', to = '"+to+"', "+ "languageEnglish = '"+eng+"',"
					+ "languageSpanish = '"+span+"',languageFrench= '"+fren+"',  height = '"+height+"', gender = '"+gend+"'";

				try {
					Statement statement = con.createStatement();
					String gender="";
					String English="",Spanish="", French="";
					//Change from and to date for employee with 'username'
					statement.executeUpdate("Update employee set fromdate ='"+from+"', todate='"
							+ ""+to+"' where username='"+username+"'");
					//Change languages spoken, gender, height
					if (isMale == true) {
						gender="True";
					}else {
						gender = "False";
					}
					if (eng == true) {
						English ="True";
					}else {
						English = "False";
					}
					if (span == true) {
						Spanish = "True";
					}else {
						Spanish = "False";
					}
					if (fren == true) {
						French = "True";
					}else {
						French ="False";
					}
					statement.executeUpdate("update actor_stars set gender='"+gender+"', languageenglish ='"+English+"'"
							+ ", languagespanish = '"+Spanish+"', languagefrench ='"+French+"', height ='"+height+"'");
					statement.close();
				} catch(Exception e) {
					String message[]= {"IDK you screwed up"};
					ErrorMsg.main(message);
				}
		
	}
	public static boolean registerAuthentication(String username) {
		// SQL to see if username exists
		String query = "Select* from users where username = '"+username+"' ";
		try {
			Statement statement = con.createStatement();
			boolean result = statement.execute(query);
			if (result) {
				return true;
			}
		} catch(Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		} 
		return false;
	}
	/*NOT TESTED FUNCTION TO*/
	public static int loginAuthentication(String username, String password) {
		//IMPLEMENTED BY MOHSIN
		// SQL to see if username & pw match and then return the type
		// if usename & pw don't match return 5

		int retVal = 5;
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("select* from users where username = '" + username+"' and password = '" + password+"' ");
			
			while(res.next()) {
				if (res.getString("u").equals("0")) {
					retVal = 0;
				}
				else if (res.getString("u").equals("1")) {
					retVal = 1;
				}
				else if (res.getString("u").equals("2")) {
					retVal = 2;
				}
				else if (res.getString("u").equals("3")) {
					retVal = 3;
				}else {
					retVal = 5;
				}	
			}
		} catch (Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
 		}
	
		return retVal;
	}
	public static void registerUser(String fullName, String username, String password, int type) {
		// add tuple to users and the table with name TABLE_ORDER[type]
		try {
			PreparedStatement statement = con.prepareStatement("Insert into users values ('"+username+"','"+password+"','"+fullName+"','"+type+"')");
			statement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	public static LinkedList<ActorObject> searchForActors(int height, boolean lessThan, boolean[] languages, boolean[] genres, boolean[] gender) {
		LinkedList<ActorObject> myList = new LinkedList<ActorObject>();
		//IMPLEMENTED BY MOHSIN
		// run SQL and add to myList
		try {
			String [] genresArray= {"False","False","False","False","False","False","False","False"};
			String [] languagestring= {"False","False","False"};
			String genderstring = "False";
			if (gender [0]==true) {
				genderstring = "True";
			}
			for (int i=0; i<=3; i++)
			{
				if (languages[i]==true) {
					languagestring[i]="True";
				}
			}
			//Horror, Fantasy, Action, Thriller, Drama, Comedy, Musical, Sci-fi
			for (int i=0; i<= genres.length; i++) {
				if (genres[i]==true) {
					genresArray[i]="True";
				}
			}
			//To create I need String username, String name, int h, boolean[] l, boolean[] g, boolean gen 
			//I have a height, lessthan, language size 3, genres size 3, gen
			ActorObject acting;
			if (gender.length == 2) {
				Statement stmt = con.createStatement();
				ResultSet result = stmt.executeQuery("select A.username_e, U.name from actor_stars A, username U where"
						+ " A.height <= '"+height+"' and A.username_e = U.username and languageenglish= '"+languagestring[0]+"'"
								+ " and languagespanish = '"+languagestring[1]+"' and languagefrench ='"+languagestring[2]+"'"
										+ " and gender='True'");
				while (result.next())
				{
					acting = new ActorObject(result.getString(1),result.getString(2),height, languages,genres, true);
					myList.add(acting);
				}
				result = stmt.executeQuery("select A.username_e, U.name from actor_stars A, username U where"
						+ " A.height <= '"+height+"' and A.username_e = U.username and languageenglish= '"+languagestring[0]+"'"
								+ " and languagespanish = '"+languagestring[1]+"' and languagefrench ='"+languagestring[2]+"'"
										+ " and gender='True'");
				while (result.next())
				{
					acting = new ActorObject(result.getString(1),result.getString(2),height, languages,genres, false);
					myList.add(acting);
				}
			}else {
				Statement stmt = con.createStatement();
				ResultSet result = stmt.executeQuery("select A.username_e, U.name from actor_stars A, username U where"
						+ " A.height <= '"+height+"' and A.username_e = U.username and languageenglish= '"+languagestring[0]+"'"
								+ " and languagespanish = '"+languagestring[1]+"' and languagefrench ='"+languagestring[2]+"'"
										+ " and gender= '"+genderstring+"'");
				while (result.next())
				{
					acting = new ActorObject(result.getString(1),result.getString(2),height, languages,genres, gender[0]);
					myList.add(acting);
				}
			}
			
		}catch(Exception e){
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		
		return myList;
	}
	
	public static LinkedList<WriterDirectorObject> searchForEmployees(String tableName, boolean[] genres){
		LinkedList<WriterDirectorObject> myList = new LinkedList<WriterDirectorObject>();
		try {
			WriterDirectorObject addtolist;
			String [] genresArray= {"False","False","False","False","False","False","False","False"};
			for (int i=0; i<= genres.length; i++) {
				if (genres[i]==true) {
					genresArray[i]="True";
				}
			}
			//I need username, name, and genres to create a writerdirectorobject
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select T.username_e, U.name from users U,"+tableName + " T where"
					+ " T.Horrorgenre ='"+genres[0]+"' and T.fantasygenre ='"+genres[1]+"' and T.actiongenre ='"+genres[2]+"'"
							+ " and T.thrillergenre ='"+genres[3]+"' and T.DRAMAGENRE ='"+genres[4]+"' and"
									+ " T.COMEDYGENRE ='"+genres[5]+"' and T.MUSICALGENRE ='"+genres[6]+"' and "
											+ " T.SCIFIGENRE ='"+genres[7]+"'");
			while (result.next()) {
				addtolist = new WriterDirectorObject (result.getString(1), result.getString(2), genres);
				myList.add(addtolist);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return myList;
	}
	public static String genreArray (boolean [] genre)
	{
		String genreString[] = {"Horror","Fantasy","Action","Thriller","Drama","Comedy","Musical","Sci-fi"}; 
		int i=0;
		String genreSQL="";
		genreSQL = genreSQL.concat("(");
		while (genre.length > i)
		{
			if (genre[i])
			{
				genreSQL = genreSQL.concat("'"+genreString[i]+"',");
			}
			i++;
		}
		genreSQL = genreSQL.substring(0, genreSQL.length()-1);
		genreSQL = genreSQL.concat(")");	
		return genreSQL;
	}
	public static LinkedList <Movie> searchForMovies(int budget, String producer, boolean[] genre){
		//assuming producer is a username
		//TODO: CHANGE IMPLEMENTATION TO ACCOMODATE GENRE COLUMNS
		LinkedList<Movie> myList = new LinkedList<Movie>();
		String genreSQL = genreArray(genre);
		try{
		Statement stmt = con.createStatement();
		ResultSet sqlRes;
		if(!producer.equals("")) {
			sqlRes = stmt.executeQuery("SELECT MP.Title from movie_produces MP , producer P where"
				+ " P.overall_budget >="+ budget +" and MP.username_p='"+ producer +"' and MP.genre IN " + genreSQL);
		}else {
			sqlRes = stmt.executeQuery("SELECT MP.Title from movie_produces MP , producer P where"
					+ " P.overall_budget >="+ budget +" and MP.genre IN " + genreSQL);
		}
		
		while(sqlRes.next()) {
			myList.add(new Movie ((sqlRes.getString(1)), new Date()));
		}
		}catch(Exception e) {
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
		}
		return myList;
	}
	public static void likeAMovie(String username, String title, Date releaseDate) {
		// add a like to the movieLikers table
		// ('"+username+"','"+password+"','"+fullName+"','"+type+"')"
		String pass = null;
		String query1 = "Insert Into favorite_movies values ('"+username+"','"+pass+"', '"+title+"', '"+releaseDate+"')";
		String query2 = "Select* from users where username = '"+username+"' ";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query2);
			while(resultSet.next()) {
				pass = resultSet.getString("password");
			}
			Statement statement2 = con.createStatement();
			statement2.executeUpdate(query1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static LinkedList<Movie> producerMovies(String username){
		LinkedList<Movie> myList = new LinkedList<Movie>();
		// find all movies produced by username
		String query = "Select* from movie_produces where username_p = '"+username+"'";
		String queryBudget = "Select* from Producer where username = '"+username+"' ";
		String name = "";
		int budget = 0;
		try {
		Statement statement2 = con.createStatement();
		ResultSet res = statement2.executeQuery(queryBudget);
		System.out.println("here");
		while (res.next()) {
			budget = res.getInt("overall_budget");
			name = res.getString("Name");
			
		}
		System.out.println("here");
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				System.out.println(resultSet.getMetaData().getColumnCount());
				System.out.println("hhhh");
				String title = resultSet.getString("Title");
				System.out.println(title);
				System.out.println("hlk");
				Date rd = resultSet.getDate("Release_date");
				String loc = resultSet.getString("filming_location");
				Movie movie = new Movie(title, rd);
				Date prodStartDate = resultSet.getDate("production_startdate");
				System.out.println(prodStartDate);
				Date prodEndDate = resultSet.getDate("production_enddate");
				System.out.println(prodEndDate);
				String g1 = resultSet.getString(3);
				System.out.println(g1);
				String g2 = resultSet.getString(4);
				System.out.println(g2);
				String g3 = resultSet.getString(5);
				System.out.println(g3);
				String g4 = resultSet.getString(6);
				System.out.println(g4);
				String g5 = resultSet.getString(7);
				String g6 = resultSet.getString(8);
				String g7 = resultSet.getString(9);
				String g8 = resultSet.getString(10);
				String[] gArray = {g1,g2,g3,g4,g5,g6,g7,g8};
				boolean[] gen = getGenres(gArray);
				movie.setAttributes(budget, gen, loc, 0, prodStartDate, prodEndDate, name);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return myList;
	}
	public static boolean[] getGenres(String[] arr) {
		boolean[] myArr = {false,false,false,false,false,false,false,false};
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == "True") {
				myArr[i] = true;
			} else {
				myArr[i] = false;
			}
		}
		return myArr;
	}
	public static void unlikeAMovie(String username, String movieTitle , Date releaseDate) {
		String query = "Delete from favorite_movies where username_e = '"+username+"' and Title = '"+movieTitle+"' ";
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	public static void hireForMovie( String username, String movieTitle, Date releaseDate) {
		// SQL to hire for movie
	}
	public static void fireFromMovie( String username, String movieTitle, Date releaseDate) {
		// SQL to fire from movie
	}
	public static LinkedList<Movie> employeeMovies(String username) {
		LinkedList<Movie> myList = new LinkedList<Movie>();
		// SQL to get the movies per username
		return myList;
	}
	public static void deleteAMovie(String title, Date releaseDate) {
 		try {
 		Statement stmt = con.createStatement();
 		ResultSet sqlRes = stmt.executeQuery("DELETE FROM Movie_produces WHERE Title='"+title+"' AND Release_date='"
 				+ ""+releaseDate+"'");

 		if (sqlRes.next()) {
 			stmt.close();
 		}
 		else {
 			sqlRes.next();
 			stmt.close();
 		}
 		}catch (Exception e)
 		{
			String message[]= {"IDK you screwed up"};
			ErrorMsg.main(message);
 		}
	}
	public static void addAMovie(Movie movie) {
		String insertSQL = "INSERT INTO Movie_produces VALUES ('"+ movie.getTitle() + "', '" + getDate(movie.getReleaseDate()) +
				"', ";
		for (int i = 0 ; i< movie.getGenre().length; i++){
			String input = String.valueOf(movie.getGenre()[i]);
			String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			insertSQL = insertSQL.concat("'").concat(output).concat("', ");
		}
		Random rn = new Random();
		int answer = rn.nextInt(100) + 1;
		
		
		insertSQL= insertSQL.concat(String.valueOf(answer));
		insertSQL= insertSQL.concat(", '").concat(getDate(movie.getProdStartDate())).concat("', '").concat(getDate(movie.getProdEndDate()));
		insertSQL= insertSQL.concat("', '").concat(movie.getLocation()).concat("', '");
		insertSQL= insertSQL.concat(movie.getProducer()).concat("', ' ', ' ', ' ',' ', ' ');");
		System.out.println(insertSQL);
		try {
	 		Statement stmt = con.createStatement();
	 		stmt.executeUpdate(insertSQL);

	 		//insert into Movie_produces
	 		//values('Planet of Faloodah', '2007-05-02', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', 1, '2004-01-02','2006-05-02', 'Listing 3', 'Miami', 'Quacker42', 'QQrrr452','Becky','Becky123','Teresa', 'Teresa123');
//INSERT INTO Movie_produces VALUES ('hello', 'Tue May 23 00:00:00 PDT 3905', ' False', ' False', ' False', ' False', ' False', ' False', ' False', ' False', 10, 'Thu Mar 29 21:30:11 PDT 2018', 'Thu Mar 29 21:30:11 PDT 2018', '0', '', ' ', ' ', ' ',' ');
	 		
	 		}catch (Exception e)
	 		{
				String message[]= {e.getMessage()};
				ErrorMsg.main(message);
	 		}
		// add a movie with all the attributes
		// movie passed has to have a producer
	}
	public static String getDate(Date date){
		String year = String.valueOf(date.getYear());
		while(year.length()< 4) {
			year= "0".concat(year);
		}
		String month = String.valueOf(date.getMonth());
		while(month.length()< 2) {
			month= "0".concat(month);
		}
		String day = String.valueOf(date.getDay());
		while(day.length()< 2) {
			day= "0".concat(day);
		}
		return year.concat("-").concat(month).concat("-").concat(day);
	}

	public static String maxRatingAVG() {
		String myString = "";
		try {
			//SELECT MAX(AVG(rating))
//			FROM movie_produces
//			GROUP BY genre;
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select MAX(AVG(rating)) from movie_produces group by horrorgenre");
			result.next();
			myString = result.getString(1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return myString;
	}
	public static String minRatingAVG() {
		String myString = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select min(avg(rating)) from movie_produces group by horrorgenre");
			result.next();
			myString = result.getString(1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return myString;
	}
	public static LinkedList<WriterDirectorObject> witersWithHigherRatingThan(int rating){
		LinkedList<WriterDirectorObject> myWriterDirectorObjects = new LinkedList<WriterDirectorObject>();
		return myWriterDirectorObjects;
	}
	public static boolean[] languageArrayMaker(boolean e, boolean s, boolean f) {
		boolean[] languageArray = {e,s,f};
		return languageArray;
	}
	public static boolean[] genreArrayMaker(boolean h, boolean f, boolean a, boolean t, boolean d, boolean c, boolean m, boolean s) {
		boolean[] genreArray = {h,f,a,t,d,c,m,s};
		return genreArray;
	}
	public static boolean[] genderArrayMaker(boolean m, boolean f) {
		if(!m && !f) {
			boolean[] res = {true,true};
			return res;
		}else {
			boolean[] res = {m,f};
			return res;
		}
	}
	
}
