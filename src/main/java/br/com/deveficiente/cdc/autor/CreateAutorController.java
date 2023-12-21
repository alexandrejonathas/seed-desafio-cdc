package br.com.deveficiente.cdc.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CreateAutorController {
    
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/api/autores")    
    public ResponseEntity<?> create(@Valid @RequestBody CreateAutorRequest request) {
        manager.persist(request.toModel());
        return ResponseEntity.ok().build();
    }
}
