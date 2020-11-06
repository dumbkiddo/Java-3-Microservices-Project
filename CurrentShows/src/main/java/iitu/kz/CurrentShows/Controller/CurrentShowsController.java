package iitu.kz.CurrentShows.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import iitu.kz.CurrentShows.DTO.MovieDTO;
import iitu.kz.CurrentShows.MovieCatalogProxy.MovieCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/currentShows")
public class CurrentShowsController {

    @Autowired
    MovieCatalog catalogServiceProxy;

    @HystrixCommand(fallbackMethod = "testFallback")
    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<String>("Current Shows Service is running",HttpStatus.OK);
    }

    public ResponseEntity<String> testFallback() {
        return new ResponseEntity<String>("Current Shows Service is responding",HttpStatus.BAD_REQUEST);
    }

    @HystrixCommand
    @GetMapping("/get-movie/{movieId}")
    public ResponseEntity<MovieDTO> getInventory(@PathVariable("movieId") Integer bookId) {
        return catalogServiceProxy.getInventory(bookId);
    }



}