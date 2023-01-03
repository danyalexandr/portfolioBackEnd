package com.argentina.programa.jwt.test.controller;

import com.argentina.programa.jwt.test.dto.ExperienciaDTO;
import com.argentina.programa.jwt.test.model.Experiencia;
import com.argentina.programa.jwt.test.service.ExperienciaService;
import java.util.List;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exp")
@CrossOrigin(origins = "https://portfoliofrontend-danyalexandr.firebaseapp.com/")
public class ExperienciaController {
    
    @Autowired 
    ExperienciaService experienciaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> List(){
        
        List<Experiencia> list = experienciaService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienciaDTO dtoexp){
        if(StringUtils.isBlank(dtoexp.getPuesto())){
            return new ResponseEntity(new Mensaje("obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(experienciaService.existsByPuesto(dtoexp.getPuesto()))
            return new ResponseEntity(new Mensaje("ya xiste"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoexp.getPuesto(), dtoexp.getEmpresa(), dtoexp.getFechaInicio(), 
                                                  dtoexp.getFechaFin(), dtoexp.getLugar());
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDTO dtoexp){
        
        if(!experienciaService.existsById(id))
         return new ResponseEntity(new Mensaje("no existe id"),HttpStatus.BAD_REQUEST);        
        
        if(experienciaService.existsByPuesto(dtoexp.getPuesto()) && experienciaService.getByPuesto(dtoexp.getPuesto()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoexp.getPuesto()))
            return new ResponseEntity(new Mensaje("obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setPuesto(dtoexp.getPuesto());
        experiencia.setEmpresa(dtoexp.getEmpresa());
        experiencia.setFechaInicio(dtoexp.getFechaInicio());
        experiencia.setFechaFin(dtoexp.getFechaFin());
        experiencia.setLugar(dtoexp.getLugar());
        
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("actualizado"), HttpStatus.OK);
        }

        @DeleteMapping("/borrar/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") int id){
            
           if(!experienciaService.existsById(id))
                return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
            
            experienciaService.delete(id);
            return new ResponseEntity(new Mensaje("eliminado"), HttpStatus.OK);
        }
        
        private static class Mensaje {

        public Mensaje(String obligatorio) {
        }
    }
}
