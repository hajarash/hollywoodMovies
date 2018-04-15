package movieApp;

import java.util.Date;
import java.util.LinkedList;


public class Movie {
	public static final String[] GENRESLIST = {"horror", "fantasy", "action", "thriller", "drama", "comedy", "musical", "sci-fi"};
	public String producer=" ";
	public int budget=0;
	public boolean[] genre= {false,false,false,false,false,false,false,false};
	public String location= " ";
	public int listingNo = 0;
	public Date releaseDate;
	public Date prodStartDate = new Date();
	public Date prodEndDate = new Date();
	public String title = " ";
	
	public Movie(String title, Date releaseDate) {
		this.title = title;
		this.releaseDate = releaseDate;
	}
	// find writers with movies higher than following rating
	public void setAttributes(int bud, boolean[] gen, String loc, int listingNo, Date prodStartDate, Date prodEndDate, String prod) {
		this.budget = bud;
		this.genre = gen;
		this.location = loc;
		this.listingNo = listingNo;
		this.prodStartDate = prodStartDate;
		this.prodEndDate = prodEndDate;
		this.producer = prod;
	}
	public String getProducer() {
		return producer;
	}
	public int getBudget() {
		return budget;
	}
	public boolean[] getGenre() {
		return genre;
	}
	public LinkedList<String> getGenreString() {
		LinkedList<String> retString = new LinkedList<String>();
		for(int i = 0 ; i < genre.length ; i++) {
			if (genre[i]) {
				retString.add(GENRESLIST[i]);
			}
		}
		return retString;
	}
	public String getLocation() {
		return location;
	}
	public int getListingNo() {
		return listingNo;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public Date getProdStartDate() {
		return prodStartDate;
	}
	public Date getProdEndDate() {
		return prodEndDate;
	}
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		
		String retString = this.title + "   " + this.prodStartDate.toString() + "   " + this.prodEndDate.toString()+ "   " + this.releaseDate.toString();
		LinkedList<String> myList= this.getGenreString();
		for (int i = 0 ; i < myList.size() ; i++) {
			retString.concat("   " + myList.get(i));
		}
		return retString;
	}
}
