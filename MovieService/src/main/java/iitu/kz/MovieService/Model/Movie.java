package iitu.kz.MovieService.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlElement;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "movies")
public class Movie {

    public Movie(){
    }

    public Movie(int id, String firstName, Set<Synopsis> synopsis) {
        this.id=id;
        this.firstName=firstName;
        this.synopsis=synopsis;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    private String firstName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_synopsis", joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "synopsis_id"))
    private Set<Synopsis> synopsis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected Set<Synopsis> getSynopsises() {
        if (this.synopsis == null) {
            this.synopsis = new HashSet<>();
        }
        return this.synopsis;
    }

    @XmlElement
    public List<Synopsis> getSynopsis() {
        List<Synopsis> synopsises = new ArrayList<>(getSynopsises());
        PropertyComparator.sort(synopsises, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(synopsises);
    }

    public int getNumOfSynopsises() {
        return getSynopsises().size();
    }

    public void addSynopsis(Synopsis synopsis) {
        getSynopsises().add(synopsis);
    }

}
