package com.laojizhang.liveframe.network;

import java.io.Serializable;

/**
 * 文件名称： MovieResponse
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:55
 * 文件描述：
 * <p>
 */

public class MovieResponse implements Serializable {

    private int movieid;
    private double rating;
    private String genres;
    private String runtime;
    private String language;
    private String title;
    private String poster;
    private String writers;
    private String film_locations;
    private String directors;
    private int rating_count;
    private String actors;
    private String plot_simple;
    private int year;
    private String country;
    private String type;
    private String release_date;
    private String also_known_as;

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getFilm_locations() {
        return film_locations;
    }

    public void setFilm_locations(String film_locations) {
        this.film_locations = film_locations;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot_simple() {
        return plot_simple;
    }

    public void setPlot_simple(String plot_simple) {
        this.plot_simple = plot_simple;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(String also_known_as) {
        this.also_known_as = also_known_as;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movieid=" + movieid +
                ", rating=" + rating +
                ", genres='" + genres + '\'' +
                ", runtime='" + runtime + '\'' +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", writers='" + writers + '\'' +
                ", film_locations='" + film_locations + '\'' +
                ", directors='" + directors + '\'' +
                ", rating_count=" + rating_count +
                ", actors='" + actors + '\'' +
                ", plot_simple='" + plot_simple + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", release_date='" + release_date + '\'' +
                ", also_known_as='" + also_known_as + '\'' +
                '}';
    }
}
