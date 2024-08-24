package KalongKingdom.corp.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import KalongKingdom.corp.clientapp.models.Peminjaman;

@Service
public class PeminjamanService {

        @Value("${server.base.url}/peminjaman")
        private String url;

        @Autowired
        private RestTemplate restTemplate;

        // GetAll
        public List<Peminjaman> getAll() {
                return restTemplate
                                .exchange(
                                                url,
                                                HttpMethod.GET,
                                                null,
                                                new ParameterizedTypeReference<List<Peminjaman>>() {
                                                })
                                .getBody();
        }

        // GetById
        public Peminjaman getById(Integer id) {
                return restTemplate
                                .exchange(
                                                url + "/" + id,
                                                HttpMethod.GET,
                                                null,
                                                Peminjaman.class)
                                .getBody();
        }

        public Peminjaman create(Peminjaman peminjaman) {
                return restTemplate
                                .exchange(
                                                url,
                                                HttpMethod.POST,
                                                new HttpEntity<>(peminjaman),
                                                Peminjaman.class)
                                .getBody();
        }

        // Update (Peminjaman)
        public Peminjaman update(Integer id, Peminjaman peminjaman) {
                return restTemplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.PUT,
                                                new HttpEntity<>(peminjaman),
                                                Peminjaman.class)
                                .getBody();
        }

        // Delete (Peminjaman)
        public Peminjaman delete(Integer id) {
                return restTemplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.DELETE,
                                                null,
                                                Peminjaman.class)
                                .getBody();
        }

        public Peminjaman findByNameGuest(String name) {
                return restTemplate
                                .exchange(
                                                url.concat("/guest/?username=" + name),
                                                HttpMethod.GET,
                                                null,
                                                Peminjaman.class)
                                .getBody();
        }
}
