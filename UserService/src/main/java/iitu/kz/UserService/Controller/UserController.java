package iitu.kz.UserService.Controller;

import iitu.kz.UserService.DTO.UserDTO;
import iitu.kz.UserService.Model.User;
import iitu.kz.UserService.Repository.UserRepository;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

@RibbonClient(name="user-service")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<String>("User Service is running",HttpStatus.OK);
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value="/register",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> registerNewUser(@Valid @RequestBody UserDTO userDto, Errors errors) {

        if(userDto.getUsername() != null) {
            User existingUser = userRepository.findByUsername(userDto.getUsername());
            if(existingUser !=null) {

                errors.reject("Username is not available", "User with this username already exists'"+userDto.getUsername()+"'. ");
            }
            existingUser = userRepository.findByEmail(userDto.getEmail());
            if(existingUser !=null) {
                errors.reject("Email is not available", "User with this email already exists '"+userDto.getEmail()+"'. ");
            }

            if(!userDto.getPassword().equalsIgnoreCase(userDto.getConfirmPassword())) {
                errors.reject("Passwords do not match", "Password and confirmation are not the same");
            }
        }

        if(errors.hasErrors()) {
            List<String> errorMsg = new ArrayList<String>();
            errors.getAllErrors().forEach(a -> errorMsg.add(a.getDefaultMessage()));
            return new ResponseEntity<List<String>>(errorMsg, HttpStatus.BAD_REQUEST);
        }else {
            User userEntity = new User();
            userEntity.setUsername(userDto.getUsername());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userEntity.setEmail(userDto.getEmail());
            userEntity.setMobile(userDto.getMobile());

            userRepository.save(userEntity);
            List<String> msgLst = Arrays.asList("User registered successfully");
            return new ResponseEntity<List<String>>(msgLst, HttpStatus.OK);
        }
    }
}
