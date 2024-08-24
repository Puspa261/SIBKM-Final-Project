package KalongKingdom.corp.Serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import KalongKingdom.corp.Serverapp.models.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
