package pl.moviebase.moviesite.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.moviebase.moviesite.model.Reviewers;

import java.util.Collection;
import java.util.Collections;

@Repository
public interface ReviewersRepository extends CrudRepository<Reviewers,Long>, PagingAndSortingRepository<Reviewers,Long> {
 @Query("select r from Reviewers r where r.age=?1 ")
 Iterable<Reviewers> Findingbyage(Long age);
}