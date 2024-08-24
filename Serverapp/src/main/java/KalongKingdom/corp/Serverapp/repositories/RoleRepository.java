package KalongKingdom.corp.Serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import KalongKingdom.corp.Serverapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
