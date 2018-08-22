
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters) {
        double sum = 0;
        int ratingsCount = 0;
        for (Rater r : RaterDatabase.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
        for (String movie : MovieDatabase.filterBy(new TrueFilter())) {
            if (me.hasRating(movie) && r.hasRating(movie)) {
                dotProduct += (me.getRating(movie) - 5) * (r.getRating(movie) - 5);
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (r != me && dotProduct(me, r) > 0) {
                list.add(new Rating(r.getID(), dotProduct(me, r)));
                //System.out.println("DP: " + dotProduct(me, r));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String movie : MovieDatabase.filterBy(new TrueFilter())) {
            double total = 0;
            int count = 0;
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = similarities.get(k);
                if (RaterDatabase.getRater(r.getItem()).hasRating(movie)) {
                    total += r.getValue() * RaterDatabase.getRater(r.getItem()).getRating(movie);
                    count++;
                }
            }
            if (count >= minimalRaters) {
                ret.add(new Rating(movie, Math.round(total / count * 10000.0) / 10000.0));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String movie : MovieDatabase.filterBy(filterCriteria)) {
            double total = 0;
            int count = 0;
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = similarities.get(k);
                if (RaterDatabase.getRater(r.getItem()).hasRating(movie)) {
                    total += r.getValue() * RaterDatabase.getRater(r.getItem()).getRating(movie);
                    count++;
                }
            }
            if (count >= minimalRaters) {
                ret.add(new Rating(movie, Math.round(total / count * 10000.0) / 10000.0));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
}
