package iitu.kz.MovieRequest;


public class MovieRequest {

    private String movieId;
    private Movie movie;

    public MovieRequest() {
    }

    public MovieRequest(String movieId, Movie movie) {
        this.movieId = movieId;
        this.movie = movie;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Movie information:" + movie;
    }
}