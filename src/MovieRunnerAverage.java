
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings s = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Number of Movies: " + s.getMovieSize());
        System.out.println("Number of Raters: " + s.getRaterSize());
        
        ArrayList<Rating> averageRatings = s.getAverageRatings(5);
        Collections.sort(averageRatings);
        for (Rating r : averageRatings) {
            System.out.println(r.getValue() + " " + s.getTitle(r.getItem()).replace("\"", ""));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings s = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        ArrayList<Rating> averageRatings = s.getAverageRatings(3);
        for (Rating r : averageRatings) {
            if (s.getTitle(r.getItem()).equals("\"The Godfather\"")) {
                System.out.println(r.getValue() + " " + s.getTitle(r.getItem()).replace("\"", ""));
            }
        }
    }
}

class MovieRunnerAverageTester {
    public static void main(String[] args) {
        MovieRunnerAverage m = new MovieRunnerAverage();
        m.printAverageRatings();
        //m.getAverageRatingOneMovie();
    }
}
