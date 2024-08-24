package KalongKingdom.corp.Serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import KalongKingdom.corp.Serverapp.models.Kategori;
import KalongKingdom.corp.Serverapp.repositories.KategoriRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KategoriService {

    private KategoriRepository kategoriRepository;

    public List<Kategori> getAll() {
        return kategoriRepository.findAll();
    }

    public Kategori getById(Integer id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategori Not Found"));
    }

    public Kategori create(Kategori kategori) {
        if (kategori.getName() == null || kategori.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kategori Need to be Input");
        }
        return kategoriRepository.save(kategori);
    }

    public Kategori update(Integer id, Kategori kategori) {
        getById(id);
        kategori.setId(id);
        return kategoriRepository.save(kategori);
    }

    public Kategori delete(Integer id) {
        Kategori kategori = getById(id);
        kategoriRepository.deleteById(id);
        return kategori;
    }
}
