package pl.moviebase.moviesite.services;

import pl.moviebase.moviesite.model.Chairman;

import java.util.Optional;

public interface ChairmanServices {
    Iterable<Chairman> listAllChairmen();
    Optional<Chairman> getChairmanbypersonalIdNum(String personalIdNum);
    Chairman saveChairman(Chairman chairman);
    void deleteChairman(String personalIdNum);
    Boolean checkIfExist(String personalIdNum);
    Iterable<Chairman> listAllChairmanPaging(Integer pageNr,Integer howManytonPage);

}
