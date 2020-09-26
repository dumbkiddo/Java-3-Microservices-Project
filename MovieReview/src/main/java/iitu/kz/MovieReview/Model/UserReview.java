package iitu.kz.MovieReview.Model;
import java.util.List;

public class UserReview {

    private List<Review> userRating;

    public UserReview(){

    }

    public List<Review> getRatings() {
        return userRating;
    }

    public void setRatings(List<Review> userRating) {
        this.userRating = userRating;
    }
}

