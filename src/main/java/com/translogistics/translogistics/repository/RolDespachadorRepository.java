package com.translogistics.translogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.translogistics.translogistics.model.RolDespachador;

@Repository
public interface RolDespachadorRepository extends JpaRepository<RolDespachador, Long> {

}


