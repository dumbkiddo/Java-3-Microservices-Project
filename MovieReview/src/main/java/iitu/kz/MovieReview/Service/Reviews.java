package iitu.kz.MovieReview.Service;

import iitu.kz.MovieReview.Model.Review;
import iitu.kz.MovieReview.Model.UserReview;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/reviews")
public class Reviews {

    @RequestMapping("/{movieID}")
    public Review getRatings(@PathVariable("movieID") String movieID){
        return new Review(movieID ,"5");
    }

    @RequestMapping("users/{userId}")
    public UserReview getUserRatings(@PathVariable("userId") String userId){

        List<Review> userRates = Arrays.asList(
                new Review("1","5"),
                new Review("2","4")
        );

        UserReview userRate = new UserReview();
        userRate.setRatings(userRates);
        return userRate;
    }
}
