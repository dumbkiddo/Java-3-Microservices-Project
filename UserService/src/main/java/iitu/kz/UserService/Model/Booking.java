package iitu.kz.UserService.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "bookings")
public class Booking {

    public Booking(){
    }

    public Booking(int id, String name, Date movieDate, BookingType type, User user) {
        this.id=id;
        this.name=name;
        this.movieDate=movieDate;
        this.type=type;
        this.owner=user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "movie_date")
    @Temporal(TemporalType.DATE)
    private Date movieDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BookingType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(final Date movieDate) {
        this.movieDate = movieDate;
    }

    public BookingType getType() {
        return type;
    }

    public void setType(final BookingType type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("id", this.getId())
            .append("name", this.getName())
            .append("movieDate", this.getMovieDate())
            .append("type", this.getType().getName())
            .append("Firstname", this.getOwner().getFirstName())
            .append("Lastname", this.getOwner().getLastName())
            .toString();
    }

}
