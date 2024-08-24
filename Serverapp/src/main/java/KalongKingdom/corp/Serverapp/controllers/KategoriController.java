package KalongKingdom.corp.Serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.Serverapp.models.Kategori;
import KalongKingdom.corp.Serverapp.service.KategoriService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/kategori")
public class KategoriController {
    private KategoriService kategoriService;

    @GetMapping
    public List<Kategori> getAll() {
        return kategoriService.getAll();
    }

    @GetMapping("/{id}")
    public Kategori getById(@PathVariable Integer id) {
        return kategoriService.getById(id);
    }

    @PostMapping
    public Kategori create(@RequestBody Kategori kategori) {
        return kategoriService.create(kategori);
    }

    @PutMapping("/{id}")
    public Kategori update(@PathVariable Integer id, @RequestBody Kategori kategori) {
        return kategoriService.update(id, kategori);
    }

    @DeleteMapping("/{id}")
    public Kategori delete(@PathVariable Integer id) {
        return kategoriService.delete(id);
    }
}
