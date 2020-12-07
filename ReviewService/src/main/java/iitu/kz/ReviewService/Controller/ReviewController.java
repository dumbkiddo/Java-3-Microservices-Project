package iitu.kz.ReviewService.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import iitu.kz.ReviewService.Model.Review;
import iitu.kz.ReviewService.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
class ReviewController {

    private final ReviewRepository reviewRepository;

    @HystrixCommand
    @PostMapping("owners/*/pets/{petId}/visits")
    @ResponseStatus(HttpStatus.CREATED)
   public Review create(
        @Valid @RequestBody Review review,
        @PathVariable("petId") int petId) {
        review.setPetId(petId);
        log.info("Saving review {}", review);
        return reviewRepository.save(review);
    }

    @HystrixCommand
    @GetMapping("owners/*/pets/{petId}/visits")
    public List<Review> reviews(@PathVariable("petId") int bookingId) {
        return reviewRepository.findByPetId(bookingId);
    }

    @HystrixCommand(fallbackMethod = "getReviewsFallback",threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping("pets/visits")
    public Reviews reviewsMultiGet(@RequestParam("petId") List<Integer> bookingIds) {
        final List<Review> byReviewIdIn = reviewRepository.findByPetIdIn(bookingIds);
        return new Reviews(byReviewIdIn);
    }

    public Reviews getReviewsFallback(@RequestParam("petId") List<Integer> bookingIds) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(-1);
        final List<Review> reviewId = reviewRepository.findByPetIdIn(list);
        return new Reviews(reviewId);
    }

    @Value
    static class Reviews {
        List<Review> items;
    }
}
