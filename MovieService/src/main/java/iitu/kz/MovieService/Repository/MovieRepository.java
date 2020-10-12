package iitu.kz.MovieService.Repository;

import iitu.kz.MovieService.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
