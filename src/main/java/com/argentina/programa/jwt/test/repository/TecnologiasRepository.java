package com.argentina.programa.jwt.test.repository;

import com.argentina.programa.jwt.test.model.Tecnologias;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiasRepository extends JpaRepository<Tecnologias, Integer>{
    public Optional<Tecnologias> findByHabilidad(String habilidad);
    public boolean existsByHabilidad(String habilidad);
}
