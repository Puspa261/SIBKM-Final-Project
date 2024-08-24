package KalongKingdom.corp.Serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.Serverapp.models.Peminjaman;
import KalongKingdom.corp.Serverapp.service.PeminjamanService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/peminjaman")
@PreAuthorize("hasAnyRole('GUEST', 'ADMIN')")
public class PeminjamanController {
    private PeminjamanService peminjamanService;

    @GetMapping
    public List<Peminjaman> getAll() {
        return peminjamanService.getAll();
    }

    @GetMapping("/{id}")
    public Peminjaman getById(@PathVariable Integer id) {
        return peminjamanService.getById(id);
    }

    // @PreAuthorize("hasAuthority('READ_GUEST')")
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
    public List<Peminjaman> findByNameGuest(@RequestParam(name = "username") String username) {
        return peminjamanService.findByNameGuest(username);
    }
}
