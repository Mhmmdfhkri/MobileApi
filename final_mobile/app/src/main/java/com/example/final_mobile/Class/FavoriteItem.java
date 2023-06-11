package com.example.final_mobile.Class;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FavoriteItem implements Parcelable {
    private int id;
    private String title;
    private String posterPath;
    private String releaseDate;
    private double voteAverage;
    private String overview;
    private String backdropPath;

    public FavoriteItem(int id, String title, String posterPath, String releaseDate, double voteAverage, String overview, String backdropPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.backdropPath = backdropPath;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeDouble(voteAverage);
        parcel.writeString(overview);
        parcel.writeString(backdropPath);
    }

    // Buat constructor, getter, dan setter sesuai kebutuhan
}

