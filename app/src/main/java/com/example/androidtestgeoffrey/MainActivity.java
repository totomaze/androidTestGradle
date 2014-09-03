package com.example.androidtestgeoffrey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtestgeoffrey.model.Movie;
import com.example.androidtestgeoffrey.utils.RottenTomatoes;

public class MainActivity extends Activity implements OnClickListener{
	private ImageView iv_search;
	private EditText ed_search;
	private ListView lv_movies;
	private TextView tv_no_results;
	private List<Movie> movies;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv_search=(ImageView)findViewById(R.id.iv_search);
		iv_search.setOnClickListener(this);
		ed_search=(EditText)findViewById(R.id.ed_search);
		lv_movies=(ListView)findViewById(R.id.lv_movies);
		tv_no_results=(TextView)findViewById(R.id.tv_no_results);

		MovieAdapter adapter = new MovieAdapter(this, movies);
		adapter.notifyDataSetChanged();
		lv_movies.setAdapter(adapter);
		
		//Initialize the search for the test
		ed_search.setText("Android");
		new RetrieveMovies(this).execute(ed_search.getText().toString());
	}

	@Override
	public void onClick(View v) {
		if (v==iv_search){
			if (ed_search.getText()!=null && ed_search.getText().toString()!=null && !ed_search.getText().toString().equals("")){
				new RetrieveMovies(this).execute(ed_search.getText().toString());
			}
		}
	}


	/**
	 * Call of the search with an asyncTask to avoid to block the main thread and to show the user that something is processing
	 * @author Geoffrey Thomazeau
	 *
	 */
	public class RetrieveMovies extends AsyncTask<String, Void, List<Movie>> {
		private ProgressDialog dialog;
		private Activity activity;
		private boolean errorDetected=false;;

		public RetrieveMovies(Activity activity) {
			this.activity=activity;
		}

		protected void onPreExecute() {
			/** progress dialog to show user that something is processing. */
			dialog = new ProgressDialog(activity);
			String chargement= activity.getApplicationContext().getApplicationContext().getResources().getString(R.string.loading);
			dialog.setMessage(chargement);
			dialog.setIndeterminate(true);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			super.onPreExecute();
		}

		protected void onPostExecute(List<Movie> listOfMovies) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}

			if (errorDetected){
				Toast.makeText(activity,activity.getApplicationContext().getApplicationContext().getResources().getString(R.string.error_internet) , Toast.LENGTH_LONG).show();
			}

			if (listOfMovies==null)listOfMovies=new ArrayList<Movie>();

			MovieAdapter adapter = new MovieAdapter(activity, listOfMovies);
			adapter.notifyDataSetChanged();
			lv_movies.setAdapter(adapter);

			if (listOfMovies.size()==0){
				tv_no_results.setVisibility(View.VISIBLE);
			}else{
				tv_no_results.setVisibility(View.GONE);
			}

		}


		@Override
		protected List<Movie> doInBackground(String... movieTab) {
			List<Movie> listOfMovies=null;
			String movieSearch=null;
			if (movieTab!=null){
				movieSearch=movieTab[0];
			}
			if (movieSearch!=null && !movieSearch.equals("")){
				try {
					listOfMovies=RottenTomatoes.findMovies(movieSearch);
				} catch (IOException e) {
					errorDetected=true;
					e.printStackTrace();
				}
			}
			return listOfMovies;
		}
	}


}
