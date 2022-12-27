package com.example.android.atoznews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    @Override
    protected void onStartLoading(){
        Log.i("LOG_TAG" , "Test : onStartLoading called....");
        forceLoad();
    }

    public NewsLoader(Context context , String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<News> loadInBackground() {
        Log.i("LOG_TAG" , "Test : loadInBackground called....");
        if (mUrl == null){
            return null;
        }
        List<News> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
