package com.example.androidtestgeoffrey.model;
/**
 * Class containing some of the attributes for the Json object Movie coming from the API RottenTomatoes
 * Only the following attributes will be used for the test : title, year and runtime
 * To avoid problems during the Json parsing I am using the String type for all the simple attributes 
 * @author Geoffrey Thomazeau
 *
 */
public class Movie {
	private String id; 		// Yes the id is a String type. This type come from the API RottenTomatoes 
	private String title;
	private String year;
	private String runtime;// if the runtime is missing we are getting an empty string that's why I am using a String Type
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
}
