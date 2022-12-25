package com.argentina.programa.jwt.test.controller;

import com.argentina.programa.jwt.test.dto.ProyectosDTO;
import com.argentina.programa.jwt.test.model.Proyectos;
import com.argentina.programa.jwt.test.service.ProyectoService;
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
@RequestMapping("proyecto")
@CrossOrigin(origins = "https://portfoliofrontend-danyalexandr.web.app/")
public class ProyectosController {
    
    @Autowired ProyectoService proyectoservice;
    
    @GetMapping("/listapro")
    public ResponseEntity<List<Proyectos>> List(){
        
        List<Proyectos> list = proyectoservice.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detailpro/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!proyectoservice.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyectos proyectos = proyectoservice.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @PostMapping("/crearpro")
    public ResponseEntity<?> create(@RequestBody ProyectosDTO dtopro){
        if(StringUtils.isBlank(dtopro.getNombre())){
            return new ResponseEntity(new Mensaje("obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(proyectoservice.existsByNombre(dtopro.getNombre()))
            return new ResponseEntity(new Mensaje("ya xiste"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = new Proyectos(dtopro.getNombre(), dtopro.getDescripcion());
        proyectoservice.save(proyectos);
        return new ResponseEntity(new Mensaje("agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/updatepro/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectosDTO dtopro){
        
        if(!proyectoservice.existsById(id))
         return new ResponseEntity(new Mensaje("no existe id"),HttpStatus.BAD_REQUEST);        
        
        if(proyectoservice.existsByNombre(dtopro.getNombre()) && proyectoservice.getByNombre(dtopro.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtopro.getNombre()))
            return new ResponseEntity(new Mensaje("obligatorio"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = proyectoservice.getOne(id).get();
        proyectos.setNombre(dtopro.getNombre());
        proyectos.setDescripcion(dtopro.getDescripcion());      
        
        proyectoservice.save(proyectos);
        return new ResponseEntity(new Mensaje("actualizado"), HttpStatus.OK);
        }
    
    @DeleteMapping("/borrarpro/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") int id){
            
           if(!proyectoservice.existsById(id))
                return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
            
            proyectoservice.delete(id);
            return new ResponseEntity(new Mensaje("eliminado"), HttpStatus.OK);
        }

    private static class Mensaje {

        public Mensaje(String agregado) {
        }
    }
}
