package pl.moviebase.moviesite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.moviebase.moviesite.Interfaces.RedactionRepository;
import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Redaction;

import java.util.Optional;
@Service
public class RedactionService implements RedactionServices
{
    @Autowired
    private RedactionRepository redactionRepository;
    @Override
    public Iterable<Redaction> listAllRedactions() {
        return redactionRepository.findAll();
    }

    @Override
    public Optional<Redaction> getRedactionId(Long id) {
        return redactionRepository.findById(id);
    }

    @Override
    public Redaction saveRedaction(Redaction redaction) {
        return redactionRepository.save(redaction);
    }

    @Override
    public void deleteRedaction(Long id) {
        redactionRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Long id) {
        if(redactionRepository.existsById(id)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public Iterable<Redaction> listAllRedactionPaging(Integer pageNr, Integer howManytonPage) {
       return redactionRepository.findAll(PageRequest.of(pageNr,howManytonPage));
    }
}
