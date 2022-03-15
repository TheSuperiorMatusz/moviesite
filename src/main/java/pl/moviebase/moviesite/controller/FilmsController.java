package pl.moviebase.moviesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.moviebase.moviesite.model.Chairman;
import pl.moviebase.moviesite.model.Films;
import pl.moviebase.moviesite.services.ChairmanServices;
import pl.moviebase.moviesite.services.FilmsServices;

import javax.validation.Valid;
import java.util.Optional;

    @RestController
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/api")
    public class FilmsController {
        @Autowired
        private FilmsServices filmsServices;

        @GetMapping(value = "/films", produces = MediaType.APPLICATION_JSON_VALUE)
        public Iterable<Films> redirect(Model model) {
            return  filmsServices.listAllFilms();
        }
        @GetMapping(value = "/films/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Films getByPublicId(@PathVariable(value = "id") Long id_film) {
            return filmsServices.getFilmbyID(id_film).orElseGet(null);
        }
        @GetMapping(value = "/films/studio/{studio}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Iterable<Films> getByStudio(@PathVariable(value = "studio") String studio) {
            return filmsServices.FilmsbyStudio(studio);
        }
        @PostMapping(value = "/films")
        public ResponseEntity<Films> create(@RequestBody @NonNull @Valid
                                                       Films Films) {
            filmsServices.saveFilm(Films);
            return ResponseEntity.ok().body(Films);
        }
        @PutMapping(value="/films")
        public ResponseEntity<Void> edit(@RequestBody Films Films){
            if(!filmsServices.checkIfExist(Films.getIdFilm()))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else {
                filmsServices.saveFilm(Films);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        @DeleteMapping(value = "/Films/{id}")
        public ResponseEntity delete(@PathVariable  long id) {
            if(filmsServices.checkIfExist(id)) {
                filmsServices.deleteFilm(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        @GetMapping(value ="/films/{genre}")
        public Integer getFilmsgenrelms(@PathVariable String genre){
            return filmsServices.getNumberofFilmsGenre(genre);
        }
        @GetMapping(value = "/Films/paging/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Iterable<Films> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
            return filmsServices.listAllFilmsPaging(pageNr, howManyOnPage.orElse(2));
        }
    }

