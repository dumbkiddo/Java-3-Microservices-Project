package iitu.kz.NotificationService;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="director",catalog="mid_catalog")
public class Director {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="director")
    private Set<Movie> movieLst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
