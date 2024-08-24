package KalongKingdom.corp.Serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import KalongKingdom.corp.Serverapp.models.Buku;
import KalongKingdom.corp.Serverapp.models.Peminjaman;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer> {
    List<Peminjaman> findByBuku(Buku buku);

    @Query("SELECT p FROM Peminjaman p WHERE p.guest.user.username = :username")
    List<Peminjaman> findByNameGuest(@Param("username") String username);
}
