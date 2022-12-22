
package com.argentina.programa.jwt.test.repository;

import com.argentina.programa.jwt.test.model.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer>{
    public Optional<Educacion> findByInstitucion(String institucion);
    public boolean existsByInstitucion(String institucion);
}
