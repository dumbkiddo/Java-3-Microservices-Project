package iitu.kz.ApiGateway.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingDetails {

    private int id;

    private String name;

    private String movieDate;

    private BookingType type;

    private final List<ReviewDetails> visits = new ArrayList<>();

}
