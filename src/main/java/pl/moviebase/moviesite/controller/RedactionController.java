package pl.moviebase.moviesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.moviebase.moviesite.model.Redaction;
import pl.moviebase.moviesite.model.Reviewers;
import pl.moviebase.moviesite.services.RedactionServices;
import pl.moviebase.moviesite.services.ReviewersServicesin;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RedactionController {
    @Autowired
    private RedactionServices redactionServices;
    @GetMapping(value = "/redaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Redaction> redirect(Model model) {
        return  redactionServices.listAllRedactions();
    }
    @GetMapping(value = "/redaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Redaction getByPublicId(@PathVariable(value = "id") Long id_red) {
        return redactionServices.getRedactionId(id_red).orElseGet(null);
    }
    @PostMapping(value = "/redaction")
    public ResponseEntity<Redaction> create(@RequestBody @NonNull @Valid
                                                    Redaction redaction) {
       redactionServices.saveRedaction(redaction);
        return ResponseEntity.ok().body(redaction);
    }
    @PutMapping(value="/redaction")
    public ResponseEntity<Void> edit(@RequestBody Redaction redaction){
        if(!redactionServices.checkIfExist(redaction.getIdRedaction()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            redactionServices.saveRedaction(redaction);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/redaction/{id}")
    public ResponseEntity delete(@PathVariable  long id) {
        if(redactionServices.checkIfExist(id)) {
            redactionServices.deleteRedaction(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "/redaction/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Redaction> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return redactionServices.listAllRedactionPaging(pageNr, howManyOnPage.orElse(2));
    }
}
