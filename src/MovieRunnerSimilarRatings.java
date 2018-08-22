
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public static void main(String[] args) {
        //printAverageRatings();
        //printAverageRatingsByYearAfterAndGenre();
        //printSimilarRatings();
        printSimilarRatingsByGenre();
        //printSimilarRatingsByDirector();
        //printSimilarRatingsByGenreAndMinutes();
        //printSimilarRatingsByYearAfterAndMinutes();
    }
    
    public static void printAverageRatings() {
        FourthRatings f = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Read data for " + RaterDatabase.size() + " raters.");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        ArrayList<Rating> averageRatings = f.getAverageRatings(35);
        Collections.sort(averageRatings);
        System.out.println("Found " + averageRatings.size() + " movies:");
        for (Rating r : averageRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
        }
    }
    
    public static void printAverageRatingsByYearAfterAndGenre() {
       AllFilters f = new AllFilters();
       f.addFilter(new YearAfterFilter(1990));
       f.addFilter(new GenreFilter("Drama"));
        
       FourthRatings fr = new FourthRatings();
       RaterDatabase.initialize("ratings.csv");
       System.out.println("Read data for " + RaterDatabase.size() + " raters.");
        
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Read data for " + MovieDatabase.size() + " movies."); 
        
       ArrayList<Rating> averageRatings = fr.getAverageRatingsByFilter(8, f);
       Collections.sort(averageRatings);
       System.out.println("Found " + averageRatings.size() + " movies:");
       for (Rating r : averageRatings) {
           System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
           System.out.println("\t" + MovieDatabase.getGenres(r.getItem()).replace("\"", ""));
       } 
    }
    
    public static void printSimilarRatings() {
        FourthRatings f = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ArrayList<Rating> list = f.getSimilarRatings("71", 20, 5);
        for (Rating r : list) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public static void printSimilarRatingsByGenre() {
        FourthRatings f = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ArrayList<Rating> list = f.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        for (Rating r : list) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public static void printSimilarRatingsByDirector() {
        FourthRatings f = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ArrayList<Rating> list = f.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating r : list) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public static void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings f = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter("Drama"));
        af.addFilter(new MinutesFilter(80, 160));
        
        ArrayList<Rating> list = f.getSimilarRatingsByFilter("168", 10, 3, af);
        for (Rating r : list) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public static void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings f = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1975));
        af.addFilter(new MinutesFilter(70, 200));
        
        ArrayList<Rating> list = f.getSimilarRatingsByFilter("314", 10, 5, af);
        for (Rating r : list) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
}
