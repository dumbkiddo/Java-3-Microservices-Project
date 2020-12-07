package iitu.kz.ReviewService.Repository;

import java.util.Collection;
import java.util.List;

import iitu.kz.ReviewService.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByPetId(int petId);

    List<Review> findByPetIdIn(Collection<Integer> petIds);
}
