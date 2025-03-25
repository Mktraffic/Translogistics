package com.translogistics.translogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.translogistics.translogistics.model.Vehiculo;




@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    
}
