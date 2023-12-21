package br.com.deveficiente.cdc.autor;

import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ListAutorController {
    
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/api/autores")
    public ResponseEntity<?> list() {
        var autores = manager.createQuery("SELECT a FROM Autor a").getResultList();
        return ResponseEntity.ok(autores);
    }
    
}
