package com.translogistics.translogistics.repository;

import com.translogistics.translogistics.model.RolConductor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolConductorRepository extends JpaRepository<RolConductor, Long> {

}
