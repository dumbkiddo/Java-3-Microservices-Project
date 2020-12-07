package iitu.kz.MovieRequestService.Repository;

import iitu.kz.MovieRequestService.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
