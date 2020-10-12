package iitu.kz.MovieCatalog.Repository;

import iitu.kz.MovieCatalog.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}