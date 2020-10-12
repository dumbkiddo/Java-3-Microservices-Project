package iitu.kz.MovieCatalog.Repository;

import iitu.kz.MovieCatalog.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}