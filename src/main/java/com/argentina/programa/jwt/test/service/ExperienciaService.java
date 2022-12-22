package com.argentina.programa.jwt.test.service;

import com.argentina.programa.jwt.test.model.Experiencia;
import com.argentina.programa.jwt.test.repository.ExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {
    
    @Autowired 
    ExperienciaRepository experienciarepository;
    
    public List<Experiencia> List(){
    return experienciarepository.findAll();
    }
    
    public Optional<Experiencia> getOne(int id){
        return experienciarepository.findById(id);
    }
    public Optional<Experiencia> getByPuesto(String puesto){
        return experienciarepository.findByPuesto(puesto);
    }
    
    public void save(Experiencia exp){
        experienciarepository.save(exp);
    }
    
    public void delete(int id){
        experienciarepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return experienciarepository.existsById(id);
    }
    
    public boolean existsByPuesto(String puesto){
        return experienciarepository.existsByPuesto(puesto);
    }
}
