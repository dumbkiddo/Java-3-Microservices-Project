package iitu.kz.MovieCatalog.Catalog;

import iitu.kz.MovieCatalog.Model.CatalogMovie;
import iitu.kz.MovieCatalog.Model.MovieDescription;
import iitu.kz.MovieCatalog.Model.Review;
import iitu.kz.MovieCatalog.Model.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient.Builder webClient;

    @RequestMapping("/{userID}")
    public List<CatalogMovie> getCatalogItem(@PathVariable("userID")String userId){
//
//        List<Review> ratings = Arrays.asList(
//                new Review("1234","2"),
//                new Review("5678","3")
//        );
        UserReview ratings = restTemplate.getForObject("http://movie-reviews-service/reviews/users/" + userId,UserReview.class);
        return ratings.getRatings().stream().map(rating -> {
            MovieDescription infoObject = restTemplate.getForObject("http://movie-desc-service/description/" + rating.getMovieId() ,MovieDescription.class);
            // info infoObject = webClient.build().get().uri("http://localhost:8082/info/" + rating.getMovieId()).retrieve().bodyToMono(info.class).block();
            return new CatalogMovie(infoObject.getMovieName(), infoObject.getMovieDesc(), rating.getRating());
        }).collect(Collectors.toList());
    }
}