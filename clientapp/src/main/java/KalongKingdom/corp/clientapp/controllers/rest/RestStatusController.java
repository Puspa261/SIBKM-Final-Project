package KalongKingdom.corp.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.clientapp.models.Status;
import KalongKingdom.corp.clientapp.services.StatusService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/status")
public class RestStatusController {
    
    private StatusService statusService;

    @GetMapping
    public List<Status> getAll() {
        return statusService.getAll();
    }

    @GetMapping("/id")
    public Status getById(@PathVariable Integer id) {
        return statusService.getById(id);
    }

    @PostMapping
    public Status create(@RequestBody Status peminjaman) {
        return statusService.create(peminjaman);
    }

    @PostMapping("/{id}")
    public Status update(@PathVariable Integer id, @RequestBody Status peminjaman){
        return statusService.update(id, peminjaman);
    }

    @DeleteMapping("/{id}")
    public Status delete(@PathVariable Integer id) {
        return statusService.delete(id);
    }
}
