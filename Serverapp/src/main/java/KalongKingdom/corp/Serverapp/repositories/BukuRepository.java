package KalongKingdom.corp.Serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import KalongKingdom.corp.Serverapp.models.Buku;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Integer> {

    @Query("SELECT b FROM Buku b WHERE b.judul LIKE %:judul%")
    public List<Buku> findByBuku(@Param("judul") String judul);

    @Query("SELECT i FROM Buku i WHERE i.kategori.name = :name")
    List<Buku> findKategori(@Param("name") String name);

}
