
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] directors;
    
    public DirectorsFilter(String directors) {
        this.directors = directors.split(",");
    }
    
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        for (String s : directors) {
            if (movieDirectors.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
