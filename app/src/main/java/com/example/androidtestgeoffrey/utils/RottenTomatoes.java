package com.example.androidtestgeoffrey.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.example.androidtestgeoffrey.model.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Class containing the method to do the search with the API RottenTomatoes
 * @author Geoffrey Thomazeau
 *
 */
public class RottenTomatoes {
	private static String API_KEY="ss748fpwskh2tdmwaq9ysf48";
	private static String URL_API="http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="+API_KEY;
	private static Integer PAGE_LIMIT=30;	//this is the default value from rottenTomatoes
	private static Integer PAGE=1;			//this is the default value from rottenTomatoes
	private static Integer TIMEOUT=10000;

	/**
	 * Method to find a list of movies containing the word given in input  
	 * @param search String used for the search
	 * @return List of Movies matching the search word
	 * @throws IOException
	 */
	public static List<Movie> findMovies(String search) throws IOException{
		List<Movie> listOfMovies=null;
		BufferedReader stream  = null;
		InputStream is=getStreamUrl(URL_API+"&q="+search+"&page_limit="+PAGE_LIMIT+"&page="+PAGE);
		if (is!=null){
			stream = new BufferedReader(new InputStreamReader(is,"UTF8"));
			if (stream!=null){
				String text = stream.readLine();
				while (text!=null ){
					if (!text.equals("")){
						Gson gson =new GsonBuilder().create();
						Type listType = new TypeToken<List<Movie>>() {}.getType();
						JsonParser jsonParser = new JsonParser();
						JsonObject jo = (JsonObject)jsonParser.parse(text);
						JsonArray jsonArr = jo.getAsJsonArray("movies");
						listOfMovies=gson.fromJson(jsonArr, listType);
						break;
					}
					text = stream.readLine();
				}
			}
		}
		return listOfMovies;
	}

	/**
	 * 
	 * @param url 
	 * @return InputStream corresponding to the url
	 * @throws IOException
	 */
	public static InputStream getStreamUrl(String url) throws IOException{
		InputStream is=null;
		
		URL u = new URL(url);
		URLConnection urlConnection = u.openConnection();
		urlConnection.setReadTimeout(TIMEOUT);
		is= new BufferedInputStream(urlConnection.getInputStream());

		return is;
	}
}
