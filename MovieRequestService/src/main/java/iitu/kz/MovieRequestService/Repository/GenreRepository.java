package iitu.kz.MovieRequestService.Repository;

import iitu.kz.MovieRequestService.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
