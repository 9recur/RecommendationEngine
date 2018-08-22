/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings f = new FirstRatings();
        myMovies = f.loadMovies(movieFile);
        myRaters = f.loadRaters(ratingsFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double sum = 0;
        int ratingsCount = 0;
        for (Rater r : myRaters) {
            if (r.hasRating(id)) {
                sum += r.getRating(id);
                ratingsCount++;
            }
        }
        if (ratingsCount >= minimalRaters) {
            return Math.round(sum / ratingsCount * 10000.0) / 10000.0;
        }
        else {
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averages = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            double averageRating = getAverageByID(m.getID(), minimalRaters);
            if (averageRating > 0.0) {
                averages.add(new Rating(m.getID(), averageRating));
            }
        }
        return averages;
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "ERROR: Movie Title Not Found.";
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "ERROR: Movie ID Not Found.";
    }
}
