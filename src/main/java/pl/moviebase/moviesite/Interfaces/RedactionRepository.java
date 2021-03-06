package pl.moviebase.moviesite.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.moviebase.moviesite.model.Redaction;

@Repository
public interface RedactionRepository extends CrudRepository<Redaction,Long>, PagingAndSortingRepository<Redaction,Long> {

}