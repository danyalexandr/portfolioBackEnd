
package com.argentina.programa.jwt.test.service;

import com.argentina.programa.jwt.test.model.Persona;
import com.argentina.programa.jwt.test.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    
    @Autowired PersonaRepository personaRepository;
    
    public List<Persona> getPersona() {
        List<Persona> persona = personaRepository.findAll();
        return persona;
    }

    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    public void deletePersona(Long id) {
       personaRepository.deleteById(id);
    }

    public Persona findPersona(Long id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        return persona;
    }
}
