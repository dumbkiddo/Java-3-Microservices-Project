package iitu.kz.MovieRequest;

import javax.persistence.Entity;
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

    private Integer id;
    private String title;
    private String smallDesc;
    private String longDesc;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " Title='" + title + '\'' +
                ", synopsis='" + smallDesc + '\'' +
                ", details='" + longDesc;
    }

}