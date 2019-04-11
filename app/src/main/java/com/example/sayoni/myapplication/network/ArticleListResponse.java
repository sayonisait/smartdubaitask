package com.example.sayoni.myapplication.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArticleListResponse {

    public String status;
    public String copyright;
    public int num_results;
    public ArrayList<Result> results;


    public class MediaMetadata
    {
        public String url;
        public String format;
        public int height;
        public int width;
    }

    public class Medium
    {
        public String type;
        public String subtype;
        public String caption;
        public String copyright;
        public int approved_for_syndication;
        @SerializedName("media-metadata")
        public List<MediaMetadata> list;
    }

    public class Result
    {
        public String url;
        public String adx_keywords;
        public String column;
        public String section;
        public String byline;
        public String type;
        public String title;
        public String published_date;
        public String source;
        public Object id;
        public Object asset_id;
        public int views;
        public Object des_facet;
        public Object org_facet;
        public Object per_facet;
        public Object geo_facet;
        public List<Medium> media;

    }




}
