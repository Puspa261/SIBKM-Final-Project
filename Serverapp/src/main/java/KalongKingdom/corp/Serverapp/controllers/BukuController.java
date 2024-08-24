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

import KalongKingdom.corp.Serverapp.models.Buku;
import KalongKingdom.corp.Serverapp.service.BukuService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/buku")
@PreAuthorize("hasAnyRole('ADMIN','GUEST')")
public class BukuController {
    private BukuService bukuService;

    // @PreAuthorize("hasAuthority('READ_GUEST')")
    @GetMapping
    public List<Buku> getAll() {
        return bukuService.getAll();
    }

    // @PreAuthorize("hasAuthority('READ_GUEST')")
    @GetMapping("/{id}")
    public Buku getById(@PathVariable Integer id) {
        return bukuService.getById(id);
    }

    @PostMapping
    public Buku create(@RequestBody Buku buku) {
        return bukuService.create(buku);
    }

    @PutMapping("/{id}")
    public Buku update(@PathVariable Integer id, @RequestBody Buku buku) {
        return bukuService.update(id, buku);
    }

    @DeleteMapping("/{id}")
    public Buku delete(@PathVariable Integer id) {
        return bukuService.delete(id);
    }

    // @GetMapping("/judul")
    // public Buku getBuku(@RequestParam(name = "judul") String judul) {
    // return bukuService.getByJudul(judul);
    // }

    @GetMapping("/judul")
    public List<Buku> getBukuByJudul(@RequestParam(name = "judul") String judul) {
        return bukuService.getByJudul(judul);
    }

    @GetMapping("/kategori")
    public List<Buku> findKategori(@RequestParam(name = "name") String name) {
        return bukuService.findKategori(name);
    }

}
