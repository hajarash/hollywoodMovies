package movieApp;

public class WriterDirectorObject {
	boolean[] genres;
	String username;
	String name;
	public WriterDirectorObject(String usr, String name, boolean[] genres) {
		this.name = name;
		this.username = usr;
		this.genres = genres;
	}
	public boolean[] getGenres() {
		return this.genres;
	}
	public String getUsr() {
		return this.username;
	}
	public String getName() {
		return this.name;
	}
	public String toString() {
			return username.concat(" ").concat("full name: ").concat(this.getName());
		
	}
}
