package com.example.final_mobile.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Television implements Parcelable {
    private String title;
    private String releaseDate;
    private String posterImageUrl;
    private double voteAverage;
    private String overview;
    private String backdropImageUrl;

    public Television(String title, String releaseDate, String posterImageUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterImageUrl = posterImageUrl;
    }

    protected Television(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        posterImageUrl = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
        backdropImageUrl = in.readString();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
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

    public String getBackdropImageUrl() {
        return backdropImageUrl;
    }

    public void setBackdropImageUrl(String backdropImageUrl) {
        this.backdropImageUrl = backdropImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(posterImageUrl);
        dest.writeDouble(voteAverage);
        dest.writeString(overview);
        dest.writeString(backdropImageUrl);
    }
}
