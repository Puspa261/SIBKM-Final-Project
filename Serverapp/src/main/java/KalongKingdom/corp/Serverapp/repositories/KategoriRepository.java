package KalongKingdom.corp.Serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import KalongKingdom.corp.Serverapp.models.Kategori;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Integer> {

}
