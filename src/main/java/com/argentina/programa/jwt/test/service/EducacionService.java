package com.argentina.programa.jwt.test.service;

import com.argentina.programa.jwt.test.model.Educacion;
import com.argentina.programa.jwt.test.repository.EducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducacionService {
    
    @Autowired EducacionRepository educacionrepository;
    
    public List<Educacion> List(){
    return educacionrepository.findAll();
    }
    
    public Optional<Educacion> getOne(int id){
        return educacionrepository.findById(id);
    }
    
    public Optional<Educacion> getByInstitucion(String institucion){
        return educacionrepository.findByInstitucion(institucion);
    }
    
    public void save(Educacion edu){
        educacionrepository.save(edu);
    }
    
    public void delete(int id){
        educacionrepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return educacionrepository.existsById(id);
    }
    
    public boolean existsByInstitucion(String institucion){
        return educacionrepository.existsByInstitucion(institucion);
    }
    
}
