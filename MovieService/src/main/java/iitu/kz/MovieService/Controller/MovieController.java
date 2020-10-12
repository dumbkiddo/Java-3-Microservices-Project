package iitu.kz.MovieService.Controller;

import iitu.kz.MovieService.DTO.MovieDTO;
import iitu.kz.MovieService.Model.Director;
import iitu.kz.MovieService.Model.Genre;
import iitu.kz.MovieService.Model.Movie;
import iitu.kz.MovieService.Repository.DirectorRepository;
import iitu.kz.MovieService.Repository.GenreRepository;
import iitu.kz.MovieService.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private Environment env;

    public MovieController(MovieRepository movieRepository, DirectorRepository directorRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/get-all-movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @PostMapping(value="/addMovie",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> addNewBook(@Valid @RequestBody MovieDTO movieDTO, Errors errors) {

        if(errors.hasErrors()) {
            List<String> errorMsg = new ArrayList<String>();
            errors.getAllErrors().forEach(a -> errorMsg.add(a.getDefaultMessage()));
            return new ResponseEntity<List<String>>(errorMsg, HttpStatus.BAD_REQUEST);
        }else {
            Movie movieEntity = new Movie();
            Director movieDirector = getDirector(movieDTO.getDirectorId());
            movieEntity.setDirector(movieDirector);

            movieEntity.setPrice(movieDTO.getPrice());
            movieEntity.setGenre(getGenre(movieDTO.getGenre()));
            movieEntity.setLongDesc(movieDTO.getLongDesc());
            movieEntity.setSmallDesc(movieDTO.getSmallDesc());
            movieEntity.setTitle(movieDTO.getTitle());


            movieRepository.save(movieEntity);
            List<String> msgLst = Arrays.asList("Movie '"+movieDTO.getTitle()+"' has been added successfully");
            return new ResponseEntity<List<String>>(msgLst, HttpStatus.OK);
        }
    }

    private Director getDirector(Integer directorId) {
        Optional<Director> directorData =  directorRepository.findById(directorId);
        if(directorData.isPresent()) {
            return directorData.get();
        }else {
            return null;
        }
    }

    private Genre getGenre(Integer genreId){
        Optional<Genre> genreData =  genreRepository.findById(genreId);
        if(genreData.isPresent()) {
            return genreData.get();
        }else {
            return null;
        }
    }
}
