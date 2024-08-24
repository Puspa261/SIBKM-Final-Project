package KalongKingdom.corp.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import KalongKingdom.corp.clientapp.models.Buku;

@Service
public class BukuService {

        @Value("${server.base.url}/buku")
        private String url;

        @Autowired
        private RestTemplate restTemplate;

        // GetAll
        public List<Buku> getAll() {
                return restTemplate
                                .exchange(
                                                url,
                                                HttpMethod.GET,
                                                null,
                                                new ParameterizedTypeReference<List<Buku>>() {
                                                })
                                .getBody();
        }

        // GetById
        public Buku getById(Integer id) {
                return restTemplate
                                .exchange(
                                                url + "/" + id,
                                                HttpMethod.GET,
                                                null,
                                                Buku.class)
                                .getBody();
        }

        public List<Buku> getByJudul(String judul) {
                return restTemplate
                        .exchange(
                                url + "/search?judul=" + judul,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<Buku>>() {})
                        .getBody();
        }

        public Buku create(Buku buku) {
                return restTemplate
                                .exchange(
                                                url,
                                                HttpMethod.POST,
                                                new HttpEntity<>(buku),
                                                Buku.class)
                                .getBody();
        }

        // Update (Buku)
        public Buku update(Integer id, Buku buku) {
                return restTemplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.PUT,
                                                new HttpEntity<>(buku),
                                                Buku.class)
                                .getBody();
        }

        // Delete (Buku)
        public Buku delete(Integer id) {
                return restTemplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.DELETE,
                                                null,
                                                Buku.class)
                                .getBody();
        }

        public Buku getBuku(String judul) {
                return restTemplate
                                .exchange(
                                                url.concat("?judul=" + judul),
                                                HttpMethod.GET,
                                                null,
                                                Buku.class)
                                .getBody();

        }

        public Buku getKategori(String name) {
                return restTemplate
                                .exchange(
                                                url.concat("/kategori?name=" + name),
                                                HttpMethod.GET,
                                                null,
                                                Buku.class)
                                .getBody();
        }
}
