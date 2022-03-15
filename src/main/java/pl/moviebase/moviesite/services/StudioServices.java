package pl.moviebase.moviesite.services;

import pl.moviebase.moviesite.model.Redaction;
import pl.moviebase.moviesite.model.Studio;

import java.util.Optional;

public interface StudioServices {
    Iterable<Studio> listAllStudios();
    Optional<Studio> getStudioname(String name);
    Studio savestudio(Studio studio);
    void deletestudio(String name);
    Boolean checkIfExist(String name);
    Iterable<Studio> listAllStudionPaging(Integer pageNr,Integer howManytonPage);
}

