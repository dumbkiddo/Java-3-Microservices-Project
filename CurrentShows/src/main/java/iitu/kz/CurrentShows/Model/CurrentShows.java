package iitu.kz.CurrentShows.Model;

//@Entity
//@Table(name="shows",catalog="mid_shows")
public class CurrentShows {

    /*@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")*/
    private Integer Id;

    //@Column(name="movie_id")
    private Integer movieId;

    //@Column(name="showing")
    private Integer showing;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getShowing() {
        return showing;
    }

    public void setShowing(Integer showing) {
        this.showing = showing;
    }

}
