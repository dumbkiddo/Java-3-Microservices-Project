package iitu.kz.MovieRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie/request")
public class MovieRequestController {

    private final Producer producer;
    private MovieInformationService movieInformationService;

    @Autowired
    public MovieRequestController(Producer producer, MovieInformationService movieInformationService) {
        this.producer = producer;
        this.movieInformationService = movieInformationService;
    }

    @GetMapping
    public String sendMessageToKafkaTopic2(@RequestParam("movieId") String movieId){

       MovieRequest bookRequest = new MovieRequest(movieId, movieInformationService.getMovieById(movieId));
        this.producer.movieRequestNotify(bookRequest);
        return "Request was sent successfully!";
    }
}