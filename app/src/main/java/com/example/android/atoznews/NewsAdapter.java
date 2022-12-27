package com.example.android.atoznews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.metrics.Event;
import android.os.AsyncTask;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Context context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        // Find the news at the given position in the list of news.
        News currentNews = getItem(position);

        // setting the image to the imageView.
        ImageView newsImageView = (ImageView) listItemView.findViewById(R.id.news_image);
        String imgUrl = currentNews.getmImageUrl();
        LoadImage loadImage = new LoadImage(newsImageView);

        loadImage.execute(imgUrl);

        // Setting news title in the textview.
        TextView newstitle = listItemView.findViewById(R.id.news_title);
        String title = currentNews.getmTitle();
        newstitle.setText(title);

        String [] date = new String[2];
        // Create a new Date object from the time in milliseconds of the earthquake
        String dateObject = currentNews.getmDate();
        date = dateObject.split(" ");
        // Setting the date and time.
        TextView newsDate = listItemView.findViewById(R.id.news_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = date[0];
        // Display the date of the current earthquake in that TextView
        newsDate.setText(formattedDate);

        // Find the TextView with view ID time
        TextView newsTime = (TextView) listItemView.findViewById(R.id.news_time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = date[1];
        // Display the time of the current earthquake in that TextView
        newsTime.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;

    }

    private class LoadImage extends AsyncTask<String , Void , Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView newsImageView){
            this.imageView = newsImageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imgUrl = strings[0];
            Bitmap bitmap = null;
            if (imgUrl == null){
                imgUrl = "https://im.ge/i/F5hdSS";
                InputStream inputStream = null;
                try {
                    inputStream = new URL(imgUrl).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            try {
                InputStream inputStream = new URL(imgUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
