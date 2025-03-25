package com.translogistics.translogistics.repository;

import com.translogistics.translogistics.model.RolAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolAdministradorRepository extends JpaRepository<RolAdministrador, Long> {

}
