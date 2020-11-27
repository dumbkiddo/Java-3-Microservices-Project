package iitu.kz.NotificationService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Integer id;
    private String title;
    private String smallDesc;
    private String longDesc;
    private Double price;

    @Override
    public String toString() {
        return " Title='" + title + '\'' +
                ", synopsis='" + smallDesc + '\'' +
                ", details='" + longDesc;
    }

}
