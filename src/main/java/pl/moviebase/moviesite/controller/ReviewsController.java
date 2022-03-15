package pl.moviebase.moviesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.moviebase.moviesite.Interfaces.FilmsRepository;
import pl.moviebase.moviesite.Interfaces.ReviewersRepository;
import pl.moviebase.moviesite.model.Films;
import pl.moviebase.moviesite.model.Reviewers;
import pl.moviebase.moviesite.model.Reviews;
import pl.moviebase.moviesite.services.ReviewService;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ReviewsController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewersRepository reviewersRepository;
    @Autowired
    private FilmsRepository filmsRepository;
    @GetMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Reviews> redirect(Model model) {
        return  reviewService.listAllReviews();
    }
    @GetMapping(value = "/review/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reviews getByPublicId(@PathVariable(value = "id") Long id_review) {
        return reviewService.getReviewbyID(id_review).orElseGet(null);
    }
    @PostMapping(value = "/review")
    public ResponseEntity<Reviews> create(@RequestBody @NonNull @Valid
                                                Reviews reviews) {
        reviewService.saveReview(reviews);
        return ResponseEntity.ok().body(reviews);
    }
    @PutMapping(value="/review")
    public ResponseEntity<Void> edit(@RequestBody Reviews reviews){
        if(!reviewService.checkIfExist(reviews.getIdReview()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            reviewService.saveReview(reviews);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @GetMapping(value ="/review/critic/{critic}")
    public Integer getRevievscount(@PathVariable(value = "critic") Long critc){
        return reviewService.getNumberofReviews(critc);
    }
    @GetMapping(value = "review/films/{genre}")
    public Integer getReviewsfilmsc(@PathVariable(value = "genre")String genre){
        return reviewService.getNumberReviewsgenre(genre);
    }
    @DeleteMapping(value = "/review/{id}")
    public ResponseEntity delete(@PathVariable  long id) {
        if(reviewService.checkIfExist(id)) {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "/review/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Reviews> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return reviewService.listAllReviewsP(pageNr, howManyOnPage.orElse(2));
    }
    @PutMapping(value = "/review/{id}/{id_critic}/{id_film}")
    public ResponseEntity<Reviews> edit(@PathVariable Long id,@PathVariable Long id_critic,@PathVariable Long id_film){
        Reviews reviews=reviewService.getReviewbyID(id).get();
        Reviewers reviewers=reviewersRepository.findById(id_critic).get();
        Films films=filmsRepository.findById(id_film).get();
        reviews.setFilms(films);
        reviews.setReviewers(reviewers);
        reviewService.saveReview(reviews);
        return ResponseEntity.ok().body(reviews);
    }

}
