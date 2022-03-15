package pl.moviebase.moviesite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "reviewers")
public class Reviewers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_critic", nullable = false)
    private Long idCritic;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age")
    private Long age;

    @Column(name = "fav_genre")
    private String favGenre;

 public Reviewers(){

 }

    public Reviewers(String name, String surname, Long age, String favGenre, Long red) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.favGenre = favGenre;
    }

    public Redaction getRedaction() {
        return redaction;
    }

    public void setRedaction(Redaction redaction) {
        this.redaction = redaction;
    }

    @OneToOne
    @JoinColumn(name="red",referencedColumnName = "id_redaction")
    private Redaction redaction;

    public Set<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "reviewers")
    private Set<Reviews> reviews;
}
