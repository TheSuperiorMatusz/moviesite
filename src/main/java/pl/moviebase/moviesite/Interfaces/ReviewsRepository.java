package pl.moviebase.moviesite.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.moviebase.moviesite.model.Reviews;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews,Long>, PagingAndSortingRepository<Reviews,Long> {
    @Query("select  count(*) from Reviews r where r.reviewers.idCritic=?1")
    Integer countReviewCritic(Long id_film);
    @Query("select count(*) from Reviews r  where r.films.genre=?1")
    Integer countfFilmgenre(String genre);
}