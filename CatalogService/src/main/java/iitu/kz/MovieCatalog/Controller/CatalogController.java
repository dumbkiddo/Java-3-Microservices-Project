package iitu.kz.MovieCatalog.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import iitu.kz.MovieCatalog.DTO.MovieDTO;
import iitu.kz.MovieCatalog.Model.Director;
import iitu.kz.MovieCatalog.Model.Genre;
import iitu.kz.MovieCatalog.Model.Movie;
import iitu.kz.MovieCatalog.Repository.DirectorRepository;
import iitu.kz.MovieCatalog.Repository.GenreRepository;
import iitu.kz.MovieCatalog.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private Environment env;

    @Autowired
    RestTemplate restTemplate;

    public CatalogController(MovieRepository movieRepository, DirectorRepository directorRepository,GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
    }

    @HystrixCommand(fallbackMethod = "getAllMoviesFallback", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "15"),
            @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping("/get-all-movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getAllMoviesFallback() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(-1, "Not available", "Not available","Not available"));
        return movieList;
    }


//    public ResponseEntity<MovieDTO> getMovie(@PathVariable("movieId") Integer movieId) {
//        Movie movie = null;
//        Optional<Movie> movieData =  movieRepository.findById(movieId);
//        if(movieData.isPresent()) {
//            movie =  movieData.get();
//        }
//
//        MovieDTO movieDTO = new MovieDTO();
//        if(movie !=null) {
//
//            movieDTO.setBookId(movieId);
//            movieDTO.setGenre(movie.getGenre().getId());
//            movieDTO.setLongDesc(movie.getLongDesc());
//            movieDTO.setPrice(movie.getPrice());
//            movieDTO.setDirectorId(movie.getDirector().getId());
//            movieDTO.setSmallDesc(movie.getSmallDesc());
//            movieDTO.setTitle(movie.getTitle());
//            movieDTO.setPort(env.getProperty("local.server.port"));
//
//            return new ResponseEntity(movieDTO,HttpStatus.OK);
//        }else {
//            return new ResponseEntity(movieDTO, HttpStatus.NOT_FOUND);
//        }
//    }

    @HystrixCommand(fallbackMethod = "getMovieByIdFallback", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "15"),
            @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping("/get-movie/{movieId}")
    public MovieDTO getMovie(@PathVariable("movieId") Integer movieId){

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://movie-service/movie/get-movie-by-id/" + movieId,
                HttpMethod.GET, entity, MovieDTO.class).getBody();
    }
    public ResponseEntity<MovieDTO> getMovieByIdFallback(@PathVariable("movieId") Integer movieId){
        MovieDTO movieDTO = new MovieDTO();
        return new ResponseEntity(movieDTO, HttpStatus.BAD_REQUEST);
    }

    @HystrixCommand
    @PostMapping(value="/addMovie",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> addNewMovie(@Valid @RequestBody MovieDTO movieDTO, Errors errors) {

        if(errors.hasErrors()) {
            List<String> errorMsg = new ArrayList<String>();
            errors.getAllErrors().forEach(a -> errorMsg.add(a.getDefaultMessage()));
            return new ResponseEntity<List<String>>(errorMsg, HttpStatus.BAD_REQUEST);
        }else {
            Movie movieEntity = new Movie();
            Director movieDirector = getDirector(movieDTO.getPublisherId());
            movieEntity.setDirector(movieDirector);

            movieEntity.setPrice(movieDTO.getPrice());
            movieEntity.setGenre(getGenre(movieDTO.getCategory()));
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
