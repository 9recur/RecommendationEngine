import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {  
            Scanner in = new Scanner(new File(fileName));
            in.nextLine();
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                movies.add(new Movie(line[0], line[1], line[2], line[4], line[5], line[3], line[7], Integer.parseInt(line[6])));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String fileName) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        try {  
            Scanner in = new Scanner(new File(fileName));
            in.nextLine();
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                boolean existingRater = false;
                for (int i = 0; i < raters.size(); i++) {
                    if (raters.get(i).getID().equals(line[0])) {
                        raters.get(i).addRating(line[1], Integer.parseInt(line[2]));
                        existingRater = true;
                        break;
                    }
                }
                if (!existingRater) {
                    Rater r = new EfficientRater(line[0]);
                    r.addRating(line[1], Integer.parseInt(line[2]));
                    raters.add(r);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return raters;
    }
    
    /*
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        int comedyCount = 0;
        int lengthCount = 0;
        for (Movie m : movies) {
            //System.out.println(m.toString());
            if (m.getGenres().contains("Comedy")) {
                comedyCount++;
            }
            
            if (m.getMinutes() > 150) {
                lengthCount++;
            }
        }
        System.out.println(comedyCount);
        System.out.println(lengthCount);
    }*/
    
    /*
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Number of raters: " + raters.size());
        int maxRatings = 0;
        for (Rater r : raters) {
            
            System.out.println(r.getID() + ", " + r.numRatings());
            ArrayList<String> ratedMovies = r.getItemsRated();
            for (String s : ratedMovies) {
                System.out.println("\t" + s + ". Rating: " + r.getRating(s));
            }
            System.out.println("====================");
            
            if (r.numRatings() > maxRatings) {
                maxRatings = r.numRatings();
                System.out.println("num: " + maxRatings);
                System.out.println("id: " + r.getID());
            }
        }
    }*/
}
