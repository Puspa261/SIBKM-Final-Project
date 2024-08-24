package KalongKingdom.corp.clientapp.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.clientapp.models.Buku;
import KalongKingdom.corp.clientapp.services.BukuService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/buku")
public class RestBukuController {

    private BukuService bukuService;

    @GetMapping
    public List<Buku> getAll() {
        return bukuService.getAll();
    }

    @GetMapping("/{id}")
    public Buku getById(@PathVariable Integer id) {
        return bukuService.getById(id);
    }

    @GetMapping("/search")
    public List<Buku> searchByJudul(@RequestParam String judul) {
    return bukuService.getByJudul(judul);
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

    @GetMapping("/judul")
    public List<Buku> getBukuByJudul(@RequestParam(name = "judul") String judul) {
        Buku buku = bukuService.getBuku(judul);
        List<Buku> result = new ArrayList<>();
        result.add(buku);
        return result;
    }

    // @GetMapping("/idbuku")
    // public List<Buku> findIdBuku(@RequestParam(name = "id") Integer id) {
    // Buku kategori = bukuService.getKategori(id);
    // List<Buku> result = new ArrayList<>();
    // result.add(kategori);
    // return result;
    // }

    @GetMapping("/kategori")
    public Buku getKategori(Authentication auth) {
        return bukuService.getKategori(auth.getName());
    }

}
