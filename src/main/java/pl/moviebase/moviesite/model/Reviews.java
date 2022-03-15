package pl.moviebase.moviesite.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "reviews")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=Reviews.class)
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_review", nullable = false)
    private Long idReview;


    @Column(name = "review")
    private String review;

    @Column(name = "data_publication")
    private Date Data_publication;



    public Reviews(Long idReview,Long filmId, String review, Date Data_publication) {
        this.idReview = idReview;
        this.review = review;
        this.Data_publication =Data_publication;
    }

    public Reviews() {

    }

    public Reviewers getReviewers() {
        return reviewers;
    }

    public void setReviewers(Reviewers reviewers) {
        this.reviewers = reviewers;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_critic",referencedColumnName = "id_critic")
    private Reviewers reviewers;

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="film_id",referencedColumnName = "id_film")
    private Films films;
}
