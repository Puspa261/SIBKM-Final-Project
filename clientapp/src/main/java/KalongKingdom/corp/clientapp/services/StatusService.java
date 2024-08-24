package KalongKingdom.corp.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import KalongKingdom.corp.clientapp.models.Status;

@Service
public class StatusService {
    
    @Value("${server.base.url}/status")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // GetAll
        public List<Status> getAll() {
        return restTemplate
            .exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Status>>() {
                        })
            .getBody();
        }

        // GetById
        public Status getById(Integer id) {
            return restTemplate
                .exchange(
                            url + "/" + id,
                            HttpMethod.GET,
                            null,
                            Status.class)
                .getBody();
        }

        public Status create(Status status) {
        return restTemplate
                .exchange(url,
                        HttpMethod.POST,
                        new HttpEntity<>(status),
                        Status.class)
                .getBody();
    }

    // Update (Status)
    public Status update(Integer id, Status status) {
        return restTemplate
                .exchange(url.concat("/" + id),
                        HttpMethod.PUT, new HttpEntity<>(status),
                        Status.class)
                .getBody();
    }

    // Delete (Status)
    public Status delete(Integer id) {
        return restTemplate
                .exchange(url.concat("/" + id),
                        HttpMethod.DELETE,
                        null,
                        Status.class)
                .getBody();
    }
}
