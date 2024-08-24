package KalongKingdom.corp.Serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import KalongKingdom.corp.Serverapp.models.Guest;
import KalongKingdom.corp.Serverapp.repositories.GuestRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuestService {

    private GuestRepository guestRepository;

    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public Guest getById(Integer id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not Found"));
    }

    public Guest getAccountByName(String username) {
        return guestRepository.findByGuest(username);
    }

    public Guest create(Guest guest) {
        if (guest.getName() == null || guest.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guest need required");
        }
        return guestRepository.save(guest);
    }

    public Guest update(Integer id, Guest guest) {
        getById(id);
        guest.setId(id);
        return guestRepository.save(guest);
    }

    public Guest delete(Integer id) {
        Guest guest = getById(id);
        guestRepository.deleteById(id);
        return guest;
    }

}
