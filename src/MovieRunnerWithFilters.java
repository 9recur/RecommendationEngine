
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public static void main(String[] args) {
        //printAverageRatings();
        //printAverageRatingsByYearAfter();
        //printAverageRatingsByGenre();
        //printAverageRatingsByMinutes();
        //printAverageRatingsByDirectors();
        printAverageRatingsByYearAfterAndGenre();
        //printAverageRatingsByDirectorsAndMinutes();
    }
    
    public static void printAverageRatings() {
        ThirdRatings t = new ThirdRatings("data/ratings.csv");
        System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        ArrayList<Rating> averageRatings = t.getAverageRatings(35);
        Collections.sort(averageRatings);
        System.out.println("Found " + averageRatings.size() + " movies:");
        for (Rating r : averageRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
        }
    }
    
    public static void printAverageRatingsByYearAfter() {
        ThirdRatings t = new ThirdRatings("data/ratings.csv");
        System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        ArrayList<Rating> averageRatingsByYear = t.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        Collections.sort(averageRatingsByYear);
        System.out.println("Found " + averageRatingsByYear.size() + " movies:");
        for (Rating r : averageRatingsByYear) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
        }
    }
    
    public static void printAverageRatingsByGenre() {
       ThirdRatings t = new ThirdRatings("data/ratings.csv");
       System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
       ArrayList<Rating> averageRatingsByGenre = t.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
       Collections.sort(averageRatingsByGenre);
       System.out.println("Found " + averageRatingsByGenre.size() + " movies:");
       for (Rating r : averageRatingsByGenre) {
           System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
           System.out.println("\t" + MovieDatabase.getGenres(r.getItem()).replace("\"", ""));
       } 
    }
    
    public static void printAverageRatingsByMinutes() {
       ThirdRatings t = new ThirdRatings("data/ratings.csv");
       System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
       ArrayList<Rating> averageRatingsByMinutes = t.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
       Collections.sort(averageRatingsByMinutes);
       System.out.println("Found " + averageRatingsByMinutes.size() + " movies:");
       for (Rating r : averageRatingsByMinutes) {
           System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
       } 
    }
    
    public static void printAverageRatingsByDirectors() {
       ThirdRatings t = new ThirdRatings("data/ratings.csv");
       System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
       ArrayList<Rating> averageRatingsByDirector = t.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
       Collections.sort(averageRatingsByDirector);
       System.out.println("Found " + averageRatingsByDirector.size() + " movies:");
       for (Rating r : averageRatingsByDirector) {
           System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
           System.out.println("\t" + MovieDatabase.getDirector(r.getItem()).replace("\"", ""));
       } 
    }
    
    public static void printAverageRatingsByYearAfterAndGenre() {
       AllFilters f = new AllFilters();
       f.addFilter(new YearAfterFilter(1990));
       f.addFilter(new GenreFilter("Drama"));
        
       ThirdRatings t = new ThirdRatings("data/ratings.csv");
       System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Read data for " + MovieDatabase.size() + " movies."); 
        
       ArrayList<Rating> averageRatings = t.getAverageRatingsByFilter(8, f);
       Collections.sort(averageRatings);
       System.out.println("Found " + averageRatings.size() + " movies:");
       for (Rating r : averageRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
           System.out.println("\t" + MovieDatabase.getGenres(r.getItem()).replace("\"", ""));
       } 
    }
    
    public static void printAverageRatingsByDirectorsAndMinutes() {
       AllFilters f = new AllFilters();
       f.addFilter(new MinutesFilter(90, 180));
       f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        
       ThirdRatings t = new ThirdRatings("data/ratings.csv");
       System.out.println("Read data for " + t.getRaterSize() + " raters.");
        
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
       ArrayList<Rating> averageRatings = t.getAverageRatingsByFilter(3, f);
       Collections.sort(averageRatings);
       System.out.println("Found " + averageRatings.size() + " movies:");
       for (Rating r : averageRatings) {
           System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()).replace("\"", ""));
           System.out.println("\t" + MovieDatabase.getDirector(r.getItem()).replace("\"", ""));
       } 
    }
}
