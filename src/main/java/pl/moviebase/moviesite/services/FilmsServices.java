package pl.moviebase.moviesite.services;

import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Films;

import java.util.Optional;

public interface FilmsServices {
    Iterable<Films> listAllFilms();
    Optional<Films> getFilmbyID(Long id);
   Films saveFilm(Films films);
    void deleteFilm(Long id);
    Boolean checkIfExist(Long id);
    Integer getNumberofFilmsGenre(String genre);
    Iterable<Films> FilmsbyStudio(String studio);
    Iterable<Films> listAllFilmsPaging(Integer pageNr, Integer howManytonPage);
}
