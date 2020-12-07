package iitu.kz.MovieService.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import iitu.kz.MovieService.Model.Movie;
import iitu.kz.MovieService.Model.Synopsis;
import iitu.kz.MovieService.Repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/movies")
@RestController
@RequiredArgsConstructor
class MovieController {

    private final MovieRepository movieRepository;

    @HystrixCommand(fallbackMethod = "getAllMoviesFallback", threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping
    public List<Movie> showMovieList() {
        return movieRepository.findAll();
    }

    public List<Movie> getAllMoviesFallback() {
        List<Movie> movieList = new ArrayList<>();
        Set<Synopsis> synopsis = new HashSet<>();;
        movieList.add(new Movie(-1, "Not available", synopsis));
        return movieList;
    }
}
