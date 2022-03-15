package pl.moviebase.moviesite.services;

import pl.moviebase.moviesite.model.Reviewers;

import java.util.Collection;
import java.util.Optional;

public interface ReviewersServicesin {
    Iterable<Reviewers> listAllReviewers();
    Optional<Reviewers> getReviewersId(Long id);
    Reviewers saveRedaction(Reviewers reviewers);
    void deleteRevies(Long id);
    Boolean checkIfExist(Long id);
    Iterable<Reviewers> Reviersbyage(Long age);
    Iterable<Reviewers> listAllReviewersPaging(Integer pageNr,Integer howManytonPage);
}
