package com.example.android.atoznews;

public class News {
    private String mImageUrl;
    private String mTitle;
    private String mDate;
    private String mUrl;

    public News(String imageUrl , String title , String date , String url){
        mImageUrl = imageUrl;
        mTitle = title;
        mDate = date;
        mUrl = url;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public String getmTitle(){
        return mTitle;
    }

    public String getmDate(){
        return mDate;
    }

    public String getmUrl(){
        return mUrl;
    }

}
