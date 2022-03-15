package pl.moviebase.moviesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.moviebase.moviesite.model.Reviewers;
import pl.moviebase.moviesite.model.Reviews;
import pl.moviebase.moviesite.services.ReviewService;
import pl.moviebase.moviesite.services.ReviewersServicesin;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ReviewersController {
    @Autowired
    private ReviewersServicesin reviewersServicesin;
    @GetMapping(value = "/critic", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Reviewers> redirect(Model model) {
        return  reviewersServicesin.listAllReviewers();
    }
    @GetMapping(value = "/critic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reviewers getByPublicId(@PathVariable(value = "id") Long id_review) {
        return reviewersServicesin.getReviewersId(id_review).orElseGet(null);
    }
    @GetMapping(value = "/critic/age/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Reviewers> getByage(@PathVariable(value = "age") Long age) {
        return reviewersServicesin.Reviersbyage(age);
    }
    @PostMapping(value = "/critic")
    public ResponseEntity<Reviewers> create(@RequestBody @NonNull @Valid
                                                  Reviewers reviewers) {
        reviewersServicesin.saveRedaction(reviewers);
        return ResponseEntity.ok().body(reviewers);
    }
    @PutMapping(value="/critic")
    public ResponseEntity<Void> edit(@RequestBody Reviewers reviewers){
        if(!reviewersServicesin.checkIfExist(reviewers.getIdCritic()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            reviewersServicesin.saveRedaction(reviewers);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/critic/{id}")
    public ResponseEntity delete(@PathVariable  long id) {
        if(reviewersServicesin.checkIfExist(id)) {
            reviewersServicesin.deleteRevies(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "/critic/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Reviewers> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return reviewersServicesin.listAllReviewersPaging(pageNr, howManyOnPage.orElse(2));
    }
}
