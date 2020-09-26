package iitu.kz.MovieCatalog.Model;

public class CatalogMovie {
    private String movieName;
    private String movieDesc;
    private String rating;

    public CatalogMovie(){

    }

    public CatalogMovie(String movieName, String movieDesc, String rating) {
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}