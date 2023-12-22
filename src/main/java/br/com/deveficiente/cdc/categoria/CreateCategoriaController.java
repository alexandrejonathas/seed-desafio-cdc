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
// Total da carga intrínseca 2
public class CreateCategoriaController {
    
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/api/categorias")
    //1 Acoplamento contextual classe CreateCategoriaRequest 
    public ResponseEntity<?> create(@Valid @RequestBody CreateCategoriaRequest request) {
        //1 Acoplamento contextual classe Categoria
        Categoria categoria = request.toModel();
        manager.persist(categoria);

        return ResponseEntity.ok(categoria);
    }

}
