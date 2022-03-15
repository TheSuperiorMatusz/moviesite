package pl.moviebase.moviesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.moviebase.moviesite.model.Studio;
import pl.moviebase.moviesite.services.StudioServices;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")

public class StudioController {
    @Autowired
    private StudioServices studioServices;
    @GetMapping(value = "/studio", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Studio> redirect(Model model) {
        return  studioServices.listAllStudios();
    }
    @GetMapping(value = "/studio/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Studio getByPublicId(@PathVariable(value = "name") String name) {
        return studioServices.getStudioname(name).orElseGet(null) ;
    }
    @PostMapping(value = "/studio")
    public ResponseEntity<Studio> create(@RequestBody @NonNull @Valid
                                                Studio studio) {
        studioServices.savestudio(studio);
        return ResponseEntity.ok().body(studio);
    }
    @PutMapping(value="/studio")
    public ResponseEntity<Void> edit(@RequestBody Studio studio){
        if(!studioServices.checkIfExist(studio.getName()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
             studioServices.savestudio(studio);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/studio/{name}")
    public ResponseEntity delete(@PathVariable  String name) {
        if(studioServices.checkIfExist(name)) {
            studioServices.deletestudio(name);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "/studio/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Studio> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return studioServices.listAllStudionPaging(pageNr, howManyOnPage.orElse(2));
    }
}
