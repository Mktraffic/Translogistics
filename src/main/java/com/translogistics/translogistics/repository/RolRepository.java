package com.translogistics.translogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.translogistics.translogistics.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
