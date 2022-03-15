package pl.moviebase.moviesite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.moviebase.moviesite.Interfaces.StudioRepository;
import pl.moviebase.moviesite.model.Studio;

import java.util.Optional;
@Service
public class StudioServicesImplementation implements StudioServices{
    @Autowired
    private StudioRepository studioRepository;
    @Override
    public Iterable<Studio> listAllStudios() {
        return studioRepository.findAll();
    }

    @Override
    public Optional<Studio> getStudioname(String name) {
        return studioRepository.findById(name);
    }

    @Override
    public Studio savestudio(Studio studio) {
       return studioRepository.save(studio);
    }

    @Override
    public void deletestudio(String name) {
     studioRepository.deleteById(name);
    }

    @Override
    public Boolean checkIfExist(String name) {
        if(studioRepository.existsById(name)){
            return true;
        }else
        {
           return false;
        }
    }

    @Override
    public Iterable<Studio> listAllStudionPaging(Integer pageNr, Integer howManytonPage) {
        return studioRepository.findAll(PageRequest.of(pageNr,howManytonPage));
    }
}
