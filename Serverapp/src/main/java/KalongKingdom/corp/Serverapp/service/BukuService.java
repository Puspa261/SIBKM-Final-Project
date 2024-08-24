package KalongKingdom.corp.Serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import KalongKingdom.corp.Serverapp.models.Buku;
import KalongKingdom.corp.Serverapp.repositories.BukuRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BukuService {

    private BukuRepository bukuRepository;

    public List<Buku> getAll() {
        return bukuRepository.findAll();
    }

    public Buku getById(Integer id) {
        return bukuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buku not Found"));
    }

    public List<Buku> getByJudul(String judul) {
        return bukuRepository.findByBuku("%" + judul + "%");
    }

    public Buku create(Buku buku) {
        if (buku.getJudul() == null || buku.getJudul().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Judul need required");
        }
        return bukuRepository.save(buku);
    }

    public Buku update(Integer id, Buku buku) {
        getById(id);
        buku.setId(id);
        return bukuRepository.save(buku);
    }

    public Buku delete(Integer id) {
        Buku buku = getById(id);
        bukuRepository.delete(buku);
        return buku;
    }

    public List<Buku> findKategori(String name) {
        return bukuRepository.findKategori(name);
    }
}
