
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> movies = new ArrayList<String>();
        movies.add("2395427");
        movies.add("0816692");
        movies.add("3659388");
        movies.add("1345836");
        movies.add("1300854");
        movies.add("0435761");
        movies.add("0499549");
        movies.add("1375666");
        movies.add("0496806");
        movies.add("1201607");
        return movies;
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings f = new FourthRatings();
        ArrayList<Rating> list = f.getSimilarRatings(webRaterID, 20, 5);
        System.out.println("Recommended Movies for You:");
        for (int i = 0; i < list.size(); i++) {
            if (i >= 10) break;
            System.out.println(MovieDatabase.getTitle(list.get(i).getItem()).replace("\"", ""));
        }
    }
}
