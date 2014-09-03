package com.example.androidtestgeoffrey;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtestgeoffrey.model.Movie;

/**
 * Class that manage to present the data for a Movie
 * @author Geoffrey Thomazeau 
 *
 */
public class MovieAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Movie> movies;
	Context context;

	public MovieAdapter(Context context,List<Movie> movies){
		inflater = LayoutInflater.from(context);
		this.movies=movies;
		this.context=context;
	}

	public int getCount() {
		if (movies!=null){
			return movies.size(); 
		}else {return 0;}
	}

	public Object getItem(int position) {
		return movies.get(position);
	}

	public long getItemId(int position) {
		return position ;
	}

	private class ViewHolder{
		TextView tv_title;
		TextView tv_year;
		TextView tv_runtime;
		TextView tv_year_label;
		TextView tv_runtime_label;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.movie_layout, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
		holder.tv_year = (TextView)convertView.findViewById(R.id.tv_year);
		holder.tv_runtime = (TextView)convertView.findViewById(R.id.tv_runtime);
		holder.tv_year_label = (TextView)convertView.findViewById(R.id.tv_year_label);
		holder.tv_runtime_label = (TextView)convertView.findViewById(R.id.tv_runtime_label);

		Movie movie=movies.get(position);
		holder.tv_title.setText(movie.getTitle());
		if (movie.getYear()!=null && !movie.equals("")){
			holder.tv_year.setText(movie.getYear());
			holder.tv_year_label.setVisibility(View.VISIBLE);
			holder.tv_year.setVisibility(View.VISIBLE);
		}else{
			holder.tv_year_label.setVisibility(View.INVISIBLE);
			holder.tv_year.setVisibility(View.INVISIBLE);
		}
		
		if (movie.getRuntime()!=null && !movie.getRuntime().equals("")){
			holder.tv_runtime.setText(movie.getRuntime());
			holder.tv_runtime_label.setVisibility(View.VISIBLE);
			holder.tv_runtime.setVisibility(View.VISIBLE);
		}else{
			holder.tv_runtime_label.setVisibility(View.INVISIBLE);
			holder.tv_runtime.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

}
