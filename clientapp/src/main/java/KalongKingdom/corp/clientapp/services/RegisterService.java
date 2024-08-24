package KalongKingdom.corp.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import KalongKingdom.corp.clientapp.models.Guest;
import KalongKingdom.corp.clientapp.models.dto.request.GuestRequest;

@Service
public class RegisterService {

    @Value("${server.base.url}/registration")
    private String regisUrl;

    @Autowired
    private RestTemplate restTemplate;

    // register
    public Guest registerUser(GuestRequest guestRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic a2Fsb25nOmthbG9uZ0tpbmdkb20=");
        return restTemplate
                .exchange(
                        regisUrl,
                        HttpMethod.POST,
                        new HttpEntity<>(guestRequest, headers),
                        Guest.class)
                .getBody();
    }

}
