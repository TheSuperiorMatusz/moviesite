package pl.moviebase.moviesite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.moviebase.moviesite.Interfaces.ChairmanRepository;
import pl.moviebase.moviesite.model.Chairman;

import java.util.Optional;

@Service
public class ChairmanServiceImplements implements ChairmanServices{
    @Autowired
    private ChairmanRepository chairmanRepository;

    @Override
    public Iterable<Chairman> listAllChairmen() {
        return chairmanRepository.findAll();
    }

    @Override
    public Optional<Chairman> getChairmanbypersonalIdNum(String personalIdNum) {
        return chairmanRepository.findById(personalIdNum);
    }

    @Override
    public Chairman saveChairman(Chairman chairman) {
        return chairmanRepository.save(chairman);
    }

    @Override
    public void deleteChairman(String personalIdNum) {
      chairmanRepository.deleteById(personalIdNum);
    }

    @Override
    public Boolean checkIfExist(String personalIdNum) {
        if(chairmanRepository.existsById(personalIdNum)){
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    public Iterable<Chairman> listAllChairmanPaging(Integer pageNr, Integer howManyonPage) {
        return chairmanRepository.findAll(PageRequest.of(pageNr,howManyonPage));
    }
}
