package pl.moviebase.moviesite.services;

import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Redaction;

import java.util.Optional;

public interface RedactionServices {
    Iterable<Redaction> listAllRedactions();
    Optional<Redaction> getRedactionId(Long id);
    Redaction saveRedaction(Redaction redaction);
    void deleteRedaction(Long id);
    Boolean checkIfExist(Long id);
    Iterable<Redaction> listAllRedactionPaging(Integer pageNr,Integer howManytonPage);
}
