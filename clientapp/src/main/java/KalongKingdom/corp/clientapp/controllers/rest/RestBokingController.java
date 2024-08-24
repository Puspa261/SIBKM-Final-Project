package KalongKingdom.corp.clientapp.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.clientapp.models.Peminjaman;
import KalongKingdom.corp.clientapp.services.PeminjamanService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/peminjaman")
public class RestBokingController {

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping
    public List<Peminjaman> getAll() {
        return peminjamanService.getAll();
    }

    @GetMapping("/{id}")
    public Peminjaman getById(@PathVariable Integer id) {
        return peminjamanService.getById(id);
    }

    @PostMapping
    public Peminjaman create(@RequestBody Peminjaman peminjaman) {
        return peminjamanService.create(peminjaman);
    }

    @PutMapping("/{id}")
    public Peminjaman update(@PathVariable Integer id, @RequestBody Peminjaman peminjaman) {
        return peminjamanService.update(id, peminjaman);
    }

    @DeleteMapping("/{id}")
    public Peminjaman delete(@PathVariable Integer id) {
        return peminjamanService.delete(id);
    }

    @GetMapping("/guest")
    public Peminjaman findByNameGuest(Authentication auth) {
        return peminjamanService.findByNameGuest(auth.getName());
    }
}
