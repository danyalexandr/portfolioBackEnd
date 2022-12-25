package com.argentina.programa.jwt.test.controller;

import com.argentina.programa.jwt.test.model.Persona;
import com.argentina.programa.jwt.test.service.PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://portfoliofrontend-danyalexandr.web.app")
public class PersonaController {
    
    @Autowired PersonaService personaservice;
    
    @GetMapping("/persona/traer")
    public List<Persona> getPersona(){
        return personaservice.getPersona();
    }
    
    @PostMapping("/persona/crear")
    public String createPresona(@RequestBody Persona persona){
        personaservice.savePersona(persona);
        return "la persona fue creada";
    }
    @DeleteMapping("/persona/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        personaservice.deletePersona(id);
        return "la persona se borro ok";
    }
    @PutMapping("/persona/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("puesto") String nuevoPuesto,
                               @RequestParam("acercaDe") String nuevoAcercDe){
     
        Persona persona = personaservice.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        personaservice.savePersona(persona);
        return persona;
    }
    @GetMapping("/persona/traer/perfil")
    public Persona findPersona(){
        return personaservice.findPersona((long)1);
    }
}


