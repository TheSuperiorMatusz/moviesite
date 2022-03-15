package pl.moviebase.moviesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.services.ChairmanServices;
import org.springframework.lang.NonNull;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ChairmanController {
    @Autowired
    private ChairmanServices chairmanServices;

    @GetMapping(value = "/chairman", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Chairman> redirect(Model model) {
        return chairmanServices.listAllChairmen();
    }
    @GetMapping(value = "/chairman/{personalIdNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Chairman getByPublicId(@PathVariable(value = "personalIdNum") String personalIdNum) {
        return chairmanServices.getChairmanbypersonalIdNum(personalIdNum).orElseGet(null);
    }
    @PostMapping(value = "/chairman")
    public ResponseEntity<Chairman> create(@RequestBody @NonNull @Valid
                                                  Chairman chairman) {

        chairmanServices.saveChairman(chairman);
        return ResponseEntity.ok().body(chairman);
    }
    @PutMapping(value="/chairman")
    public ResponseEntity<Void> edit(@RequestBody Chairman chairman){
        if(!chairmanServices.checkIfExist(chairman.getPersonalIdNum()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            chairmanServices.saveChairman(chairman);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/chairman/{personalIdNum}")
    public ResponseEntity delete(@PathVariable  String personalIdNum) {
        if(chairmanServices.checkIfExist(personalIdNum)) {
            chairmanServices.deleteChairman(personalIdNum);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "/chairman/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Chairman> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return chairmanServices.listAllChairmanPaging(pageNr, howManyOnPage.orElse(2));
    }
}
