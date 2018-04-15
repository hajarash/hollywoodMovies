package movieApp;

public class ActorObject {
	int height;
	boolean[] languages;
	boolean[] genres;
	boolean gender;
	String username;
	String name;
	
	public ActorObject(String username, String name, int h, boolean[] l, boolean[] g, boolean gen ) {
		this.username = username;
		this.name = name;
		this.height = h;
		this.languages = l;
		this.genres = g;
		this.gender = gen;
	}
	public String getUsr() {
		return this.username;
	}
	public String getName() {
		return this.name;
	}
	public int getHeight() {
		return this.height;
	}
	public boolean[] getLanguages() {
		return this.languages;
	}
	public boolean[] getGenres() {
		return this.genres;
	}
	public boolean getGender() {
		return this.gender;
	}
	public String toString() {
		return username.concat(" ").concat("height: ").concat(String.valueOf(this.getHeight()));
	}
}
