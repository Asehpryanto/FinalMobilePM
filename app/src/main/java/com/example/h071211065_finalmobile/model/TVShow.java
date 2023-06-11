package com.example.h071211065_finalmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TVShow implements Parcelable {
    @SerializedName("id") private int id;
    @SerializedName("popularity") private double popularity;
    @SerializedName("genre_ids") private int[] genreIds;
    @SerializedName("vote_average") private double voteAverage;
    @SerializedName("vote_count") private int voteCount;
    @SerializedName("name") private String name;
    @SerializedName("overview") private String overview;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("first_air_date") private String firstAirDate;
    @SerializedName("backdrop_path") private String backdropPath;
    @SerializedName("origin_country") private String[] originCountry;

    public TVShow(int id, double popularity, int[] genreIds, double voteAverage, int voteCount, String name, String overview, String posterPath, String firstAirDate, String backdropPath, String[] originCountry) {
        this.id = id;
        this.popularity = popularity;
        this.genreIds = genreIds;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.name = name;
        this.overview = overview;
        this.posterPath = posterPath;
        this.firstAirDate = firstAirDate;
        this.backdropPath = backdropPath;
        this.originCountry = originCountry;
    }

    // Parcelable implementation
    protected TVShow(Parcel in) {
        id = in.readInt();
        popularity = in.readDouble();
        genreIds = in.createIntArray();
        voteAverage = in.readDouble();
        voteCount = in.readInt();
        name = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        firstAirDate = in.readString();
        backdropPath = in.readString();
        originCountry = in.createStringArray();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(popularity);
        dest.writeIntArray(genreIds);
        dest.writeDouble(voteAverage);
        dest.writeInt(voteCount);
        dest.writeString(name);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(firstAirDate);
        dest.writeString(backdropPath);
        dest.writeStringArray(originCountry);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    //    Getter
    public int getId() {
        return id;
    }
    public double getPopularity() {
        return popularity;
    }
    public int[] getGenreIds() {
        return genreIds;
    }
    public double getVoteAverage() {
        return voteAverage;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public String getName() {
        return name;
    }
    public String getOverview() {
        return overview;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public String getFirstAirDate() {
        return firstAirDate;
    }
    public String getBackdropPath() {
        return backdropPath;
    }
    public String[] getOriginCountry() {
        return originCountry;
    }

//    Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }
    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public void setOriginCountry(String[] originCountry) {
        this.originCountry = originCountry;
    }
}
