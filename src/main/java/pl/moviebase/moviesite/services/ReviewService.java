package pl.moviebase.moviesite.services;

import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Reviews;

import java.util.Optional;

public interface ReviewService {
    Iterable<Reviews> listAllReviews();
    Optional<Reviews> getReviewbyID(Long id);
    Reviews saveReview(Reviews reviews);
    void deleteReview(Long id);
    Boolean checkIfExist(Long id);
    Integer getNumberofReviews(Long idcritic);
    Integer getNumberReviewsgenre(String genre);
    Iterable<Reviews> listAllReviewsP(Integer pageNr, Integer howManytonPage);
}
