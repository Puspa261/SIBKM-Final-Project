package KalongKingdom.corp.Serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import KalongKingdom.corp.Serverapp.models.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

    @Query("SELECT e FROM Guest e WHERE e.user.username = :username")
    public Guest findByGuest(@Param("username") String username);

}
