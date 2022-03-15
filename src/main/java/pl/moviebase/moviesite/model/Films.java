package pl.moviebase.moviesite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "films")
public class Films implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_film", nullable = false)
    private Long idFilm;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private Long year;

    @Column(name = "length")
    private Long length;

    @Column(name = "genre")
    private String genre;


    public Films(){

    }

    public Films(Long idFilm, String title, Long year, Long length, String genre, String studioName) {
        this.idFilm = idFilm;
        this.title = title;
        this.year = year;
        this.length = length;
        this.genre = genre;
    }

    public Set<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "films")
    private Set<Reviews> reviews;
    @ManyToOne
    @JoinColumn(name="studio_name",referencedColumnName = "name")
    private Studio studio;
}
