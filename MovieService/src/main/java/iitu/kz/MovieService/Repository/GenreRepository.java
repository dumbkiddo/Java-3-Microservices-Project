package iitu.kz.MovieService.Repository;

import iitu.kz.MovieService.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
