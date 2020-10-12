package iitu.kz.CurrentShows.Feign;


import feign.RequestInterceptor;
import feign.RequestTemplate;

public class Feign implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
    }

}
