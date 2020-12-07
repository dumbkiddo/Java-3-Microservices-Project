package iitu.kz.UserService.Controller;

import lombok.Data;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
class BookingRequest {
    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date movieDate;

    @Size(min = 1)
    private String name;

    private int typeId;
}
