package KalongKingdom.corp.Serverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import KalongKingdom.corp.Serverapp.models.Peminjaman;
import KalongKingdom.corp.Serverapp.repositories.PeminjamanRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeminjamanService {

    private PeminjamanRepository peminjamanRepository;

    public List<Peminjaman> getAll() {
        return peminjamanRepository.findAll();
    }

    public Peminjaman getById(Integer id) {
        return peminjamanRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Peminjaman not Found"));
    }

    public Peminjaman create(Peminjaman peminjaman) {
        if (peminjaman.getBuku() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buku cannot be empty");
        }

        // if (isBukuDipinjam(peminjaman.getBuku().getId())) {
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buku is already
        // borrowed");
        // }

        return peminjamanRepository.save(peminjaman);
    }

    public Peminjaman update(Integer id, Peminjaman peminjaman) {
        getById(id);
        peminjaman.setId(id);
        return peminjamanRepository.save(peminjaman);
    }

    public Peminjaman delete(Integer id) {
        Peminjaman peminjaman = getById(id);
        peminjamanRepository.delete(peminjaman);
        return peminjaman;
    }

    private boolean isBukuDipinjam(Integer id) {
        Optional<Peminjaman> peminjaman = peminjamanRepository.findById(id);
        return peminjaman.isPresent();
    }

    public List<Peminjaman> findByNameGuest(String username) {
        return peminjamanRepository.findByNameGuest(username);
    }
}
