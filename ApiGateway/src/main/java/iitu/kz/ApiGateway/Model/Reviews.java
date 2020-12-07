package iitu.kz.ApiGateway.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;

@Value
public class Reviews {

    private List<ReviewDetails> items = new ArrayList<>();

}
