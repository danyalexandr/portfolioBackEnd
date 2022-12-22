package com.argentina.programa.jwt.test.controller;

import com.argentina.programa.jwt.test.dto.TecnologiasDTO;
import com.argentina.programa.jwt.test.model.Tecnologias;
import com.argentina.programa.jwt.test.service.TecnologiaService;
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
@RequestMapping("tecno")
@CrossOrigin(origins = "http://localhost:4200")
public class TecnologiasController {
    
    @Autowired TecnologiaService tecnologiaservice;
    
    @GetMapping("/listatecno")
    public ResponseEntity<List<Tecnologias>> List(){
        
        List<Tecnologias> list = tecnologiaservice.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detailtecno/{id}")
    public ResponseEntity<Tecnologias> getById(@PathVariable("id") int id){
        if(!tecnologiaservice.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Tecnologias tecnologias = tecnologiaservice.getOne(id).get();
        return new ResponseEntity(tecnologias, HttpStatus.OK);
    }
    
    @PostMapping("/creartecno")
    public ResponseEntity<?> create(@RequestBody TecnologiasDTO dtotecno){
        if(StringUtils.isBlank(dtotecno.getHabilidad())){
            return new ResponseEntity(new Mensaje("obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(tecnologiaservice.existsByHabilidad(dtotecno.getHabilidad()))
            return new ResponseEntity(new Mensaje("ya xiste"), HttpStatus.BAD_REQUEST);
        
        Tecnologias tecnologias = new Tecnologias(dtotecno.getHabilidad(), dtotecno.getPorcentaje());
        tecnologiaservice.save(tecnologias);
        return new ResponseEntity(new Mensaje("agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/updatetecno/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody TecnologiasDTO dtotecno){
        
        if(!tecnologiaservice.existsById(id))
         return new ResponseEntity(new Mensaje("no existe id"),HttpStatus.BAD_REQUEST);        
        
        if(tecnologiaservice.existsByHabilidad(dtotecno.getHabilidad()) && tecnologiaservice.getByHabilidad(dtotecno.getHabilidad()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtotecno.getHabilidad()))
            return new ResponseEntity(new Mensaje("obligatorio"), HttpStatus.BAD_REQUEST);
        
        Tecnologias tecnologias = tecnologiaservice.getOne(id).get();
        tecnologias.setHabilidad(dtotecno.getHabilidad());
        tecnologias.setPorcentaje(dtotecno.getPorcentaje());      
        
        tecnologiaservice.save(tecnologias);
        return new ResponseEntity(new Mensaje("actualizado"), HttpStatus.OK);
        }
    
    @DeleteMapping("/borrartecno/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") int id){
            
           if(!tecnologiaservice.existsById(id))
                return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
            
            tecnologiaservice.delete(id);
            return new ResponseEntity(new Mensaje("eliminado"), HttpStatus.OK);
        }

    private static class Mensaje {

        public Mensaje(String agregado) {
        }
    }
}
