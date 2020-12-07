package iitu.kz.ApiGateway.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class UserDetails {

    private int id;

    private String firstName;

    private String lastName;

    //private String address;

    private String city;

    private String telephone;

    private final List<BookingDetails> pets = new ArrayList<>();

    @JsonIgnore
    public List<Integer> getPetIds() {
        return pets.stream()
            .map(BookingDetails::getId)
            .collect(toList());
    }
}
