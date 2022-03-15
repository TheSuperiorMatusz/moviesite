package pl.moviebase.moviesite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "redaction")
public class Redaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_redaction", nullable = false)
    private Long idRedaction;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "years")
    private Long years;

    @Column(name = "country", nullable = false)
    private String country;
    public Redaction(){

    }

    public Redaction(String name, Long years, String country) {
        this.name = name;
        this.years = years;
        this.country = country;
    }

    public Reviewers getReviewers() {
        return reviewers;
    }

    public void setReviewers(Reviewers reviewers) {
        this.reviewers = reviewers;
    }

    @JsonIgnore
    @OneToOne(mappedBy = "redaction",cascade = CascadeType.ALL)
    private Reviewers reviewers;
}
