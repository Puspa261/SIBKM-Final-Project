package KalongKingdom.corp.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import KalongKingdom.corp.clientapp.models.Guest;

@Service
public class ProfileService {

    @Value("${server.base.url}/guest/profile")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public Guest getProfile(String name) {
        return restTemplate
                .exchange(
                        url.concat("?name=" + name),
                        HttpMethod.GET,
                        null,
                        Guest.class)
                .getBody();
    }

}
