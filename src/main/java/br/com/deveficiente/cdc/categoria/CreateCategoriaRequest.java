package br.com.deveficiente.cdc.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.deveficiente.cdc.validacao.AtributoValorUnico;
import jakarta.validation.constraints.NotBlank;

public class CreateCategoriaRequest {
    
    @NotBlank
    @AtributoValorUnico(classe = Categoria.class, atributo = "nome")
    @JsonProperty
    private String nome;

    @JsonCreator
    public CreateCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(nome);
    }
}
