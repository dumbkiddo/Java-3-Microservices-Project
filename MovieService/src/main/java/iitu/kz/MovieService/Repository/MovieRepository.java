package iitu.kz.MovieService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import iitu.kz.MovieService.Model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
