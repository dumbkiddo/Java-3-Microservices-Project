package iitu.kz.MovieRequestService.Repository;

import iitu.kz.MovieRequestService.Model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
