package iitu.kz.MovieDescription.Service;

import iitu.kz.MovieDescription.Model.MovieDescription;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/description")
public class MovieDescriptionService {

    @RequestMapping("/{movieID}")
    public MovieDescription getInfo(@PathVariable("movieID") String movieID){
        return new MovieDescription(movieID,"Bird Box","The film follows a woman, played by Sandra Bullock, as she tries to protect herself and two children from malevolent supernatural entities that make people who look at them go insane and kill themselves.");
    }


}
