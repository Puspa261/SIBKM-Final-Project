package KalongKingdom.corp.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import KalongKingdom.corp.clientapp.models.Guest;

@Service
public class GuestService {

        @Value("${server.base.url}/guest")
        private String url;

        @Autowired
        private RestTemplate restTemplate;

        // GetAll
        public List<Guest> getAll() {

                return restTemplate
                                .exchange(
                                                url,
                                                HttpMethod.GET,
                                                null,
                                                new ParameterizedTypeReference<List<Guest>>() {
                                                }

                                ).getBody();
        }

        // GetById
        public Guest getById(Integer id) {
                return restTemplate
                                .exchange(
                                                url + "/" + id,
                                                HttpMethod.GET,
                                                null,
                                                Guest.class)
                                .getBody();
        }

        public Guest create(Guest guest) {
                return restTemplate
                                .exchange(
                                                url,
                                                HttpMethod.POST,
                                                new HttpEntity<>(guest),
                                                Guest.class)
                                .getBody();
        }

        // Update (Guest)
        public Guest update(Integer id, Guest guest) {
                return restTemplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.PUT,
                                                new HttpEntity<>(guest),
                                                Guest.class)
                                .getBody();
        }

        // Delete (Guest)
        public Guest delete(Integer id) {
                return restTemplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.DELETE,
                                                null,
                                                Guest.class)
                                .getBody();
        }

        // GetByName (Guest)
        public Guest getByName(String name) {
                return restTemplate
                                .exchange(
                                                url.concat("/profile/?username=" + name),
                                                HttpMethod.GET,
                                                null,
                                                Guest.class)
                                .getBody();
        }
}