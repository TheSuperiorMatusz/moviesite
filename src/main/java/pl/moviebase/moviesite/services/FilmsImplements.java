package pl.moviebase.moviesite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.moviebase.moviesite.Interfaces.FilmsRepository;
import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Films;

import java.util.Optional;
@Service
public class FilmsImplements implements FilmsServices{
    @Autowired
    private FilmsRepository filmsRepository;
    @Override
    public Iterable<Films> listAllFilms() {
        return filmsRepository.findAll();
    }

    @Override
    public Optional<Films> getFilmbyID(Long id) {
        return filmsRepository.findById(id);
    }

    @Override
    public Films saveFilm(Films films) {
        return filmsRepository.save(films);
    }

    @Override
    public void deleteFilm(Long id) {
     filmsRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Long id) {
        if(filmsRepository.existsById(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Integer getNumberofFilmsGenre(String genre){
        return filmsRepository.countFilmsByGenre(genre);
    }

    @Override
    public Iterable<Films> FilmsbyStudio(String studio) {
       return filmsRepository.Filmsnystudio(studio);
    }

    @Override
    public Iterable<Films> listAllFilmsPaging(Integer pageNr, Integer howManytonPage) {
        return filmsRepository.findAll(PageRequest.of(pageNr,howManytonPage));
    }
}
