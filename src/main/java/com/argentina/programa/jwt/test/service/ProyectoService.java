package com.argentina.programa.jwt.test.service;

import com.argentina.programa.jwt.test.model.Proyectos;
import com.argentina.programa.jwt.test.repository.ProyectosRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectoService {
    
    @Autowired ProyectosRepository proyectorepository;
    
    public List<Proyectos> List(){
    return proyectorepository.findAll();
    }
    
    public Optional<Proyectos> getOne(int id){
        return proyectorepository.findById(id);
    }
    
    public Optional<Proyectos> getByNombre(String nombre){
        return proyectorepository.findByNombre(nombre);
    }
    
    public void save(Proyectos pro){
        proyectorepository.save(pro);
    }
    
    public void delete(int id){
        proyectorepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return proyectorepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return proyectorepository.existsByNombre(nombre);
    }
}
