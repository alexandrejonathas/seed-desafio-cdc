package br.com.deveficiente.cdc.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
public class CreateCategoriaController {
    
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/api/categorias")
    public ResponseEntity<?> create(@Valid @RequestBody CreateCategoriaRequest request) {
        Categoria categoria = request.toModel();
        manager.persist(categoria);

        return ResponseEntity.ok(categoria);
    }

}
