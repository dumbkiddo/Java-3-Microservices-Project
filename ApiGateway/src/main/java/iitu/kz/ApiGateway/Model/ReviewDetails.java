package iitu.kz.ApiGateway.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDetails {

    private Integer id = null;

    private Integer petId = null;

    private String date = null;

    private String description = null;
}
