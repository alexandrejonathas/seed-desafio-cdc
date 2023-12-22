package br.com.deveficiente.cdc.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class CreateLivroController {
    
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/api/livros")
    public ResponseEntity<?> create(@Valid @RequestBody CreateLivroRequest request) {
        Livro livro = request.toModel(manager);
        manager.persist(livro);
        return ResponseEntity.ok(livro);
    }
}
