package iitu.kz.MovieCatalog.Repository;

import iitu.kz.MovieCatalog.Model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
