package iitu.kz.MovieCatalog.Model;
import java.util.List;

public class UserReview {

    //    private String userId;
    private List<Review> userRating;

    public UserReview(){

    }

//    public String getUserId() {
//        return userId;
//    }

//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public List<Review> getRatings() {
        return userRating;
    }

    public void setRatings(List<Review> userRating) {
        this.userRating = userRating;
    }
}

