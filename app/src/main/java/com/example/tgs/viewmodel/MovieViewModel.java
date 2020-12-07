package com.example.tgs.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tgs.Constant;
import com.example.tgs.model.movie.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private static final String FULLURL = Constant.MOVIES_URL
            + Constant.DISCOVER
            + "?api_key="
            + Constant.API_KEY +
            "&language=en-US";


    public LiveData<ArrayList<Movie>> getMovies() {
        return MoviesList;
    }

    public void setMovies() {
        final ArrayList<Movie> listItems = new ArrayList<>();


        AsyncHttpClient client = new AsyncHttpClient();
        String url = FULLURL;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] HEAD, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Movie movieItem = new Movie(movie);
                        listItems.add(movieItem);
                    }

                    MoviesList.postValue(listItems);

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    private MutableLiveData<ArrayList<Movie>> MoviesList = new MutableLiveData<>();

}
