package br.com.deveficiente.cdc.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.deveficiente.cdc.validacao.AtributoValorUnico;
import jakarta.validation.constraints.NotBlank;

// Total da carga intrínseca 2
public class CreateCategoriaRequest {
    
    @NotBlank
    //1 Acoplamento contextual classe AtributoValorUnicoValidator
    @AtributoValorUnico(classe = Categoria.class, atributo = "nome")
    @JsonProperty
    private String nome;

    @JsonCreator
    public CreateCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        //1 Acoplamento contextual classe Categoria
        return new Categoria(nome);
    }
}
