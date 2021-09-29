package com.dh.clinica.repository;
import org.springframework.stereotype.Repository;
import com.dh.clinica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
