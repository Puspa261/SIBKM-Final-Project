package KalongKingdom.corp.clientapp.controllers.rest;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.clientapp.models.Guest;
import KalongKingdom.corp.clientapp.services.GuestService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/guest")
public class RestGuestController {

    private GuestService guestService;

    @GetMapping
    public List<Guest> getAll() {
        return guestService.getAll();
    }

    @GetMapping("/{id}")
    public Guest getById(@PathVariable Integer id) {
        return guestService.getById(id);
    }

    @PostMapping
    public Guest create(@RequestBody Guest guest) {
        return guestService.create(guest);
    }

    @PutMapping("/{id}")
    public Guest update(@PathVariable Integer id, @RequestBody Guest guest) {
        return guestService.update(id, guest);
    }

    @DeleteMapping("/{id}")
    public Guest delete(@PathVariable Integer id) {
        return guestService.delete(id);
    }

    @GetMapping("/profile")
    private Guest getProfile(Authentication auth) {
        return guestService.getByName(auth.getName());
    }

}
