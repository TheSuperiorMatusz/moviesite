package pl.moviebase.moviesite.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Films;

@Repository
public interface FilmsRepository extends CrudRepository<Films,Long>, PagingAndSortingRepository<Films,Long> {
@Query("select  count(*) from Films f where f.genre=?1")
    Integer countFilmsByGenre(String genre);
@Query("select f from Films f where f.studio.name=?1")
    Iterable<Films> Filmsnystudio(String studio);
}