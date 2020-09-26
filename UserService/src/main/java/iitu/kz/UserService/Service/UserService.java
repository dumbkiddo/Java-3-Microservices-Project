package iitu.kz.UserService.Service;

import iitu.kz.UserService.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/users")
public class UserService {

    @RequestMapping("/{userID}")
    public User getInfo(@PathVariable("userID") String userID){
        return new User(userID,"User1");
    }


}
