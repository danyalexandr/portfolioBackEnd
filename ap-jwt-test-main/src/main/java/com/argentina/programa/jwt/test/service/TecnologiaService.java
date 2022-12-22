package com.argentina.programa.jwt.test.service;

import com.argentina.programa.jwt.test.model.Tecnologias;
import com.argentina.programa.jwt.test.repository.TecnologiasRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TecnologiaService {
    
    @Autowired TecnologiasRepository tecnologiarepository;
    
    public List<Tecnologias> List(){
    return tecnologiarepository.findAll();
    }
    
    public Optional<Tecnologias> getOne(int id){
        return tecnologiarepository.findById(id);
    }
    
    public Optional<Tecnologias> getByHabilidad(String habilidad){
        return tecnologiarepository.findByHabilidad(habilidad);
    }
    
    public void save(Tecnologias tecno){
        tecnologiarepository.save(tecno);
    }
    
    public void delete(int id){
        tecnologiarepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return tecnologiarepository.existsById(id);
    }
    
    public boolean existsByHabilidad(String habilidad){
        return tecnologiarepository.existsByHabilidad(habilidad);
    }
}
