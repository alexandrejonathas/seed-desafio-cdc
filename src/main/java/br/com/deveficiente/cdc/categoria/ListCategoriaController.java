package br.com.deveficiente.cdc.categoria;

import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
// Total da carga intrínseca 1
public class ListCategoriaController {
    
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/api/categorias")
    public ResponseEntity<?> list() {
        //1 Acoplamento contextual classe Categoria
        var categorias = manager.createQuery("SELECT c FROM Categoria c").getResultList();
        return ResponseEntity.ok(categorias);
    }
    
}
