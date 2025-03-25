package com.translogistics.translogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.translogistics.translogistics.model.RegistroViaje;

@Repository
public interface RegistroViajeRepository extends JpaRepository<RegistroViaje, Long>{
}
