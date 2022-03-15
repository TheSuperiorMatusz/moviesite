package pl.moviebase.moviesite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.moviebase.moviesite.Interfaces.ReviewersRepository;
import pl.moviebase.moviesite.Interfaces.ReviewsRepository;
import pl.moviebase.moviesite.model.Reviews;

import java.util.Optional;

@Service
public class ReviewsImplements implements ReviewService {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private ReviewersRepository reviewersRepository;
    @Override
    public Iterable<Reviews> listAllReviews() {
      return reviewsRepository.findAll();
    }

    @Override
    public Optional<Reviews> getReviewbyID(Long id) {
       return reviewsRepository.findById(id);
    }

    @Override
    public Reviews saveReview(Reviews reviews) {
        return reviewsRepository.save(reviews);
    }

    @Override
    public void deleteReview(Long id) {
     reviewsRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Long id) {
        if(reviewsRepository.existsById(id)){
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    public Integer getNumberofReviews(Long idcritic) {
        return reviewsRepository.countReviewCritic(idcritic);
    }

    @Override
    public Integer getNumberReviewsgenre(String genre) {
        return reviewsRepository.countfFilmgenre(genre);
    }

    @Override
    public Iterable<Reviews> listAllReviewsP(Integer pageNr, Integer howManytonPage) {
        return reviewsRepository.findAll(PageRequest.of(pageNr,howManytonPage));
    }
}
