package com.example.final_mobile.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Television implements Parcelable{
    private String title;
    private String releaseDate;
    private String posterPath;
    private String backdropPath;
    private double voteAverage;
    private String overview;

    public Television(String title, String releaseDate, String posterPath, String overview, String backdropPath, double voteAverage) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.overview = overview;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
    }


    protected Television(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
    }

    public static final Creator<Television> CREATOR = new Creator<Television>() {
        @Override
        public Television createFromParcel(Parcel in) {
            return new Television(in);
        }

        @Override
        public Television[] newArray(int size) {
            return new Television[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeDouble(voteAverage);
        dest.writeString(overview);
    }
}
