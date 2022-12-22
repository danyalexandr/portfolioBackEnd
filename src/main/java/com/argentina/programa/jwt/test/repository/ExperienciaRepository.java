package com.argentina.programa.jwt.test.repository;

import com.argentina.programa.jwt.test.model.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer>{
    public Optional<Experiencia> findByPuesto(String puesto);
    public boolean existsByPuesto(String puesto);
}
