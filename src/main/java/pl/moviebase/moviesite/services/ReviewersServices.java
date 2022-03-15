package pl.moviebase.moviesite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pl.moviebase.moviesite.Interfaces.ReviewersRepository;
import pl.moviebase.moviesite.model.Reviewers;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReviewersServices implements ReviewersServicesin{
    @Autowired
    private ReviewersRepository reviewersRepository;
    @Override
    public Iterable<Reviewers> listAllReviewers() {
       return reviewersRepository.findAll();
    }

    @Override
    public Optional<Reviewers> getReviewersId(Long id) {
        return reviewersRepository.findById(id);
    }

    @Override
    public Reviewers saveRedaction(Reviewers reviewers) {
        return reviewersRepository.save(reviewers);
    }

    @Override
    public void deleteRevies(Long id) {
        reviewersRepository.deleteById(id);

    }

    @Override
    public Boolean checkIfExist(Long id) {
        if(reviewersRepository.existsById(id)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Iterable<Reviewers> Reviersbyage(Long age) {
        return  reviewersRepository.Findingbyage(age);
    }

    @Override
    public Iterable<Reviewers> listAllReviewersPaging(Integer pageNr, Integer howManytonPage) {
        return reviewersRepository.findAll(PageRequest.of(pageNr,howManytonPage));
    }
}
