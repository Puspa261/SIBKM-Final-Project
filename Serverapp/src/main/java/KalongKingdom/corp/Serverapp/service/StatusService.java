package KalongKingdom.corp.Serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import KalongKingdom.corp.Serverapp.models.Status;
import KalongKingdom.corp.Serverapp.repositories.StatusRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatusService {

    private StatusRepository statusRepository;

    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    public Status getById(Integer id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status Not Found"));
    }

    public Status create(Status status) {
        if (status.getStatus() == null || status.getStatus().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status need required");
        }
        return statusRepository.save(status);
    }

    public Status update(Integer id, Status status) {
        getById(id);
        status.setId(id);
        return statusRepository.save(status);
    }

    public Status delete(Integer id) {
        Status status = getById(id);
        statusRepository.delete(status);
        return status;
    }
}
