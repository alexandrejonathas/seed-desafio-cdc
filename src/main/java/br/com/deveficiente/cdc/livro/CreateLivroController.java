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
// Total da carga intrínseca 2
public class CreateLivroController {
    
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/api/livros")
    //1 Acoplamento contextual classe CreateLivroRequest
    public ResponseEntity<?> create(@Valid @RequestBody CreateLivroRequest request) {
        //1 Acoplamento contextual classe Livro
        Livro livro = request.toModel(manager);
        manager.persist(livro);
        return ResponseEntity.ok(livro);
    }
}
