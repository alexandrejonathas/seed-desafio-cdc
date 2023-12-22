package br.com.deveficiente.cdc.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
// Total da carga intrínseca 2
public class CreateAutorController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/api/autores")
    //1 Acoplamento contextual classe CreateAutorRequest
    public ResponseEntity<?> create(@Valid @RequestBody CreateAutorRequest request) {
        //1 Acoplamento contextual classe Autor
        Autor autor = request.toModel();
        manager.persist(autor);        
        return ResponseEntity.ok(autor);
    }
}
