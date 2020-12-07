package iitu.kz.MovieRequestService.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movie",catalog="mid_catalog")
public class Movie {

    public Movie(){
    }

    public Movie(int id, String title, String small_desc, String long_desc) {
        this.id=id;
        this.title=title;
        this.smallDesc=small_desc;
        this.longDesc=long_desc;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="small_desc")
    private String smallDesc;

    @Column(name="long_desc")
    private String longDesc;

    @ManyToOne
    @JoinColumn(name="genre_id", nullable=false)
    @JsonIgnore
    private Genre genre;

    @ManyToOne
    @JoinColumn(name="director_id", nullable=false)
    @JsonIgnore
    private Director director;

    @Column(name="price")
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallDesc() {
        return smallDesc;
    }

    public void setSmallDesc(String smallDesc) {
        this.smallDesc = smallDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {this.genre = genre; }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
