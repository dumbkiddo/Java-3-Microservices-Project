package iitu.kz.CurrentShows.MovieCatalogProxy;

import iitu.kz.CurrentShows.DTO.MovieDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="catalog-service", path="/catalog" )
@RibbonClient(name="catalog-service")
public interface MovieCatalog {
    @GetMapping("/get-movie/{movieId}")
    public ResponseEntity<MovieDTO> getInventory(@PathVariable("movieId") Integer movieId);
}
