
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile) {
        FirstRatings f = new FirstRatings();
        myRaters = f.loadRaters(ratingsFile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averages = new ArrayList<Rating>();
        for (String id : movies) {
            double averageRating = getAverageByID(id, minimalRaters);
            if (averageRating > 0.0) {
                averages.add(new Rating(id, averageRating));
            }
        }
        return averages;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averages = new ArrayList<Rating>();
        for (String id : movies) {
            double averageRating = getAverageByID(id, minimalRaters);
            if (averageRating > 0.0) {
                averages.add(new Rating(id, averageRating));
            }
        }
        return averages;
    }
}
